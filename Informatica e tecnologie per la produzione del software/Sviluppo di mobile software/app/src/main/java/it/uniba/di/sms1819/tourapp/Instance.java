package it.uniba.di.sms1819.tourapp;

import android.location.Location;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Models.Category;
import it.uniba.di.sms1819.tourapp.Models.User;

public final class Instance {
    // firebase
    public final static FirebaseStorage storage = FirebaseStorage.getInstance();

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // cache
    static final ArrayList<Category> categoryList = new ArrayList<>();
    static User userInfo = null;
    static Location lastKnownLocation;
}
