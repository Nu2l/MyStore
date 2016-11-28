package com.example.sin.projectone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nanth on 11/25/2016.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> products;
    public ProductAdapter(Context context, ArrayList<Product> products){
        super(context, 0, products);
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView productName = (TextView) convertView.findViewById(R.id.product_name);
        TextView productPrice = (TextView) convertView.findViewById(R.id.product_price);
        // Populate the data into the template view using the data object
        productName.setText(product.name);
        productPrice.setText(product.price);
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<Product> getAllProducts(){
        return products;
    }

}
