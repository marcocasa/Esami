package it.uniba.di.sms1819.tourapp.Models;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.Exclude;

import java.util.HashMap;

public class Place {
    @Exclude
    public String id;

    public String name;
    public Double latitude;
    public Double longitude;
    public HashMap<String, String> description;
    public String category;
    public Double ticket_price;

    public Place() {
    }

    @Exclude
    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

}
