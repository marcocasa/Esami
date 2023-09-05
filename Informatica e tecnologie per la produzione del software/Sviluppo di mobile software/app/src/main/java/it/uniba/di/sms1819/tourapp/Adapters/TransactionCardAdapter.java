package it.uniba.di.sms1819.tourapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Models.Transaction;
import it.uniba.di.sms1819.tourapp.R;
import it.uniba.di.sms1819.tourapp.ViewHolders.TransactionCardViewHolder;

public class TransactionCardAdapter extends RecyclerView.Adapter {

    private ArrayList<Transaction> transactions;

    public TransactionCardAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_transaction, viewGroup, false);
        return new TransactionCardViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TransactionCardViewHolder v = (TransactionCardViewHolder) viewHolder;
        v.bind(transactions.get(i));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}
