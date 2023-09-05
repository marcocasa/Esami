package it.uniba.di.sms1819.tourapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Adapters.CommentAdapter;
import it.uniba.di.sms1819.tourapp.Models.Comment;
import it.uniba.di.sms1819.tourapp.Models.Place;
import it.uniba.di.sms1819.tourapp.SQLite.DBQuery;

public class PlaceDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, NfcAdapter.OnNdefPushCompleteCallback {

    GoogleMap mMap;
    TextView placeNameTextView, descriptionTextView, ticketDescriptionTextView, noCommentsMessageTextView;
    ImageView coverImageView;
    FloatingActionButton heartFab;
    Button buyWithNfcButton, sendCommentButton;
    EditText commentEditText;
    RatingBar commentRatingBar, avgRating;
    NfcManager mNfcManager;
    Place place;
    CardView buyTicketCardView;
    String placeId;
    DBQuery dbQuery;
    NfcAdapter mNfcAdapter;
    SupportMapFragment mapFragment;
    int numStars = 0;
    RecyclerView commentRecycleView;
    ArrayList<Comment> comments = new ArrayList<>();
    CommentAdapter commentsAdapter;
    LinearLayout addCommentLayout;
    Button showAllCommentsButton;
    private Boolean showAllComments = false;

    // animazione commenti
    LayoutAnimationController animationController;


    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Constants.NFC_MESSAGE_SENT) {
                Toast.makeText(PlaceDetailsActivity.this, getString(R.string.can_enter), Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(PlaceDetailsActivity.this, getString(R.string.cannot_enter), Toast.LENGTH_LONG).show();
            }
        }
    };


    private OnCompleteListener<DocumentSnapshot> onGetPlace = new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (!task.isSuccessful()) {
                Common.logError("get fallito: " + task.getException());
                return;
            }

            DocumentSnapshot document = task.getResult();

            // mappo il risultato nell'oggetto place
            place = document.toObject(Place.class);

            // imposta il nome del punto di interesse nella view
            placeNameTextView.setText(place.name);

            // descrizione in base alla lingua e nascondi la descrizione se è vuota
            if (place.description == null) {
                descriptionTextView.setVisibility(View.GONE);
            } else {
                String description = place.description.get(Common.getLocale());
                if (description.length() == 0) {
                    descriptionTextView.setVisibility(View.GONE);
                } else {
                    descriptionTextView.setText(description);
                }
            }

            // imposta posizione marker nella mappa
            if (mMap != null) {
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 17));
            }

            // carico immagine nella imageview usando Glide
            StorageReference pathReference = Instance.storage.getReference(String.format("places/%s.jpg", placeId));
            Glide.with(getApplicationContext()).load(pathReference).into(coverImageView);

            // mostra informazioni biglietto se disponibili
            if (place.ticket_price != null) {
                // imposto prezzo biglietto
                buyWithNfcButton.setText(String.format(getString(R.string.str_btn_nfc), place.ticket_price));

                // biglietto
                if (Instance.userInfo != null) {
                    buyWithNfcButton.setEnabled(Instance.userInfo.credit >= place.ticket_price);
                } else {
                    ticketDescriptionTextView.setText(getString(R.string.please_login));
                    buyWithNfcButton.setEnabled(false);
                }
            } else {
                buyTicketCardView.setVisibility(View.GONE);
            }
        }
    };

    private View.OnClickListener onHeartClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // notifico che la lista dei punti di interesse preferiti è stata modificata
            setResult(Constants.SAVED_PLACES_UPDATED);

            /* controllo il punto di interesse è salvato nella lista dei preferiti e lo aggiungo
               o rimuovo di conseguenza. Cambio anche l'immagine del FAB col cuore pieno o vuoto.
             */
            if (!dbQuery.hasPlace(placeId, Common.getAccountUidOrNull())) {
                dbQuery.addPlace(placeId, place.name, Common.getAccountUidOrNull());
                heartFab.setImageResource(R.drawable.round_favorite_white_48);
                Toast.makeText(PlaceDetailsActivity.this, getString(R.string.place_added_pref), Toast.LENGTH_SHORT).show();
            } else {
                heartFab.setImageResource(R.drawable.round_favorite_border_white_48);
                dbQuery.removePlace(placeId, Common.getAccountUidOrNull());
                Toast.makeText(PlaceDetailsActivity.this, getString(R.string.place_elim_by_pref), Toast.LENGTH_SHORT).show();
            }
        }
    };
    private OnCompleteListener<QuerySnapshot> onGetCommentsListener = new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            for (QueryDocumentSnapshot comment : task.getResult()) {
                comments.add(comment.toObject(Comment.class));
            }

            // se i commenti sono meno di 5 nascondi il bottone vedi tutti
            if (comments.size() <= 5) {
                showAllCommentsButton.setVisibility(View.GONE);
                // se non ci sono commenti, mostra il messaggio
                if (comments.size() == 0) {
                    commentRecycleView.setVisibility(View.GONE);
                    noCommentsMessageTextView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!showAllComments) {
                    comments.remove(comments.size()-1);
                }
            }


            // carica commenti nella recycler view
            commentRecycleView.setLayoutAnimation(animationController);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            commentRecycleView.setLayoutManager(layoutManager);
            commentsAdapter = new CommentAdapter(comments);
            commentRecycleView.scheduleLayoutAnimation();
            commentRecycleView.setAdapter(commentsAdapter);

            // calcola rating
            calculateRating();
        }
    };
    private View.OnClickListener sendCommentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String commentText = commentEditText.getText().toString();

            if (commentText.length() < 5) {
                Toast.makeText(getApplicationContext(), String.format(getString(R.string.min_chars), 5), Toast.LENGTH_SHORT).show();
                return;
            }

            if (numStars < 1) {
                Toast.makeText(getApplicationContext(), getString(R.string.select_stars), Toast.LENGTH_SHORT).show();
                return;
            }

            // usa come username solo la parte prima della @ nella email
            String emailBeforeAt = Instance.user.getEmail().split("@")[0];
            Comment comment = new Comment(Instance.user.getUid(), emailBeforeAt, placeId, commentText, numStars);

            Instance.db.collection("comments")
                    .add(comment);

            // se prima non c'erano commenti e adesso ce n'è uno, nascondi il messaggio e mostra la recycler view
            if (comments.size() == 0) {
                noCommentsMessageTextView.setVisibility(View.GONE);
                commentRecycleView.setVisibility(View.VISIBLE);
            }

            // aggiungi commento localmente
            comments.add(0, comment);
            commentsAdapter.notifyDataSetChanged();

            // ricarica valutazione media
            calculateRating();

            // svuota la text edit e disabilita il pulsante
            commentEditText.setText(getString(R.string.cant_comment));
            commentEditText.setEnabled(false);
            sendCommentButton.setEnabled(false);

            Toast.makeText(getApplicationContext(), getString(R.string.comment_sent), Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener btnNfcListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mNfcAdapter = NfcAdapter.getDefaultAdapter(PlaceDetailsActivity.this);
            if (mNfcAdapter == null) {
                Snackbar.make(v, R.string.no_nfc, Snackbar.LENGTH_LONG).show();

            } else {
                if (!mNfcAdapter.isEnabled()) {
                    Snackbar.make(v, R.string.nfc_not_enabled, Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity( new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));

                        }
                    }, 1700);

                } else {
                    Snackbar.make(v, R.string.adviceNfc, Snackbar.LENGTH_LONG).show();
                    // Specifico il NdefMessage da inviare tramite NFC
                    mNfcAdapter.setNdefPushMessage(createNdefMessage(), PlaceDetailsActivity.this);
                    // Specifico il callback per controllare l'invio del messaggio
                    mNfcAdapter.setOnNdefPushCompleteCallback(PlaceDetailsActivity.this, PlaceDetailsActivity.this);
                }
            }
        }
    };
    private RatingBar.OnRatingBarChangeListener ratingBarChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            numStars = (int) rating;
        }
    };
    private View.OnClickListener showAllCommentsButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAllCommentsButton.setVisibility(View.GONE);
            showAllComments = true;
            loadComments(50);
        }
    };

    private void calculateRating() {
        // calcola voto medio
        if (comments.size() != 0) {
            int sum = 0;
            for (Comment comment : comments) {
                sum += comment.rating;
            }
            float starsNumber = (float) sum / comments.size();
            avgRating.setRating(starsNumber);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // quando premo back, termino questa activity per notificare il cambiamento al fragment che l'ha avviata
        finish();
    }

    public NdefMessage createNdefMessage() {
        String userId = Instance.user.getUid();
        String text = userId + "-" + place.ticket_price.toString() + "-" + place.name;
        NdefMessage msg = new NdefMessage(NdefRecord.createMime("text/plain", text.getBytes()));
        return msg;
    }


    @Override
    public void onNdefPushComplete(NfcEvent arg0) {
        mHandler.obtainMessage(Constants.NFC_MESSAGE_SENT).sendToTarget();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        // ottengo la chiave del posto passata a questa activity
        placeId = getIntent().getStringExtra("placeId");
        if (placeId == null) {
            finish();
        }

        // binding con le view
        placeNameTextView = findViewById(R.id.place_name);
        descriptionTextView = findViewById(R.id.description);
        coverImageView = findViewById(R.id.image);
        heartFab = findViewById(R.id.fab);
        heartFab.setOnClickListener(onHeartClickListener);
        buyTicketCardView = findViewById(R.id.card_buy_ticket);
        ticketDescriptionTextView = findViewById(R.id.ticket_description);
        buyWithNfcButton = findViewById(R.id.btn_nfc);
        sendCommentButton = findViewById(R.id.send_comment);
        commentEditText = findViewById(R.id.comment);
        commentRatingBar = findViewById(R.id.comment_rating);
        commentRecycleView = findViewById(R.id.comment_recycle_view);
        addCommentLayout = findViewById(R.id.add_comment_layout);
        showAllCommentsButton = findViewById(R.id.show_all_comments);
        noCommentsMessageTextView = findViewById(R.id.no_comments_message);
        avgRating = findViewById(R.id.avg_rating);

        // animazione commenti
        animationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_fall_down);

        // inizialissa sqlite
        dbQuery = new DBQuery(getApplicationContext());

        // listener pulsante nfc
        buyWithNfcButton.setOnClickListener(btnNfcListener);

        // listener pulsante aggiungi commenti
        sendCommentButton.setOnClickListener(sendCommentListener);

        // listener rating bar
        commentRatingBar.setOnRatingBarChangeListener(ratingBarChangeListener);

        // vedi tutti i commenti listener bottone
        showAllCommentsButton.setOnClickListener(showAllCommentsButtonListener);

        // gli amministratori non possono acquistare i biglietti
        if (Instance.userInfo == null || Instance.userInfo.is_admin) {
            buyWithNfcButton.setEnabled(false);
        }

        // gli utenti non loggati non possono commentare
        if (Instance.user == null) {
            addCommentLayout.setVisibility(View.GONE);
        }

        // carico il fragment della mappa
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // carico le informazioni del punto di interesse
        // ottengo il riferimento al posto
        Instance.db.collection("places").document(placeId).get().addOnCompleteListener(onGetPlace);

        // se questo posto è già nei preferiti, riempio il cuoricino
        if (dbQuery.hasPlace(placeId, Common.getAccountUidOrNull())) {
            heartFab.setImageResource(R.drawable.round_favorite_white_48);
        }

        // carica commenti
        loadComments(6); //ne richiedo 6 per scoprire se ci sono più di 5 commenti

        // mappa
        mapFragment.getMapAsync(this);
    }

    private void loadComments(int numberOfComments) {
        // svuota arraylist commenti
        comments.clear();

        // svuota recycler view
        if (commentsAdapter != null) {
            commentsAdapter.clear();
        }


        Instance.db.collection("comments")
                .whereEqualTo("place_id", placeId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(numberOfComments)
                .get()
                .addOnCompleteListener(onGetCommentsListener);
    }

}

