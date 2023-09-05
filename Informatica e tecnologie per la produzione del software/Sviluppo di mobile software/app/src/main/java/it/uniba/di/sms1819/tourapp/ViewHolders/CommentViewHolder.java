package it.uniba.di.sms1819.tourapp.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import it.uniba.di.sms1819.tourapp.Models.Comment;
import it.uniba.di.sms1819.tourapp.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private TextView usernameTextView, textTextView;
    private RatingBar ratingBar;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        usernameTextView = itemView.findViewById(R.id.username);
        textTextView = itemView.findViewById(R.id.text);
        ratingBar = itemView.findViewById(R.id.rating);
    }

    public void bind(Comment comment) {
        usernameTextView.setText(comment.username);
        textTextView.setText(comment.text);
        ratingBar.setRating(comment.rating);
    }
}
