package it.uniba.di.sms1819.tourapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.uniba.di.sms1819.tourapp.Models.Category;
import it.uniba.di.sms1819.tourapp.R;
import it.uniba.di.sms1819.tourapp.ViewHolders.CategoryCardViewHolder;

public class CategoryCardAdapter extends RecyclerView.Adapter implements CategoryCardViewHolder.OnItemClickListener {

    private List<Category> categoryList;
    private OnCategoryListener mOnCategoryClickRef;

    public CategoryCardAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public interface OnCategoryListener
    {
        void onCategoryClicked(Category category, int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_category, viewGroup, false);
        return new CategoryCardViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CategoryCardViewHolder v = (CategoryCardViewHolder) viewHolder;
        v.setOnItemClickListener(this);
        v.bind(categoryList.get(i));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setOnCategoryClickListener(final OnCategoryListener onCategoryListener) {
        this.mOnCategoryClickRef = onCategoryListener;
    }

    @Override
    public void onItemClicked(int position) {
        mOnCategoryClickRef.onCategoryClicked(categoryList.get(position), position);
    }
}
