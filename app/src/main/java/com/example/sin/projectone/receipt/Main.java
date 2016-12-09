package com.example.sin.projectone.receipt;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sin.projectone.R;

/**
 * Created by naki_ on 11/25/2016.
 */

public class Main extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_receipt_main, container, false);

        return view;
        
    }
}
