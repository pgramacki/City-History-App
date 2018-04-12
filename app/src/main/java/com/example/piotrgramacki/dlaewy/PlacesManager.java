package com.example.piotrgramacki.dlaewy;

import java.util.ArrayList;

/**
 * Created by Piotrek on 2018-04-06.
 */

public abstract class PlacesManager {
    private static ArrayList<Place> places;

    //private PlacesManager() {}

    public static void setPlaces(String[] latitudes, String[] longitudes, String[] names, String[] descriptions, String history) {
        //TODO - change into array of histories
        //TODO - change it to private when xml parser is done
        if (places == null) {
            places = new ArrayList<>();

            int size = latitudes.length;
            if (longitudes.length < size)
                size = longitudes.length;
            if (names.length < size)
                size = names.length;
            if (descriptions.length < size)
                size = descriptions.length;

            for (int i = 0; i < size; i++)
                places.add(new Place(
                        Double.parseDouble(latitudes[i]),
                        Double.parseDouble(longitudes[i]),
                        names[i],
                        descriptions[i],
                        history));
        }
    }

    public static ArrayList<Place> getPlaces() {
        return places;
    }
}
