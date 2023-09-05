package it.uniba.di.sms1819.tourapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Adapters.PlaceCardAdapter;
import it.uniba.di.sms1819.tourapp.Adapters.SpacingItemDecorator;
import it.uniba.di.sms1819.tourapp.Models.Place;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    TextView noPlaceFoundTextView;
    String categoryId;
    ArrayList<Place> places = new ArrayList<>();
    PlaceCardAdapter adapter;
    LocationManager locationManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    private OnCompleteListener<QuerySnapshot> onGetPlacesListener = new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (!task.isSuccessful()) {
                Common.logError(task.getException().toString());
                return;
            }

            Common.Boundaries longitudeBoundaries = null;

            // se ho accesso alla posizione filtro adesso i posti per longitudine
            if (Instance.lastKnownLocation != null && Common.canAccessGPS(locationManager, getContext())) {
                longitudeBoundaries = Common.getLongitudeBoundaries();
            }

            for (QueryDocumentSnapshot document : task.getResult()) {
                Place place = document.toObject(Place.class);
                place.id = document.getId();

                if (Instance.lastKnownLocation != null) {
                    if (place.longitude >= longitudeBoundaries.min && place.longitude <= longitudeBoundaries.max) {
                        places.add(place);
                    }
                } else {
                    places.add(place);
                }
            }


            Common.logDebug("Posti trovati:" + places.size());

            if (places.size() == 0) {
                noPlaceFoundTextView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                return;
            }

            showPlaceCards();
        }
    };

    private void showPlaceCards() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaceCardAdapter(places);
        recyclerView.setAdapter(adapter);

        // spaziatura tra le card visualizzate
        recyclerView.addItemDecoration(new SpacingItemDecorator(getContext(), R.dimen.space_5));

        // associo l'azione di apertura della categoria quando clicco su una card
        adapter.setOnPlaceClickListener(new PlaceCardAdapter.OnPlaceClicked() {
            @Override
            public void onPlaceClicked(Place place, int position) {
                Intent intent = new Intent(getContext(), PlaceDetailsActivity.class);
                intent.putExtra("placeId", place.id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // elementi ui
        recyclerView = view.findViewById(R.id.recyclerView);
        noPlaceFoundTextView = view.findViewById(R.id.no_place_message);

        // location manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }


    @Override
    public void onStart() {
        super.onStart();
        // carico i punti di interesse solo se non sono già stati caricati
        if (places.size() == 0) {
            // ottengo l'id della categoria
            categoryId = getArguments().getString("category");
            Common.logDebug("Category ID: " + categoryId);

        /* ottengo i posti presenti nella categoria nel raggio di km preimpostato.
        si può fare il where su un solo paramentro, quindi per ora lo facciamo sulla latitudine e lo
        faremo sulla longitudine localmente con un ciclo for.
        */

            Query query = Instance.db.collection("places")
                    .whereEqualTo("category", categoryId);

            // se ho accesso alla posizione eseguo il filtraggio sulla latitudine
            if (Instance.lastKnownLocation != null) {
                Common.Boundaries latitudeBoundaries = Common.getLatitudeBoundaries();
                query.whereLessThanOrEqualTo("latitude", latitudeBoundaries.max)
                        .whereGreaterThanOrEqualTo("latitude", latitudeBoundaries.min);
            }

            query.get().addOnCompleteListener(onGetPlacesListener);
        } else {
            // mostro direttamente i punti di interesse se sono già stati caricati
            showPlaceCards();
        }
    }
}
