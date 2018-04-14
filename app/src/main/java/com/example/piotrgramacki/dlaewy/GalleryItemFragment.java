package com.example.piotrgramacki.dlaewy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Piotrek on 2018-04-14.
 */

public class GalleryItemFragment extends Fragment {
    ImageView oldPhoto;
    ImageView newPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_item, container, false);

        findViews(view);
        setData();

        return view;
    }

    private void findViews(View v) {
        oldPhoto = v.findViewById(R.id.gallery_item_old);
        newPhoto = v.findViewById(R.id.gallery_item_new);
    }

    private void setData() {
        int idOld = getArguments().getInt(AppConstants.GALLERY_OLD_PHOTO);
        int idNew = getArguments().getInt(AppConstants.GALLERY_NEW_PHOTO);

        oldPhoto.setImageResource(idOld);
        newPhoto.setImageResource(idNew);
    }

    public static GalleryItemFragment newInstance(int idOld, int idNew) {
        GalleryItemFragment instance = new GalleryItemFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.GALLERY_OLD_PHOTO, idOld);
        bundle.putInt(AppConstants.GALLERY_NEW_PHOTO, idNew);

        instance.setArguments(bundle);

        return instance;
    }
}
