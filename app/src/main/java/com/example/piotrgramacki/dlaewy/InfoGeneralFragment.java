package com.example.piotrgramacki.dlaewy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Piotrek on 2018-04-11.
 */

public class InfoGeneralFragment extends Fragment {
    TextView description;
    ImageView oldPhoto;
    ImageView newPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_general, container, false);

        findViews(view);
        setData();

        return view;
    }

    private void findViews(View v) {
        description = v.findViewById(R.id.info_general_text);
        oldPhoto = v.findViewById(R.id.info_general_photo_old);
        newPhoto = v.findViewById(R.id.info_general_photo_new);

//        oldPhoto.setLayoutParams(new ConstraintLayout.LayoutParams(300, 300));
//        oldPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        oldPhoto.setPadding(8, 8, 8, 8);
//
//        newPhoto.setLayoutParams(new ConstraintLayout.LayoutParams(300, 300));
//        newPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        newPhoto.setPadding(8, 8, 8, 8);
    }

    private void setData() {
        int index = getArguments().getInt(AppConstants.PLACE_ID);
        description.setText(PlacesManager.getPlaces().get(index).getDescription());
        oldPhoto.setImageResource(PlacesManager.getPlaces().get(index).getOldPhoto());
        newPhoto.setImageResource(PlacesManager.getPlaces().get(index).getNewPhoto());
    }

    public static InfoGeneralFragment newInstance(int index) {
        InfoGeneralFragment instance = new InfoGeneralFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.PLACE_ID, index);

        instance.setArguments(bundle);

        return instance;
    }
}
