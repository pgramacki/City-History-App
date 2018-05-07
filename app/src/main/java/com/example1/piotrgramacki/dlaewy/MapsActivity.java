package com.example1.piotrgramacki.dlaewy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private ArrayList<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng zg = new LatLng(51.937410, 15.511228);
        map.moveCamera(CameraUpdateFactory.newLatLng(zg));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(zg, 12));

        setMarkers();
        setListeners();
    }

    private void setListeners() {
        final Intent intent = new Intent(this, InfoActivity.class);
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0; i < markers.size(); i++) {
                    if (markers.get(i).equals(marker)) {
                        intent.putExtra(AppConstants.PLACE_ID, i);
                        startActivity(intent);
                        return false;
                    }
                }
                return false;
            }
        });
    }

    private void setMarkers() {
        ArrayList<Place> places = PlacesManager.getPlaces();

        for (Place p :
                places) {
            markers.add(map.addMarker(new MarkerOptions().position(p.getCoordinates()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
        }
    }
}
