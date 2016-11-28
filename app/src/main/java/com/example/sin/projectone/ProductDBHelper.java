package com.example.sin.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        public static final String COLUMN_BACODE = "bacode"; // unique key
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DETAIL = "detail";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_QTY = "qty";

        public static final String COLUMN_S_ID = "shopID"; // dabasbase
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
                Table.COLUMN_BACODE +" TEXT UNIQUE, " +
                Table.COLUMN_NAME+" TEXT, " +
                //Table.COLUMN_BRAND+" TEXT, " +
                Table.COLUMN_PRICE+" TEXT"+
                Table.COLUMN_DETAIL+" TEXT, " +
                Table.COLUMN_TYPE+" TEXT, " +
                Table.COLUMN_QTY+" INTEGER)";
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Product searchProduct(String bacode){
        String sql = "SELECT * FROM "+Table.TABLE_PRODUCT +
                " WHERE "+Table.COLUMN_BACODE +" = '"+bacode+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!=null) {
            cursor.moveToFirst();
            db.close();
            return Product.CursorToProduct(cursor);
        }
        else{
            db.close();
            return null;
        }

    }

    public void LoadProduct(JSONArray jsonArray){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<jsonArray.length();i++){
            try {
                ContentValues values = new ContentValues();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                values.put(Table.COLUMN_P_ID, jsonObj.getInt("productID"));
                values.put(Table.COLUMN_BACODE, jsonObj.getString("bacode"));
                values.put(Table.COLUMN_QTY,jsonObj.getInt("qty"));
                values.put(Table.COLUMN_NAME, jsonObj.getString("name"));
                values.put(Table.COLUMN_TYPE, jsonObj.getString("type"));
                values.put(Table.COLUMN_PRICE, jsonObj.getString("price"));
                values.put(Table.COLUMN_DETAIL, jsonObj.getString("detail"));
                db.insert(Table.TABLE_PRODUCT, null, values);
                System.out.println("Insert "+values.getAsString(Table.COLUMN_NAME+" "+Table.COLUMN_BACODE));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       // values.put();
        db.close();

    }




}
