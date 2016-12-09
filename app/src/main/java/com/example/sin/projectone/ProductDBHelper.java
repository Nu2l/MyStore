package com.example.sin.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nanth on 11/23/2016.
 */

public class ProductDBHelper extends SQLiteOpenHelper {
    private static ProductDBHelper productDBHelper;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "product.db";
    public static int SHOP_ID = 1;

    public class Table{
        public static final String TABLE_PRODUCT = "product";

        public static final String COLUMN_P_ID = "productID"; // primary key
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BARCODE = "bacode"; // unique key
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_QTY = "qty";
        public static final String COLUMN_IMG = "imgName";

        public static final String COLUMN_S_ID = "shopID"; // dabasbase
        public static final String COLUMN_DETAIL = "detail";// unUse
        public static final String COLUMN_USER_ID = "userID";
    }
    private ProductDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized ProductDBHelper getInstance(Context context){
        if(productDBHelper==null){
            productDBHelper = new ProductDBHelper(context.getApplicationContext());
        }
        return productDBHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PRODUCT="CREATE TABLE "+Table.TABLE_PRODUCT +" ( " +
                Table.COLUMN_P_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                //Table.COLUMN_S_ID+" INTEGER, " +
                Table.COLUMN_BARCODE +" TEXT UNIQUE, " +
                Table.COLUMN_NAME+" TEXT, " +
                //Table.COLUMN_BRAND+" TEXT, " +
                Table.COLUMN_PRICE+" TEXT,"+
                Table.COLUMN_TYPE+" TEXT, " +
                Table.COLUMN_QTY+" INTEGER, "+
                Table.COLUMN_IMG+" TEXT)";
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Product searchProduct(String bacode){
        String sql = "SELECT * FROM "+Table.TABLE_PRODUCT +
                " WHERE "+Table.COLUMN_BARCODE +" = '"+bacode+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            db.close();
            System.out.println(cursor.getString(cursor.getColumnIndex(Table.COLUMN_P_ID)));
            return Product.CursorToProduct(cursor);
        }
        else{
            db.close();
            return null;
        }

    }

    public void LoadProduct(JSONArray jsonArray){// bug insert null
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<jsonArray.length();i++){
            try {
                ContentValues values = new ContentValues();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                values.put(Table.COLUMN_P_ID, jsonObj.getInt(Constant.KEY_JSON_PRODUCT_ID));
                values.put(Table.COLUMN_BARCODE, jsonObj.getString(Constant.KEY_JSON_PRODUCT_BARCODE));
                values.put(Table.COLUMN_QTY,jsonObj.getInt(Constant.KEY_JSON_PRODUCT_QTY));
                values.put(Table.COLUMN_NAME, jsonObj.getString(Constant.KEY_JSON_PRODUCT_NAME));
                values.put(Table.COLUMN_TYPE, jsonObj.getString(Constant.KEY_JSON_PRODUCT_type));
                values.put(Table.COLUMN_PRICE, jsonObj.getString(Constant.KEY_JSON_PRODUCT_PRICE));
                values.put(Table.COLUMN_IMG, jsonObj.getString(Constant.KEY_JSON_PRODUCT_img));
                db.insert(Table.TABLE_PRODUCT, null, values);
                System.out.println("Insert "+values.getAsString(Table.COLUMN_P_ID));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       // values.put();
        db.close();

    }

    public JSONObject getJSONTransaction(ArrayList<Product> products, String detail, float discount, float total){
        JSONObject transation = new JSONObject();
        JSONObject obj;
        JSONArray productList = new JSONArray();
        if(!products.isEmpty()&& products.size()>0){
            try {
                transation.put(Constant.KEY_JSON_USERID,Constant.USER_ID);
                transation.put(Constant.KEY_JSON_SHOPID,Constant.SHOP_ID);
                transation.put(Constant.KEY_JSON_DETAIL_DISCOUNT, detail);
                transation.put(Constant.KEY_JSON_DISCOUNT, discount);
                transation.put(Constant.KEY_JSON_TOTAL, total);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(Product p: products){
                try {
                    obj = new JSONObject();
                    obj.put(Constant.KEY_JSON_PRODUCT_ID, p.id);
                    obj.put(Constant.KEY_JSON_PRODUCT_QTY, p.qty);
                    productList.put(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                transation.put(Constant.KEY_JSON_PRODUCTLIST,productList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String t = transation.toString();
            return transation;
        }
        else{
            return null;
        }

    }




}
