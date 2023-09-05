package it.uniba.di.sms1819.tourapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.uniba.di.sms1819.tourapp.Models.Place;
import it.uniba.di.sms1819.tourapp.R;
import it.uniba.di.sms1819.tourapp.ViewHolders.PlaceCardViewHolder;

public class PlaceCardAdapter extends RecyclerView.Adapter implements PlaceCardViewHolder.OnPlaceClickListener {

    private List<Place> placeList;
    private OnPlaceClicked mOnCategoryClickRef;

    public PlaceCardAdapter(List<Place> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_place, viewGroup, false);
        return new PlaceCardViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PlaceCardViewHolder v = (PlaceCardViewHolder) viewHolder;
        v.setOnItemClickListener(this);
        v.bind(placeList.get(i));
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public void setOnPlaceClickListener(final OnPlaceClicked onCategoryListener) {
        this.mOnCategoryClickRef = onCategoryListener;
    }

    @Override
    public void onItemClicked(int position) {
        mOnCategoryClickRef.onPlaceClicked(placeList.get(position), position);
    }

    public interface OnPlaceClicked {
        void onPlaceClicked(Place place, int position);
    }
}
