package it.uniba.di.sms1819.tourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Adapters.SmallPlaceCardAdapter;
import it.uniba.di.sms1819.tourapp.Adapters.SpacingItemDecorator;
import it.uniba.di.sms1819.tourapp.Models.SavedPlacesModel;
import it.uniba.di.sms1819.tourapp.SQLite.DBQuery;

public class SavedPlacesFragment extends Fragment {

    DBQuery dbQuery;
    RecyclerView recyclerView;
    SmallPlaceCardAdapter adapter;
    ArrayList<SavedPlacesModel> savedPlaces;
    TextView noPlaceMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_places, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noPlaceMessage = view.findViewById(R.id.no_place_message);
        dbQuery = new DBQuery(getContext());

        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        loadPlaces();

        adapter = new SmallPlaceCardAdapter(getContext(), savedPlaces);
        recyclerView.addItemDecoration(new SpacingItemDecorator(getContext(), R.dimen.space_5));
        recyclerView.setAdapter(adapter);

        // apri scheda punto di interesse
        adapter.setPlaceClickListener(new SmallPlaceCardAdapter.OnPlaceListener() {
            @Override
            public void onPlaceClicked(SavedPlacesModel category, int position) {
                Intent intent = new Intent(getContext(), PlaceDetailsActivity.class);
                intent.putExtra("placeId", savedPlaces.get(position).id);

                startActivityForResult(intent, Constants.SAVED_PLACES_UPDATED);
            }
        });
    }

    public void loadPlaces() {
        savedPlaces = dbQuery.getPlaces(Common.getAccountUidOrNull());

        Log.d(Constants.TAG, String.format("Trovati %d posti aggiunti ai preferiti", savedPlaces.size()));

        if (savedPlaces.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noPlaceMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.SAVED_PLACES_UPDATED) {
            Log.d(Constants.TAG, "Lista posti preferiti aggiornata!");
            loadPlaces();
            adapter.updateList(this.savedPlaces);
        }
    }
}
