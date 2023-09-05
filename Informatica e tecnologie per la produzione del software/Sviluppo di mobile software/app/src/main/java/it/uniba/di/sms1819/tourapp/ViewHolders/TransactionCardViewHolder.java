package it.uniba.di.sms1819.tourapp.ViewHolders;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import it.uniba.di.sms1819.tourapp.Models.Transaction;
import it.uniba.di.sms1819.tourapp.R;


public class TransactionCardViewHolder extends RecyclerView.ViewHolder {

    static final int RED = Color.parseColor("#FF0000");
    static final int GREEN = Color.parseColor("#008000");
    private TextView reasonTextView, amountTextView, dateTimeTextView;
    private ImageView iconImageView;
    private Context context;

    public TransactionCardViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        reasonTextView = itemView.findViewById(R.id.reason);
        amountTextView = itemView.findViewById(R.id.amount);
        dateTimeTextView = itemView.findViewById(R.id.datetime);
        iconImageView = itemView.findViewById(R.id.icon);
    }

    public void bind(Transaction transaction) {
        switch (transaction.type) {
            case 1:
                // credito ricevuto (utente)
                reasonTextView.setText(context.getString(R.string.received_credit));
                iconImageView.setImageDrawable(context.getDrawable(R.drawable.cash));
                break;
            case 2:
                // acquisto biglietto (utente)
                reasonTextView.setText(transaction.arg);
                iconImageView.setImageDrawable(context.getDrawable(R.drawable.ticket));
                break;
            case 3:
                // ricezione soldi da acquisto biglietto (amministratore)
                reasonTextView.setText(String.format(context.getString(R.string.ticket_purchased), transaction.arg));
                iconImageView.setImageDrawable(context.getDrawable(R.drawable.ticket));
                break;
        }

        amountTextView.setTextColor(transaction.amount > 0 ? GREEN : RED);
        amountTextView.setText(String.format("%.2f€", transaction.amount));
        dateTimeTextView.setText(transaction.datetime);
    }
}
