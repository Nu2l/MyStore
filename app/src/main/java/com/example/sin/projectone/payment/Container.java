package com.example.sin.projectone.payment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sin.projectone.Constant;
import com.example.sin.projectone.R;

/**
 * Created by nanth on 12/8/2016.
 */

public class Container extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_payment_container, container, false);
        Fragment newFragment = new Main();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.frame_container_payment, newFragment, Constant.TAG_FRAGMENT_PAYMENT_MAIN);
        //transaction.addToBackStack(null);
// Commit the transaction
        transaction.commit();
        return view;
    }

}

