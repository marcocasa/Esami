package it.uniba.di.sms1819.tourapp.ViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import it.uniba.di.sms1819.tourapp.Models.SavedPlacesModel;
import it.uniba.di.sms1819.tourapp.R;

public class SmallPlaceCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView image;
    private TextView name;
    private Context context;
    private final FirebaseStorage storage = FirebaseStorage.getInstance();
    private final StorageReference storageRef = storage.getReference();
    private OnItemClickListener mOnItemClickListenerRef;

    public interface OnItemClickListener
    {
        void onItemClicked(int position);
    }

    public SmallPlaceCardViewHolder(@NonNull View itemView, Context ctx) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        context = ctx;
        itemView.setOnClickListener(this);
    }

    public void bind(SavedPlacesModel place) {
        StorageReference pathReference = storageRef.child(String.format("places/%s.jpg", place.id));
        Glide.with(context).load(pathReference).into(image);
        name.setText(place.name);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.mOnItemClickListenerRef = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListenerRef.onItemClicked(getLayoutPosition());
    }
}
