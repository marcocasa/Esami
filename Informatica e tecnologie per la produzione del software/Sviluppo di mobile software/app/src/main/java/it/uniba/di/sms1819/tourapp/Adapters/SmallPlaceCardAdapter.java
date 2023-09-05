package it.uniba.di.sms1819.tourapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Models.SavedPlacesModel;
import it.uniba.di.sms1819.tourapp.R;
import it.uniba.di.sms1819.tourapp.ViewHolders.SmallPlaceCardViewHolder;

public class SmallPlaceCardAdapter extends RecyclerView.Adapter implements SmallPlaceCardViewHolder.OnItemClickListener {

    private ArrayList<SavedPlacesModel> places;
    private OnPlaceListener mOnPlaceListener;
    private Context ctx;

    @Override
    public void onItemClicked(int position) {
        mOnPlaceListener.onPlaceClicked(places.get(position), position);
    }

    public void setPlaceClickListener(final OnPlaceListener onPlaceListener) {
        mOnPlaceListener = onPlaceListener;
    }


    public SmallPlaceCardAdapter(Context ctx, ArrayList<SavedPlacesModel> places) {
        this.ctx = ctx;
        this.places = places;
    }

    public void updateList(ArrayList<SavedPlacesModel> newPlaceList) {
        places.clear();
        places.addAll(newPlaceList);
        this.notifyDataSetChanged();
    }

    public interface OnPlaceListener {
        void onPlaceClicked(SavedPlacesModel category, int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_small_place, viewGroup, false);
        return new SmallPlaceCardViewHolder(layout, ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SmallPlaceCardViewHolder v = (SmallPlaceCardViewHolder) viewHolder;
        v.setOnItemClickListener(this);
        v.bind(places.get(i));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
