package com.example.piotrgramacki.dlaewy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Piotrek on 2018-04-11.
 */

public class InfoHistoryFragment extends Fragment {
    TextView history;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_history, container, false);

        findViews(view);
        setData();

        return view;
    }

    private void findViews(View v) {
        history = v.findViewById(R.id.info_history_text);
        history.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setData() {
        history.setText(getArguments().getString(AppConstants.INFO_HISTORY));
    }

    public static InfoHistoryFragment newInstance(String history) {
        InfoHistoryFragment instance = new InfoHistoryFragment();

        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.INFO_HISTORY, history);
        instance.setArguments(bundle);

        return instance;
    }
}
