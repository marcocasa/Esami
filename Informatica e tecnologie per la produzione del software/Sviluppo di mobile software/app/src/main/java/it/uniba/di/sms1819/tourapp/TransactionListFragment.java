package it.uniba.di.sms1819.tourapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Adapters.SpacingItemDecorator;
import it.uniba.di.sms1819.tourapp.Adapters.TransactionCardAdapter;
import it.uniba.di.sms1819.tourapp.Models.Transaction;


public class TransactionListFragment extends Fragment {

    TextView noTransactionsTextView;
    RecyclerView recyclerView;
    TransactionCardAdapter adapter;
    ArrayList<Transaction> transactions = new ArrayList<>();
    private OnCompleteListener<QuerySnapshot> onGetTransactions = new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (!task.isSuccessful()) {
                Common.logError(task.getException().toString());
                return;
            }

            // nessuna transazione trovata
            if (task.getResult().size() == 0) {
                recyclerView.setVisibility(View.GONE);
                noTransactionsTextView.setVisibility(View.VISIBLE);
            }

            for (QueryDocumentSnapshot documentReference : task.getResult()) {
                transactions.add(documentReference.toObject(Transaction.class));
            }

            adapter = new TransactionCardAdapter(transactions);
            recyclerView.addItemDecoration(new SpacingItemDecorator(getContext(), R.dimen.space_5));
            recyclerView.setAdapter(adapter);

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle_view);
        noTransactionsTextView = view.findViewById(R.id.no_transactions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // carico le transazioni solo se non sono già stati caricate
        if (transactions.size() == 0) {
            Instance.db.collection("users")
                    .document(Instance.user.getUid())
                    .collection("transactions")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(20)
                    .get()
                    .addOnCompleteListener(onGetTransactions);
        } else {
            adapter = new TransactionCardAdapter(transactions);
            recyclerView.addItemDecoration(new SpacingItemDecorator(getContext(), R.dimen.space_5));
            recyclerView.setAdapter(adapter);
        }

    }

}


