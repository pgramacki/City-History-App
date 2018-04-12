package com.example.piotrgramacki.dlaewy;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button buttonMap;
    private Button buttonQr;
    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();
        readDataFromResources();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.READ_QR) {
            if (resultCode == RESULT_OK) {
                String content = data.getStringExtra(AppConstants.RESULT_QR);
                int index = getIndex(content);
                if (index != -1 && index < places.size()) {
                    Intent intent = new Intent(this, InfoActivity.class);
                    intent.putExtra(AppConstants.PLACE_NAME, places.get(index).getName());
                    intent.putExtra(AppConstants.PLACE_DESCR, places.get(index).getDescription());
                    intent.putExtra(AppConstants.PLACE_HISTORY, places.get(index).getHistory());
                    //TODO - add passing pictures to info activity
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(), getText(R.string.wrong_qr), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void findViews() {
        buttonMap = findViewById(R.id.button_map);
        buttonQr = findViewById(R.id.button_qr);
    }

    private void setListeners() {
        final Intent intentMap = new Intent(this, MapsActivity.class);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMap);
            }
        });


        final Intent intentQR = new Intent(this, QrCodeScannerActivity.class);
        buttonQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intentQR, AppConstants.READ_QR);
            }
        });
    }

    private void readDataFromResources() {
        Resources res = getResources();
        PlacesManager.setPlaces(
                res.getStringArray(R.array.latitudes),
                res.getStringArray(R.array.longitudes),
                res.getStringArray(R.array.names),
                res.getStringArray(R.array.descriptions),
                res.getString(R.string.lots_of_text)
        );
        places = PlacesManager.getPlaces();
    }

    private int getIndex(String content) {
        int index;
        try {
            index = Integer.parseInt(content);
        } catch (NumberFormatException e) {
            index = -1;
        }

        return index;
    }
}
