package com.example.sin.projectone;

/**
 * Created by nanth on 11/24/2016.
 */

public class Constant {
    private Constant(){

    }

    public static int SHOP_ID = 2;
    public static int USER_ID = 4; // debug test

    //Key json
    public final static String KEY_JSON_PRODUCTLIST = "productList";
    public final static String KEY_JSON_PRODUCT_ID = "productID";
    public final static String KEY_JSON_PRODUCT_BARCODE = "barcode";
    public final static String KEY_JSON_PRODUCT_QTY = "qty";
    public final static String KEY_JSON_PRODUCT_NAME = "name";
    public final static String KEY_JSON_PRODUCT_type = "type";
    public final static String KEY_JSON_PRODUCT_PRICE = "price";
    public final static String KEY_JSON_PRODUCT_img = "imgName";

    public final static String KEY_JSON_USERID = "userID";
    //
    // Permission
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    //
    //url web Service
    public final static String URL_SERVER = "http://188.166.239.218:3001";
    public final static String URL_GET_ALL_PRODUCT = URL_SERVER+"/api/product/"+SHOP_ID;

    // other
    // path to /data/data/com.example.sin.projectone/app_productImg
    public final static String FOLDER_PHOTO = "productImg";
}
