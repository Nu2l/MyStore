package com.example.sin.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by nanth on 11/23/2016.
 */

public class ProductDBHelper extends SQLiteOpenHelper {
    private static ProductDBHelper productDBHelper;
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "pgms.db";

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

        public static final String TABLE_TRANS = "transaction_table";
        public static final String COLUMN_TRANS_ID = "transactionID";
        public static final String COLUMN_TRANS_REF_ID ="refTransactionID";
        public static final String COLUMN_TRANS_SHOPID = "shopID";
        public static final String COLUMN_TRANS_USERNAME = "username";
        public static final String COLUMN_TRANS_TOTAL = "total";
        public static final String COLUMN_TRANS_DISCOUNT = "discount";
        public static final String COLUMN_TRANS_DISCOUNT_DETAIL = "discountDetail";
        public static final String COLUMN_TRANS_STATUS = "status";
        public static final String COLUMN_TRANS_CREATE_AT = "createAt";
        public static final String COLUMN_TRANS_UPDATE_AT = "updateAt";

        public static final String TABLE_TRANS_D = "transactionDetail";
        public static final String COLUMN_TRANS_D_ID ="transactionID";
        public static final String COLUMN_TRANS_D_PRODUCT_ID ="productID";
        public static final String COLUMN_TRANS_D_QTY = "qty";
        public static final String COLUMN_TRANS_D_CREATE_AT = "createAt";
        public static final String COLUMN_TRANS_D_UPDATE_AT ="updateAt";

        private static final String TAG = "MyActivity";
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
        String DROP_PRODUCT = "DROP TABLE IF EXISTS " +Table.TABLE_PRODUCT ;
        String DROP_TRANS = "DROP TABLE IF EXISTS " +Table.TABLE_TRANS ;
        String DROP_TRANS_D = "DROP TABLE IF EXISTS " +Table.TABLE_TRANS_D ;
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

        String CREATE_TABLE_TRANS = "CREATE TABLE IF NOT EXISTS "+Table.TABLE_TRANS + " ( " +
                Table.COLUMN_TRANS_ID+" INTEGER PRIMARY KEY , "+
                Table.COLUMN_TRANS_REF_ID+" INTEGER , "+
                Table.COLUMN_TRANS_USERNAME+" TEXT, " +
                Table.COLUMN_TRANS_TOTAL+" REAL, " +
                Table.COLUMN_TRANS_DISCOUNT+ " REAL, "+
                Table.COLUMN_TRANS_DISCOUNT_DETAIL + " TEXT, " +
                Table.COLUMN_TRANS_CREATE_AT + " TEXT, " +
                Table.COLUMN_TRANS_STATUS+" INTEGER)";

        String CREATE_TABLE_TRANS_D = "CREATE TABLE IF NOT EXISTS "+Table.TABLE_TRANS_D + " ( " +
//                Table.COLUMN_TRANS_SHOPID+" INTEGER , "+
                Table.COLUMN_TRANS_D_ID+" INTEGER, " +
                Table.COLUMN_TRANS_D_PRODUCT_ID+" REAL, " +
                Table.COLUMN_TRANS_D_QTY+ " REAL, "+
                Table.COLUMN_TRANS_D_CREATE_AT + " TEXT, " +
                "PRIMARY KEY ("+Table.COLUMN_TRANS_D_PRODUCT_ID+", "+Table.COLUMN_TRANS_D_ID+")" +
                ")";
//        db.execSQL(DROP_PRODUCT);
//        db.execSQL(DROP_TRANS); เอาออกดีกว่า เราลบแค่ครั้งแรกตอนเข้าแอพ , เ method นี้ทำงาน มากกว่า 1 ครั้ง ถ้าไปสั่ง new
//        db.execSQL(DROP_TRANS_D);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_TRANS);
        db.execSQL(CREATE_TABLE_TRANS_D);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Product searchProductByBarCode(String bacode){
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

    public Product searchProductByID(String id){
        String sql = "SELECT * FROM "+Table.TABLE_PRODUCT +
                " WHERE "+Table.COLUMN_P_ID +" = '"+id+"'";
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

    public void ShowListProduct(){
        String sql = "SELECT * FROM "+Table.TABLE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        for(int i=0;i< cursor.getCount();i++){
            String a = cursor.getString(cursor.getColumnIndex(Table.COLUMN_P_ID));
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
    public void loadTransaction(JSONArray jsonArray){// bug insert null
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<jsonArray.length();i++){
            try {
                ContentValues values2 = new ContentValues();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
//                Log.d(Table.TAG, jsonObj.toString());
                String createAt = jsonObj.getString(Table.COLUMN_TRANS_CREATE_AT).replace("T"," ");
                createAt = createAt.substring(0, createAt.indexOf('.'));
                values2.put(Table.COLUMN_TRANS_ID, jsonObj.getInt(Table.COLUMN_TRANS_ID));
                values2.put(Table.COLUMN_TRANS_REF_ID, jsonObj.getInt(Table.COLUMN_TRANS_REF_ID));
                values2.put(Table.COLUMN_TRANS_USERNAME, jsonObj.getString(Table.COLUMN_TRANS_USERNAME));
                values2.put(Table.COLUMN_TRANS_TOTAL,jsonObj.getDouble(Table.COLUMN_TRANS_TOTAL));
                values2.put(Table.COLUMN_TRANS_DISCOUNT, jsonObj.getDouble(Table.COLUMN_TRANS_DISCOUNT));
                values2.put(Table.COLUMN_TRANS_DISCOUNT_DETAIL, jsonObj.getString(Table.COLUMN_TRANS_DISCOUNT_DETAIL));
                values2.put(Table.COLUMN_TRANS_STATUS, jsonObj.getString(Table.COLUMN_TRANS_STATUS));
                values2.put(Table.COLUMN_TRANS_CREATE_AT, createAt);
                db.insert(Table.TABLE_TRANS, null, values2);
                System.out.println("Insert "+values2.getAsString(Table.COLUMN_TRANS_ID));
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
