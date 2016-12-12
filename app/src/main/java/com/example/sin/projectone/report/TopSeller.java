package com.example.sin.projectone.report;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sin.projectone.R;

/**
 * Created by naki_ on 11/25/2016.
 */

public class TopSeller extends Fragment {
    private Button dailyReport;
    private Button topSell;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_report_topseller, container, false);

        return view;
    }
}
