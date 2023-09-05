package it.uniba.di.sms1819.tourapp.ViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import it.uniba.di.sms1819.tourapp.Common;
import it.uniba.di.sms1819.tourapp.Models.Category;
import it.uniba.di.sms1819.tourapp.R;

public class CategoryCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private TextView nameTextView;
    private Context context;
    private OnItemClickListener mOnItemClickListnerRef;

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public CategoryCardViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.image);
        nameTextView = itemView.findViewById(R.id.name);

        itemView.setOnClickListener(this);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.mOnItemClickListnerRef = onItemClickListener;
    }

    public void bind(Category category) {
        // icona categoria
        int categoryIcon = R.drawable.beach;
        switch (category.id) {
            case "beach":
                categoryIcon = R.drawable.beach;
                break;
            case "monument":
                categoryIcon = R.drawable.monument;
                break;
            case "art":
                categoryIcon = R.drawable.art;
                break;
            case "bar":
                categoryIcon = R.drawable.bar;
                break;
            case "club":
                categoryIcon = R.drawable.club;
                break;
            case "sport":
                categoryIcon = R.drawable.stadium;
                break;
            case "hotel":
                categoryIcon = R.drawable.hotel;
                break;
            case "restaurant":
                categoryIcon = R.drawable.restaurant;
                break;
        }
        imageView.setImageDrawable(context.getDrawable(categoryIcon));
        nameTextView.setText(category.name.get(Common.getLocale()));
    }


    @Override
    public void onClick(View v) {
        mOnItemClickListnerRef.onItemClicked(getLayoutPosition());
    }
}
