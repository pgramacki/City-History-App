package com.example1.piotrgramacki.dlaewy;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Piotrek on 2018-04-06.
 */

public abstract class PlacesManager {
    private static ArrayList<Place> places = null;

    public static ArrayList<Place> getPlaces() {
        return places;
    }

    public static void readFromResources(Resources res) throws IOException, XmlPullParserException {
        if (places == null) {
            readFromXml(res.getXml(R.xml.data));
            setPhotos(res);
        }
    }

    private static void readFromXml(XmlResourceParser xrp) throws IOException, XmlPullParserException {
        DataXmlParser parser = new DataXmlParser(xrp);
        places = parser.parse();
    }

    private static void setPhotos(Resources res) {
        TypedArray arrays = res.obtainTypedArray(R.array.photos);
        int numOfArrays = arrays.length();

        for (int i = 0; i < numOfArrays; i++) {
            int id = arrays.getResourceId(i, 0);
            TypedArray photos = res.obtainTypedArray(id);
            int numOfPhotos = photos.length();
            int[] photosIDs = new int[numOfPhotos];

            for (int j = 0; j < numOfPhotos; j++) {
                int id2 = photos.getResourceId(j, 0);
                photosIDs[j] = id2;
            }
            places.get(i).setImages(photosIDs);
            photos.recycle();
        }

        arrays.recycle();
    }
}
