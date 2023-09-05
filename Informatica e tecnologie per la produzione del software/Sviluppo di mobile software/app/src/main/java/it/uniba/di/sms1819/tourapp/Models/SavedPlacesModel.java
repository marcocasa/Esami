package it.uniba.di.sms1819.tourapp.Models;

/*
    Questa classe è utilizzata solo per gli oggetti che devono contenere i risultati della query
    getPlaces dal database SQLite.
 */
public class SavedPlacesModel {

    public String id;
    public String name;

    public SavedPlacesModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
