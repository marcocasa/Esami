package it.uniba.di.sms1819.tourapp.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TourApp.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + DBContract.PlaceEntry.TABLE_NAME + "(" +
            DBContract.PlaceEntry.COLUMN_NAME_PLACE_ID + " text not null," +
            DBContract.PlaceEntry.COLUMN_NAME_NAME + " text not null," +
            DBContract.PlaceEntry.COLUMN_NAME_ACCOUNT_UID + " text null" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.PlaceEntry.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
