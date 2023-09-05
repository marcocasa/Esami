package it.uniba.di.sms1819.tourapp;

final class Constants {
    // tag per il debug
    final static String TAG = "TourApp";

    final static String BACKEND_URL = "http://localhost:8080/";

    // codici richieste/risposte
    final static int USER_LOGGED_IN = 1;
    final static int REQUEST_LOCATION = 2;
    final static int SAVED_PLACES_UPDATED = 3;
    final static int NFC_MESSAGE_SENT = 4;

    final static double DISTANCE = 20.0; // km
    final static double RADIUS = 6371.009;

}
