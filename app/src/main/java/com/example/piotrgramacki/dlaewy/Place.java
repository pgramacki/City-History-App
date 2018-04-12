package com.example.piotrgramacki.dlaewy;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Piotrek on 2018-04-05.
 */

public class Place {
    private LatLng coordinates;
    private String name;
    private String description;
    private String history;

    public Place(double latitude, double longitude, String name, String description, String history) {
        this.coordinates = new LatLng(latitude, longitude);
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHistory() {
        return history;
    }
}
