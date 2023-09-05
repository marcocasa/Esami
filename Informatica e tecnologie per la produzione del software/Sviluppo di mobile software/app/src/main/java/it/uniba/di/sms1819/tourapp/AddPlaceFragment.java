package it.uniba.di.sms1819.tourapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import it.uniba.di.sms1819.tourapp.Models.Category;
import it.uniba.di.sms1819.tourapp.Models.Place;

public class AddPlaceFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    EditText placeNameEditText, descriptionItaEditText, descriptionEngEditText, ticketPriceEditText;
    Button saveButton, addImageButton;
    Spinner categoryListSpinner;
    ImageView imageView;
    LatLng newPlaceLatLng;
    TextView newPlacePositionTextView;
    GoogleMap googleMap;
    Marker marker;
    boolean imageSelected = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_place, container, false);
    }
    View.OnClickListener saveButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (placeNameEditText.getText().toString().length() < 5) {
                placeNameEditText.setError(String.format(getString(R.string.min_chars), 5));
                return;
            }

            if (descriptionItaEditText.getText().toString().length() < 30) {
                descriptionItaEditText.setError(String.format(getString(R.string.min_chars), 30));
                return;
            }

            if (descriptionEngEditText.getText().toString().length() < 30) {
                descriptionEngEditText.setError(String.format(getString(R.string.min_chars), 30));
                return;
            }

            if (!imageSelected) {
                Toast.makeText(getContext(), getString(R.string.select_image), Toast.LENGTH_SHORT).show();
                return;
            }

            Place place = new Place();

            final HashMap<String, String> description = new HashMap<>();
            StringBuilder keyName = new StringBuilder();
            int splitSize;
            int i = 1;

            place.category = Instance.categoryList.get(categoryListSpinner.getSelectedItemPosition()).id;

            place.name = placeNameEditText.getText().toString();
            description.put("ita", descriptionItaEditText.getText().toString());
            description.put("eng", descriptionEngEditText.getText().toString());
            place.description = description;

            if (!ticketPriceEditText.getText().toString().equals("")) {
                place.ticket_price = Double.valueOf(ticketPriceEditText.getText().toString());
            }

            place.latitude = newPlaceLatLng.latitude;
            place.longitude = newPlaceLatLng.longitude;

            // genero la chiave a partire dal nome. ad es: basilica-san-nicola
            String[] splitString = place.name.split(" ");
            splitSize = splitString.length;
            for (String word : splitString) {
                keyName.append(word.toLowerCase());
                if (i++ < splitSize) {
                    keyName.append("-");
                }
            }

            // Get the data from an ImageView as bytes
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            // salva il posto su firestore
            Instance.db.collection("places").document(keyName.toString()).set(place);

            // salva l'immagine di copertina su firestore
            StorageReference imageReference = Instance.storage.getReference("places").child(keyName.toString() + ".jpg");
            imageReference.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), getString(R.string.place_added), Toast.LENGTH_SHORT).show();

                    // reset form
                    imageView.setVisibility(View.GONE);
                    placeNameEditText.setText("");
                    descriptionEngEditText.setText("");
                    descriptionItaEditText.setText("");
                    newPlaceLatLng = new LatLng(Instance.lastKnownLocation.getLatitude(), Instance.lastKnownLocation.getLongitude());
                    marker.setPosition(newPlaceLatLng);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
                }
            });
        }
    };
    private OnCompleteListener<QuerySnapshot> onGetCategoriesListener = new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (!task.isSuccessful()) {
                Common.logError(task.getException().toString());
                return;
            }

            ArrayList<String> spinnerValues = new ArrayList<>();
            for (QueryDocumentSnapshot document : task.getResult()) {
                spinnerValues.add(document.toObject(Category.class).name.get(Common.getLocale()));
            }

            // carica lista categorie posti nello spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, spinnerValues);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoryListSpinner.setAdapter(adapter);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeNameEditText = view.findViewById(R.id.place_name);
        descriptionItaEditText = view.findViewById(R.id.description_ita);
        descriptionEngEditText = view.findViewById(R.id.description_eng);
        saveButton = view.findViewById(R.id.save);
        ticketPriceEditText = view.findViewById(R.id.ticket_price);
        categoryListSpinner = view.findViewById(R.id.category_list);
        addImageButton = view.findViewById(R.id.select_image);
        imageView = view.findViewById(R.id.image);
        newPlacePositionTextView = view.findViewById(R.id.new_place_position);

        // Carico le categorie e le aggiungo allo spinner
        Instance.db.collection("categories")
                .get()
                .addOnCompleteListener(onGetCategoriesListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        saveButton.setOnClickListener(saveButtonClick);
        addImageButton.setOnClickListener(selectImageButtonClick);
    }

    View.OnClickListener selectImageButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImagePicker.create(getActivity())
                    .limit(1) // max images can be selected (99 by default)
                    .start();
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // carica fragment mappa
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_fragment);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_fragment, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

        newPlaceLatLng = new LatLng(Instance.lastKnownLocation.getLatitude(), Instance.lastKnownLocation.getLongitude());
        newPlacePositionTextView.setText(String.format("%s, %s", Instance.lastKnownLocation.getLatitude(), Instance.lastKnownLocation.getLongitude()));
    }

    public void setImageFromActivityResult(Image image) {
        imageView.setVisibility(View.VISIBLE);
        imageSelected = true;
        Glide.with(getContext()).load(image.getPath()).into(imageView);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (newPlaceLatLng != null) {
            googleMap.setOnMapClickListener(this);
            marker = googleMap.addMarker(new MarkerOptions()
                    .position(newPlaceLatLng)
                    .title(getString(R.string.you_are_here)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        newPlaceLatLng = latLng;
        marker.setPosition(latLng);
        newPlacePositionTextView.setText(String.format("%s, %s", latLng.latitude, latLng.longitude));
    }
}
