package com.example.sin.projectone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by nanth on 11/25/2016.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> products;
    private int layout;
    public ProductAdapter(Context context, ArrayList<Product> products, int layout){
        super(context, 0, products);
        this.products = products;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(ProductAdapter.this.layout, parent, false);
        }
        // Lookup view for data population
        if(product.id!=null && !product.id.isEmpty()){
            TextView productName = (TextView) convertView.findViewById(R.id.product_name);
            TextView productPrice = (TextView) convertView.findViewById(R.id.product_price);
            TextView productQty = (TextView) convertView.findViewById(R.id.product_qty);
            if(productName!=null){
                productName.setText(product.name);
                int i = convertView.getId();
            }
            if(productPrice!=null){
                productPrice.setText(product.price);
                System.out.println(convertView.getId());
            }
            if(productQty!=null){
                productQty.setText(Integer.toString(product.qty));
            }
        }
        // Populate the data into the template view using the data object
        // Return the completed view to render on screen
        return convertView;
    }

    public boolean addQtyProduct(String productID, int qty){
        if(productID==null || productID.isEmpty()){
            return false;
        }
        for(int i=0;i<products.size();i++){
            Product p  = products.get(i);
            if(p.id.equals(productID)){
                p.qty+=qty;
                products.set(i,p);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> getAllProducts(){
        return products;
    }

}
