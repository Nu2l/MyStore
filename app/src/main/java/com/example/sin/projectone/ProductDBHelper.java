package com.example.sin.projectone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;

/**
 * Created by nanth on 11/23/2016.
 */

public class ProductDBHelper extends SQLiteOpenHelper {
    private static ProductDBHelper productDBHelper;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "product.db";


    public class Table{
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_P_ID = "productID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_S_ID = "shopID";
        public static final String COLUMN_BARCODE = "barCode";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_DETAIL = "detail";
        public static final String COLUMN_TYPE = "type";
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
        String CREATE_TABLE_PRODUCT="CREATE TABLE "+Table.TABLE_NAME+" ( " +
                Table.COLUMN_P_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table.COLUMN_S_ID+" INTEGER, " +
                Table.COLUMN_BARCODE+" TEXT, " +
                Table.COLUMN_NAME+" TEXT, " +
                Table.COLUMN_BARCODE+" TEXT, " +
                Table.COLUMN_DETAIL+" TEXT, " +
                Table.COLUMN_TYPE+" TEXT)";
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void searchProduct(int productID){
        String sql = "SELECT * FROM "+Table.TABLE_NAME+" WHERE productid = '"+Table.COLUMN_P_ID+"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            System.out.println(cursor.getInt(0));
        }
        db.close();
    }

    public static void LoadProduct(Context context,JSONArray jsonArray){
        SQLiteDatabase db = getInstance(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        
        db.close();

    }

  \


}
