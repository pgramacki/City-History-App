package com.example.piotrgramacki.dlaewy;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Piotrek on 2018-04-05.
 */

public class Place {
    private LatLng coordinates;
    private String name;
    private String[] description;
    private String history;
    private int[] images;

    public Place(double latitude, double longitude, String name, String[] description, String history) {
        this.coordinates = new LatLng(latitude, longitude);
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return description[AppConstants.DESCR_ADDRESS];
    }

    public String getYear() {
        return description[AppConstants.DESCR_YEAR];
    }

    public String getArchitect() {
        return description[AppConstants.DESCR_ARCH];
    }

    public String getThen() {
        return description[AppConstants.DESCR_THEN];
    }

    public String getNow() {
        return description[AppConstants.DESCR_NOW];
    }

    public String getHistory() {
        return history;
    }

    public int[] getImages() {
        return images;
    }

    public int getOldPhoto() {
        return images[0];
    }

    public int getNewPhoto() {
        return images[1];
    }

}

//TODO - opisy do par zdjec, ew copyright pod zdjeciem, ew opis do rozbudowania, znalsc
