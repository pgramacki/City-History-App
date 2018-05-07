package com.example1.piotrgramacki.dlaewy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity implements InfoGalleryFragment.OnPhotoClickListener {
    TabLayout tabLayout;
    ViewPager pager;
    int placeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        findViews();
        setData();
    }

    private void findViews() {
        tabLayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.pager);
        pager.setAdapter(new InfoPagerAdapter(getSupportFragmentManager(), getIntent().getExtras()));
        pager.setCurrentItem(1);

        tabLayout.setupWithViewPager(pager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            placeId = extras.getInt(AppConstants.PLACE_ID);
            setTitle(PlacesManager.getPlaces().get(placeId).getName());
        }
    }

    private void setData() {

    }

    @Override
    public void onPhotoCLick(int position) {
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra(AppConstants.PLACE_ID, placeId);
        intent.putExtra(AppConstants.GALLERY_POSITION, position / 2);
        startActivity(intent);
    }
}
