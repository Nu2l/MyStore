package com.example.sin.projectone;

/**
 * Created by nanth on 11/24/2016.
 */

public class Constant {
    private Constant(){

    }

    public static int SHOP_ID = 2;
    public static int USER_ID = 3; // debug test

    //Key json
    public final static String KEY_JSON_PRODUCTLIST = "productList";
    public final static String KEY_JSON_PRODUCT_ID = "productID";
    public final static String KEY_JSON_PRODUCT_BARCODE = "barcode";
    public final static String KEY_JSON_PRODUCT_QTY = "qty";
    public final static String KEY_JSON_PRODUCT_NAME = "name";
    public final static String KEY_JSON_PRODUCT_type = "type";
    public final static String KEY_JSON_PRODUCT_PRICE = "price";
    public final static String KEY_JSON_PRODUCT_img = "imgName";
    public final static String KEY_JSON_PRODUCT_COST = "cost";
    public final static String KEY_JSON_PRODUCT_DETAILS = "details";
    public final static String KEY_JSON_PRODUCT_CREATE_AT = "createAt";
    public final static String KEY_JSON_SHOPID = "shopID";
    public final static String KEY_JSON_DETAIL_DISCOUNT = "discountDetatil";
    public final static String KEY_JSON_DISCOUNT = "discount";
    public final static String KEY_JSON_TOTAL = "total";
    public final static String KEY_JSON_TRANSACTIONID = "transactionID";

    public final static String KEY_JSON_USERID = "userID";


    //
    // Permission
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    //
    //url web Service
    public final static String URL_SERVER = "http://188.166.239.218:3001";
    public final static String URL_GET_ALL_PRODUCT = URL_SERVER+"/api/product/"+SHOP_ID;
    public final static String URL_SEND_TRANSACTION =  URL_SERVER+"/api/transaction";
    // Tag Fragment
    public final static String TAG_FRAGMENT_ITEM_EDIT = "tag_fragment_item_edit";
    public final static String TAG_FRAGMENT_ITEM_ADD = "tag_fragment_item_add";
    public final static String TAG_FRAGMENT_ITEM_VIEW = "tag_fragment_item_view";
    public final static String TAG_FRAGMENT_RECEIPT_MAIN = "tag_receipt_main";
    public final static String TAG_FRAGMENT_PAYMENT_MAIN = "tag_fragment_payment_main";
    public final static String TAG_FRAGMENT_PAYMENT_END = "tag_fragment_payment_end";
    public final static String TAG_FRAGMENT_ITEM_MAIN = "tag_fragment_item_main";
    public final static String TAG_FRAGMENT_SCAN_PAYMENT = "tag_fragment_scanpayment";
    public final static String TAG_FRAGMENT_DIALOG_ALERT = "tag_fragment_dialog_alert";
    public final static String TAG_FRAGMENT_DIALOG_PRODUCT_DETAIL = "tag_fragment_dialog_alert";
    // Key Intent
    public final static String KEY_INTENT_PRODUCT = "key_intent_product";
    // Key Bundle
    public final static String KEY_BUNDLE_ARRAYLIST_PRODUCT = "key_bundle_array_list_product";
    public final static String KEY_BUNDLE_MESSAGE_DIALOG = "key_bundle_message_dialog";
    public final static String KEY_BUNDLE_TITLE_DIALOG = "key_bundle_title_dialog";
    public final static String KEY_BYNDLE_HAS_OK_CANCEL_DIALOG = "key_bundle_ok_cancel";
    public final static String KEY_BUNDLE_PRODUCT= "key_bundle_product";
    // Request code
    public final static int REQUEST_CODE_PRODUCT_PAYMENT_DIALOG          = 1000001;
    public final static int RESULT_CODE_PRODUCT_PAYMENT_DIALOG_SUBMIT    = 1000002;
    public final static int RESULT_CODE_PRODUCT_PAYMENT_DIALOG_CANCEL    = 1000003;

    public final static int REQUEST_CODE_PRODUCT_DETAIL_DIALOG           = 3000001;


    public final static int REQUEST_CODE_OK_CANCEL                       = 2000001;
    public final static int RESULT_CODE_OK                               = 2000002;
    public final static int RESULT_CODE_CANCEL                           = 2000003;
    //message
    public final static String MESSAGE_ALERT_PRODUCT_SCAN_FIRST = "Please scan the product first";

    // other
    // path to /data/data/com.example.sin.projectone/app_productImg
    public final static String FOLDER_PHOTO = "productImg";

}
