package com.example1.piotrgramacki.dlaewy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonMap;
    private Button buttonQr;
    private Button buttonList;
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
                if (index != -1 && index < places.size() && index >= 0) {
                    Intent intent = new Intent(this, InfoActivity.class);
                    intent.putExtra(AppConstants.PLACE_ID, index);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), getText(R.string.wrong_qr), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void findViews() {
        buttonMap = findViewById(R.id.button_map);
        buttonQr = findViewById(R.id.button_qr);
        buttonList = findViewById(R.id.button_list);
    }

    private void setListeners() {
        final Intent intentMap = new Intent(this, MapsActivity.class);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMap);
            }
        });

        final Intent intentList = new Intent(this, ListActivity.class);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentList);
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
        try {
            PlacesManager.readFromResources(res);
            places = PlacesManager.getPlaces();
        } catch (Exception e) {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.data_error),
                    Toast.LENGTH_LONG).show();
        }
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
