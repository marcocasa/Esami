package it.uniba.di.sms1819.tourapp.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

import it.uniba.di.sms1819.tourapp.Instance;
import it.uniba.di.sms1819.tourapp.Models.Place;
import it.uniba.di.sms1819.tourapp.R;

public class PlaceCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView nameTextView;
    public ImageView imageView;
    private OnPlaceClickListener mOnItemClickListenerRef;
    private Context context;

    public PlaceCardViewHolder(View v) {
        super(v);
        this.context = v.getContext();
        nameTextView = v.findViewById(R.id.name);
        imageView = v.findViewById(R.id.image);
        v.setOnClickListener(this);
    }

    public interface OnPlaceClickListener {
        void onItemClicked(int position);
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListenerRef.onItemClicked(getLayoutPosition());
    }

    public void setOnItemClickListener(final OnPlaceClickListener onItemClickListener) {
        this.mOnItemClickListenerRef = onItemClickListener;
    }

    public void bind(Place place) {
        StorageReference pathReference = Instance.storage.getReference(String.format("places/%s.jpg", place.id));
        Glide.with(context).load(pathReference).into(imageView);
        nameTextView.setText(place.name);
    }
}
