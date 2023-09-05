package it.uniba.di.sms1819.tourapp.Models;

import it.uniba.di.sms1819.tourapp.Common;

public class Transaction {
    public Double amount;
    public String datetime;
    public int type;
    public String arg;

    public Transaction() {
    }

    // imposta data e ora automaticamente
    public String getDatetime() {
        return Common.getDateTime();
    }

    public long getTimestamp() {
        return Common.getTimestamp();
    }

}
