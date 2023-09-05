package it.uniba.di.sms1819.tourapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class Common {

    public static String getAccountUidOrNull() {
        return Instance.user == null ? "" : Instance.user.getUid();
    }

    public static String getDateTime() {
        return DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    }

    public static long getTimestamp() {
        return Calendar.getInstance().getTimeInMillis(); // utc timestamp in millisecondi
    }

    public static void logDebug(String message) {
        Log.d(Constants.TAG, message);
    }

    public static void logError(String message) {
        Log.e(Constants.TAG, message);
    }

    public static String getLocale() {
        return Locale.getDefault().getISO3Language().equals("ita") ? "ita" : "eng";
    }

    public static Boundaries getLatitudeBoundaries() {
        Boundaries boundaries = new Boundaries();

        boundaries.max = Instance.lastKnownLocation.getLatitude() + Math.toDegrees(Constants.DISTANCE / Constants.RADIUS);
        boundaries.min = Instance.lastKnownLocation.getLatitude() - Math.toDegrees(Constants.DISTANCE / Constants.RADIUS);

        return boundaries;
    }

    public static Boundaries getLongitudeBoundaries() {
        Boundaries boundaries = new Boundaries();

        boundaries.max = Instance.lastKnownLocation.getLongitude() + Math.toDegrees(Constants.DISTANCE / Constants.RADIUS) / Math.cos(Math.toRadians(Instance.lastKnownLocation.getLatitude()));
        boundaries.min = Instance.lastKnownLocation.getLongitude() - Math.toDegrees(Constants.DISTANCE / Constants.RADIUS) / Math.cos(Math.toRadians(Instance.lastKnownLocation.getLatitude()));

        return boundaries;
    }

    public static boolean canAccessGPS(LocationManager locationManager, Context context) {
        // restituisce true se è attivo il gps in alta precisione e se è stato dato il permesso per accedere alla posizione
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static class Boundaries {
        double min;
        double max;
    }

}
