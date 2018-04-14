package com.example.piotrgramacki.dlaewy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Piotrek on 2018-04-14.
 */

public class GalleryPagerAdapter extends FragmentStatePagerAdapter {
    private Bundle extras;
    private int[] images;

    public GalleryPagerAdapter(FragmentManager fm, Bundle extras) {
        super(fm);
        this.extras = extras;

        getData();
    }

    @Override
    public Fragment getItem(int position) {
        return GalleryItemFragment.newInstance(images[2 * position], images[2 * position + 1]);
    }

    @Override
    public int getCount() {
        return images.length / 2;
    }

    private void getData() {
        int placeId = extras.getInt(AppConstants.PLACE_ID);
        images = PlacesManager.getPlaces().get(placeId).getImages();
    }
}
