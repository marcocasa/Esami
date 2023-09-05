package it.uniba.di.sms1819.tourapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.uniba.di.sms1819.tourapp.Models.Comment;
import it.uniba.di.sms1819.tourapp.R;
import it.uniba.di.sms1819.tourapp.ViewHolders.CommentViewHolder;

public class CommentAdapter extends RecyclerView.Adapter {

    private List<Comment> comments = new ArrayList<>();

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment, viewGroup, false);
        return new CommentViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CommentViewHolder v = (CommentViewHolder) viewHolder;
        v.bind(comments.get(i));
    }

    public void clear() {
        comments.clear();
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
