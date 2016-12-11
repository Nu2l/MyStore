package com.example.sin.projectone.item;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.sin.projectone.ApplicationHelper;
import com.example.sin.projectone.Product;
import com.example.sin.projectone.ProductAdapter;
import com.example.sin.projectone.ProductDBHelper;
import com.example.sin.projectone.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nanth on 12/11/2016.
 */

public class ViewProduct extends Fragment {
    private FragmentManager fragmentManager;
    private ProductAdapter productAdapter;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayAdapter<CharSequence> spinerAdapter;
    private ListView listProduct;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_item_view, container, false);
        // set spiner
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinerAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.titles, R.layout.spiner_item);
        spinner.setAdapter(spinerAdapter);
        //set View
        listProduct = (ListView) view.findViewById(R.id.list_view_item);
        products = ProductDBHelper.getInstance(getActivity().getApplicationContext()).getAllProductFromDB();
        productAdapter = new ProductAdapter(ApplicationHelper.getAppContext(), products, R.layout.list_item);
        listProduct.setAdapter(productAdapter);
        return view;
    }
}
