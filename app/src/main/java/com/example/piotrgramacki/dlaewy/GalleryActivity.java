package com.example.piotrgramacki.dlaewy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GalleryActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        findViews();
        setData();
    }

    private void findViews() {
        pager = findViewById(R.id.gallery_pager);
        pager.setAdapter(new GalleryPagerAdapter(getSupportFragmentManager(), getIntent().getExtras()));
    }

    private void setData() {
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            int position = extras.getInt(AppConstants.GALLERY_POSITION, 0);
            int placeId = extras.getInt(AppConstants.PLACE_ID);

            setTitle(PlacesManager.getPlaces().get(placeId).getName());
            pager.setCurrentItem(position);
        }
    }
}
