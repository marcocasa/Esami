package it.uniba.di.sms1819.tourapp.Models;

import it.uniba.di.sms1819.tourapp.Common;

public class Comment {
    public String user_uid;
    public String place_id;
    public String text;
    public String username;
    public int rating;

    public Comment() {
    }

    public Comment(String user_uid, String username, String place_id, String text, int rating) {
        this.user_uid = user_uid;
        this.place_id = place_id;
        this.text = text;
        this.rating = rating;
        this.username = username;
    }

    public String getDatetime() {
        return Common.getDateTime();
    }

    public long getTimestamp() {
        return Common.getTimestamp();
    }

}
