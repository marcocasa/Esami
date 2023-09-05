package it.uniba.di.sms1819.tourapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import it.uniba.di.sms1819.tourapp.Models.Transaction;
import it.uniba.di.sms1819.tourapp.Models.User;

public class AddCreditFragment extends Fragment {

    Button addCreditButton;
    EditText creditEditText;
    EditText userEmailEditText;
    private View.OnClickListener creditButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // validazione form
            if (userEmailEditText.getText().toString().equals("")) {
                userEmailEditText.setError(getString(R.string.emailValid));
                return;
            }
            if (creditEditText.getText().toString().equals("")) {
                creditEditText.setError(getString(R.string.creditValid));
                return;
            }

            final Double addCreditValue = Double.valueOf(creditEditText.getText().toString());

            // ottieni l'istanza dell'utente da aggiornare
            Instance.db.collection("users")
                    .whereEqualTo("email", userEmailEditText.getText().toString())
                    .limit(1)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                            // l'utente è stato trovato?
                            if (documentSnapshots.size() == 0) {
                                Common.logDebug("Nessun utente trovato");
                                Toast.makeText(getContext(), getString(R.string.user_not_found), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            DocumentSnapshot userSnapshop = documentSnapshots.get(0);

                            User newUserInstance = userSnapshop.toObject(User.class);
                            newUserInstance.credit += addCreditValue;

                            DocumentReference userRef = Instance.db.collection("users")
                                    .document(userSnapshop.getId());

                            // sincronizza istanza
                            userRef.set(newUserInstance);

                            // registra stransazione
                            Transaction transaction = new Transaction();
                            transaction.amount = addCreditValue;
                            transaction.type = 1;

                            userRef.collection("transactions").add(transaction);


                            Toast.makeText(getContext(), getString(R.string.credit_updated), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_credit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addCreditButton = view.findViewById(R.id.button);
        creditEditText = view.findViewById(R.id.new_credit);
        userEmailEditText = view.findViewById(R.id.email);

        addCreditButton.setOnClickListener(creditButtonClickListener);
    }


}
