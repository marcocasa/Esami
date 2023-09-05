package it.uniba.di.sms1819.tourapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import it.uniba.di.sms1819.tourapp.Models.Category;
import it.uniba.di.sms1819.tourapp.Models.Place;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    private Button openPlaceButton;
    private CardView placeInfo;
    private ImageView placeImage;
    private TextView placeName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    private OnCompleteListener<QuerySnapshot> onGetPlacesListener = new OnCompleteListener<QuerySnapshot>() {

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            Common.logDebug("Posti trovati: " + task.getResult().size());

            // se ho accesso alla posizione filtro adesso i posti per longitudine
            Common.Boundaries longitudeBoundaries = Common.getLongitudeBoundaries();

            for (DocumentSnapshot placeSnapshop : task.getResult()) {
                Place place = placeSnapshop.toObject(Place.class);

                place.id = placeSnapshop.getId();

                int markerIcon = R.drawable.marker_beach;

                switch (place.category) {
                    case "beach":
                        markerIcon = R.drawable.marker_beach;
                        break;
                    case "monument":
                        markerIcon = R.drawable.marker_monument;
                        break;
                    case "art":
                        markerIcon = R.drawable.marker_art;
                        break;
                    case "bar":
                        markerIcon = R.drawable.marker_bar;
                        break;
                    case "club":
                        markerIcon = R.drawable.marker_club;
                        break;
                    case "sport":
                        markerIcon = R.drawable.marker_sport;
                        break;
                    case "hotel":
                        markerIcon = R.drawable.marker_hotel;
                        break;
                    case "restaurant":
                        markerIcon = R.drawable.marker_restaurant;
                        break;
                }


                if (place.longitude >= longitudeBoundaries.min && place.longitude <= longitudeBoundaries.max) {
                    Marker marker = mMap.addMarker(
                            new MarkerOptions()
                                    .position(place.getLatLng())
                                    .title(place.name)
                                    .icon(BitmapDescriptorFactory.fromResource(markerIcon)));

                    marker.setTag(place);
                }


            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeInfo = view.findViewById(R.id.place_info);
        openPlaceButton = view.findViewById(R.id.open_place);
        placeImage = view.findViewById(R.id.place_image);
        placeName = view.findViewById(R.id.place_name);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // ottengo i posti nei paraggi per le categorie selezionate
        Common.Boundaries latitudeBoundaries = Common.getLatitudeBoundaries();

        for (final Category category : Instance.categoryList) {
            // quali categorie di posti voglio vedere? leggo le shared preferences
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            boolean showThisCategory = prefs.getBoolean(category.id, false);

            if (showThisCategory) {
                Instance.db.collection("places")
                        .whereLessThanOrEqualTo("latitude", latitudeBoundaries.max)
                        .whereGreaterThanOrEqualTo("latitude", latitudeBoundaries.min)
                        .whereEqualTo("category", category.id)
                        .get()
                        .addOnCompleteListener(onGetPlacesListener);
            }
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        final Place place = (Place) marker.getTag();

        if (place == null) {
            return false;
        }

        placeInfo.setVisibility(View.VISIBLE);
        StorageReference pathReference = Instance.storage.getReference("places").child(place.id + ".jpg");

        assert getContext() != null;
        Glide.with(getContext()).load(pathReference).into(placeImage);
        placeName.setText(place.name);
        openPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceDetailsActivity.class);
                intent.putExtra("placeId", place.id);
                startActivity(intent);
            }
        });

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng myLatLng = new LatLng(Instance.lastKnownLocation.getLatitude(), Instance.lastKnownLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 13));

        // marker con la mia posizione
        mMap.addMarker(
                new MarkerOptions()
                        .position(myLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gps)));


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        placeInfo.setVisibility(View.GONE);
    }
}
