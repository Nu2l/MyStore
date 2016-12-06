package com.example.sin.projectone;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nanth on 11/25/2016.
 */

public class Product implements Parcelable {
    public String id, name, barcode, price, type, imgName;
    public int qty;

    public Product(String id, String name, String barcode, String price, int qty, String type, String imgName){
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.qty = qty;
        this.type = type;
        this.imgName = imgName;
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        barcode = in.readString();
        price = in.readString();
        type = in.readString();
        imgName = in.readString();
        qty = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public static Product CursorToProduct(Cursor cursor){
        System.out.println(cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_P_ID)));
        if(cursor.moveToFirst()){
            Product p =new Product(//id name bacode price
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_P_ID)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_BARCODE)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_PRICE)),
                    cursor.getInt(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_QTY)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_TYPE)),
                    cursor.getString(cursor.getColumnIndex(ProductDBHelper.Table.COLUMN_IMG)
                    ));
            return  p;
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(barcode);
        dest.writeString(price);
        dest.writeString(type);
        dest.writeString(imgName);
        dest.writeInt(qty);
    }


}
