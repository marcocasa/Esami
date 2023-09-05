package it.uniba.di.sms1819.tourapp.SQLite;

import android.provider.BaseColumns;

final class DBContract {
    private DBContract() {
    }

    public static class PlaceEntry implements BaseColumns {
        static final String TABLE_NAME = "saved_places";
        static final String COLUMN_NAME_NAME = "nameTextView";
        static final String COLUMN_NAME_PLACE_ID = "place_id";
        static final String COLUMN_NAME_ACCOUNT_UID = "account_uid";
    }
}
