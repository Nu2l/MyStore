package com.example.sin.projectone;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nanth on 11/25/2016.
 */

public class Product {
    public String id, name, bacode, price, type, detail;
    int qty;

    public Product(String id, String name, String bacode, String price, int qty, String type, String detail){
        this.id = id;
        this.name = name;
        this.bacode = bacode;
        this.price = price;
        this.qty = qty;
        this.type = type;
        this.detail = detail;
    }

    public static Product CursorToProduct(Cursor cursor){
        if(cursor.moveToFirst()&&cursor.getCount()>0){
            Product p =new Product(//id name bacode price
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_P_ID)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_BACODE)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_PRICE)),
                    cursor.getInt(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_QTY)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_TYPE)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_DETAIL))
                    );
            return  p;
        }
        return null;
    }

//    public static JSONArray productsToJSONProductKey(Product[] products){
//        JSONObject obj = new JSONObject();
//        JSONArray result = new JSONArray();
//        for(Product p : products){
//            try {
//                obj.put(ProductDBHelper.Table.COLUMN_P_ID, p.id);
//                obj.put(ProductDBHelper.Table.COLUMN_NAME, p.name);
//                obj.put(ProductDBHelper.Table.COLUMN_BACODE, p.bacode);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
