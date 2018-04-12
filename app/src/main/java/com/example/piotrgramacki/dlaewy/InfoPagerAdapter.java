package com.example.piotrgramacki.dlaewy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Piotrek on 2018-04-11.
 */

public class InfoPagerAdapter extends FragmentPagerAdapter {
    private Bundle extras;

    public InfoPagerAdapter(FragmentManager fm, Bundle extras) {
        super(fm);
        this.extras = extras;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return InfoGalleryFragment.newInstance();
            case 1: return InfoGeneralFragment.newInstance(
                    extras.getString(AppConstants.PLACE_DESCR),
                    R.drawable.sample_0,
                    R.drawable.sample_1);
            default: return InfoHistoryFragment.newInstance(
                    extras.getString(AppConstants.PLACE_HISTORY));
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //TODO - remove hardcoded strings!!!
        switch (position) {
            case 0: return "GALLERY";
            case 1: return "INFO";
            default: return "HISTORY";
        }
    }
}
