package it.uniba.di.sms1819.tourapp.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import it.uniba.di.sms1819.tourapp.Models.SavedPlacesModel;

public class DBQuery {

    private final static String SELECT_PLACE_QUERY = DBContract.PlaceEntry.COLUMN_NAME_PLACE_ID + "=? AND " + DBContract.PlaceEntry.COLUMN_NAME_ACCOUNT_UID + "=?";
    private final static String MY_PLACES_QUERY = DBContract.PlaceEntry.COLUMN_NAME_ACCOUNT_UID + "=?";
    private DBHelper mDbHelper;

    public DBQuery(Context context) {
        mDbHelper = new DBHelper(context);
    }

    public void addPlace(String id, String name, String accountUID) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        final ContentValues values = new ContentValues();
        values.put(DBContract.PlaceEntry.COLUMN_NAME_NAME, name);
        values.put(DBContract.PlaceEntry.COLUMN_NAME_PLACE_ID, id);
        values.put(DBContract.PlaceEntry.COLUMN_NAME_ACCOUNT_UID, accountUID);

        db.insert(DBContract.PlaceEntry.TABLE_NAME, null, values);
    }

    public boolean hasPlace(String id, String accountUID) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db,
                DBContract.PlaceEntry.TABLE_NAME,
                SELECT_PLACE_QUERY,
                new String[]{id, accountUID}) > 0;
    }

    public void removePlace(String id, String accountUID) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(DBContract.PlaceEntry.TABLE_NAME,
                SELECT_PLACE_QUERY,
                new String[]{id, accountUID});
    }

    public ArrayList<SavedPlacesModel> getPlaces(String accountUID) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // filtro
        String selection = MY_PLACES_QUERY;
        String[] selectionArgs = {accountUID};

        final Cursor cursor = db.query(DBContract.PlaceEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        ArrayList<SavedPlacesModel> places = new ArrayList<>();

        int placeIdId = cursor.getColumnIndex(DBContract.PlaceEntry.COLUMN_NAME_PLACE_ID);
        int placeNameId = cursor.getColumnIndex(DBContract.PlaceEntry.COLUMN_NAME_NAME);

        while (cursor.moveToNext()) {
            String placeId = cursor.getString(placeIdId);
            String placeName = cursor.getString(placeNameId);

            places.add(new SavedPlacesModel(placeId, placeName));
        }
        cursor.close();

        return places;
    }

}
