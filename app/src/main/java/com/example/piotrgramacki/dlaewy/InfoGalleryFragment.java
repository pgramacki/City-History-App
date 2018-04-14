package com.example.piotrgramacki.dlaewy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by Piotrek on 2018-04-11.
 */

public class InfoGalleryFragment extends Fragment {
    private GridView gridView;
    private int[] images;
    private OnPhotoClickListener listener;

    public interface OnPhotoClickListener {
        void onPhotoCLick(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnPhotoClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_gallery, container, false);

        setData();
        findViews(view);
        setListeners();


        return view;
    }

    public static InfoGalleryFragment newInstance(int index) {
        InfoGalleryFragment instance = new InfoGalleryFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.PLACE_ID, index);

        instance.setArguments(bundle);

        return instance;
    }

    private void findViews(View view) {
        gridView = view.findViewById(R.id.info_gallery_grid_view);
        gridView.setAdapter(new ImageAdapter(getActivity(), images));
    }

    private void setListeners() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listener.onPhotoCLick(position);
            }
        });
    }

    private void setData() {
        images = PlacesManager.getPlaces().get(getArguments().getInt(AppConstants.PLACE_ID)).getImages();
    }
}
