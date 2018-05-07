package com.example1.piotrgramacki.dlaewy;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Piotrek on 2018-04-12.
 */

public class DataXmlParser {
    private String ns = null;
    private XmlPullParser parser;
    private ArrayList<Place> places;

    public DataXmlParser(XmlPullParser parser) {
        this.parser = parser;
        places = new ArrayList<>();
    }

    public ArrayList<Place> parse() throws XmlPullParserException, IOException {
        parser.next();
        parser.next();
        readRoot();
        return places;
    }

    private void readRoot() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_ROOT);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String name = parser.getName();

                if (name.equals(AppConstants.XML_PLACE)) {
                    places.add(readPlace());
                } else {
                    skip();
                }
            }
        }
    }

    private Place readPlace() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE);
        String name = null;
        String description[] = new String[AppConstants.DESCR_SIZE];
        String history = null;
        double latitude = 0.0;
        double longitude = 0.0;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String tag = parser.getName();
                switch (tag) {
                    case AppConstants.XML_PLACE_NAME:
                        name = readName();
                        break;
                    case AppConstants.XML_PLACE_DESCR:
                        description = readDescription();
                        break;
                    case AppConstants.XML_PLACE_HISTORY:
                        history = readHistory();
                        break;
                    case AppConstants.XML_PLACE_LATITUDE:
                        latitude = readLatitude();
                        break;
                    case AppConstants.XML_PLACE_LONGITUDE:
                        longitude = readLongitude();
                        break;
                    default:
                        skip();
                        break;
                }
            }
        }

        return new Place(latitude, longitude, name, description, history);
    }

    private String readName() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_NAME);
        String name = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_NAME);
        return name;
    }

    private String[] readDescription() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_DESCR);
        String[] description = new String[AppConstants.DESCR_SIZE];

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                String tag = parser.getName();
                switch (tag) {
                    case AppConstants.XML_PLACE_DESCR_ADRESS:
                        description[AppConstants.DESCR_ADDRESS] = readAddress();
                        break;
                    case AppConstants.XML_PLACE_DESCR_YEAR:
                        description[AppConstants.DESCR_YEAR] = readYear();
                        break;
                    case AppConstants.XML_PLACE_DESCR_ARCH:
                        description[AppConstants.DESCR_ARCH] = readArchitect();
                        break;
                    case AppConstants.XML_PLACE_DESCE_THEN:
                        description[AppConstants.DESCR_THEN] = readThen();
                        break;
                    case AppConstants.XML_PLACE_DESCR_NOW:
                        description[AppConstants.DESCR_NOW] = readNow();
                        break;
                    default:
                        skip();
                        break;
                }
            }
        }

        return description;
    }

    private String readAddress() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_DESCR_ADRESS);
        String address = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_DESCR_ADRESS);
        return address;
    }

    private String readYear() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_DESCR_YEAR);
        String address = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_DESCR_YEAR);
        return address;
    }

    private String readArchitect() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_DESCR_ARCH);
        String address = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_DESCR_ARCH);
        return address;
    }

    private String readThen() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_DESCE_THEN);
        String address = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_DESCE_THEN);
        return address;
    }

    private String readNow() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_DESCR_NOW);
        String address = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_DESCR_NOW);
        return address;
    }

    private String readHistory() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_HISTORY);
        String history = readText();
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_HISTORY);
        return history;
    }

    private double readLatitude() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_LATITUDE);
        double latitude;
        try {
            latitude = Double.parseDouble(readText());
        } catch (NumberFormatException e) {
            latitude = 0.0;
        }
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_LATITUDE);
        return latitude;
    }

    private double readLongitude() throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, AppConstants.XML_PLACE_LONGITUDE);
        double longitude;
        try {
            longitude = Double.parseDouble(readText());
        } catch (NumberFormatException e) {
            longitude = 0.0;
        }
        parser.require(XmlPullParser.END_TAG, ns, AppConstants.XML_PLACE_LONGITUDE);
        return longitude;
    }

    private void skip() throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private String readText() throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
