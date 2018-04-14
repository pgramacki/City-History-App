package com.example.piotrgramacki.dlaewy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity implements InfoGalleryFragment.OnPhotoClickListener {
    TabLayout tabLayout;
    ViewPager pager;

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

        setTitle(PlacesManager.getPlaces().get(getIntent().getExtras().getInt(AppConstants.PLACE_ID)).getName());
    }

    private void setData() {

    }

    @Override
    public void onPhotoCLick(int position) {
        Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_LONG).show();
    }
}
