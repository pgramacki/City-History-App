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
    private int[] images = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
    private int oldPhoto;
    private int newPhoto;

    public Place(double latitude, double longitude, String name, String[] description, String history) {
        this.coordinates = new LatLng(latitude, longitude);
        this.name = name;
        this.description = description;
        this.history = history;
        oldPhoto = R.drawable.sample_0;
        newPhoto = R.drawable.sample_1;
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
        return oldPhoto;
    }

    public int getNewPhoto() {
        return newPhoto;
    }
}

//TODO - opisy do par zdjec, ew copyright pod zdjeciem, ew opis do rozbudowania, znalsc
