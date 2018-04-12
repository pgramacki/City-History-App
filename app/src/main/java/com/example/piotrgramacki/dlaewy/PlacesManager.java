package com.example.piotrgramacki.dlaewy;

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

    public static void readFromXml(XmlResourceParser xrp) throws IOException, XmlPullParserException {
        if (places == null) {
            DataXmlParser parser = new DataXmlParser(xrp);
            places = parser.parse();
        }
    }
}
