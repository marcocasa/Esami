package it.uniba.di.sms1819.tourapp;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import it.uniba.di.sms1819.tourapp.Models.Transaction;
import it.uniba.di.sms1819.tourapp.Models.User;

public class ReceiveCreditActivity extends AppCompatActivity {
    String userId;
    Double price;
    String placeName;
    DocumentReference targetUserReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_credit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public void onResume() {
        super.onResume();
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter == null) {
            Toast.makeText(this, R.string.no_nfc, Toast.LENGTH_LONG).show();

        } else {
            if (!mNfcAdapter.isEnabled()) {
                Toast.makeText(this, R.string.nfc_not_enabled, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.adviceNfc, Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            processIntent(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private OnCompleteListener<DocumentSnapshot> updateTargetUser = new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            User user = task.getResult().toObject(User.class);

            user.credit -= price;

            targetUserReference.set(user);

            // aggiungi transazione
            Transaction transaction = new Transaction();
            transaction.type = 2;
            transaction.arg = placeName;
            transaction.amount = -price;

            targetUserReference.collection("transactions").add(transaction);

            // aggiungi transazione per l'amministratore
            Instance.userInfo.credit += price;

            DocumentReference adminReference = Instance.db.collection("users")
                    .document(Instance.user.getUid());


            adminReference.set(Instance.userInfo);

            // aggiungi transazione
            transaction.type = 3;
            transaction.arg = placeName;
            transaction.amount = price;

            adminReference.collection("transactions").add(transaction);

            Toast.makeText(getApplicationContext(), getString(R.string.credit_added), Toast.LENGTH_SHORT).show();
        }
    };


    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String text = new String(msg.getRecords()[0].getPayload());

        final String[] parts = text.split("-");
        userId = parts[0];
        price = Double.parseDouble(parts[1]);
        placeName = parts[2];

        // ottieni istanza dell'utente a cui rimuovere il credito
        targetUserReference = Instance.db.collection("users")
                .document(userId);

        targetUserReference.get().addOnCompleteListener(updateTargetUser);

    }
}
