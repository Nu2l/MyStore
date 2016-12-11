package com.example.sin.projectone;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;


/**
 * Created by nanth on 11/11/2016.
 */
public class WebService {
    private WebService(){}

    private static AsyncHttpClient _Client = new AsyncHttpClient();
    public static void getAllProduct(JsonHttpResponseHandler handler){
        String url = Constant.URL_GET_ALL_PRODUCT;
        _Client.get(url,null, handler);
    }

    public static void sendTransaction(AsyncHttpResponseHandler handler, JSONObject transaction){
        String url = Constant.URL_SEND_TRANSACTION;
        String data = transaction.toString();
        StringEntity entity = null;
        try {
            entity = new StringEntity(data);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch(Exception e) {
        }
        _Client.post(ApplicationHelper.getAppContext(),url,entity,"application/json",handler);
    }

    public static void sendAddOrUpdateProduct(AsyncHttpResponseHandler handler, JSONObject product){
        String url = Constant.SEND_ADD_OR_UPDATE_PRODUCT;
        String data = product.toString();
        StringEntity entity = null;
        try {
            entity = new StringEntity(data);
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch(Exception e) {
        }
        _Client.post(ApplicationHelper.getAppContext(),url,entity,"application/json",handler);
    }

    public static void postABC(AsyncHttpResponseHandler handler,String url){
        _Client.post(url,handler);
    }

    //* Example
    //  WebService.getABC(new AsyncHttpResponseHandler(){
    //      @Override
    //    public void onStart() {
    //        // Initiated the request
    //    }
    //
    //    @Override
    //    public void onSuccess(String response) {
    //        // Successfully got a response
    //    }
    //
    //    @Override
    //    public void onFailure(Throwable e, String response) {
    //        // Response failed :(
    //    }
    //
    //    @Override
    //    public void onFinish() {
    //        // Completed the request (either success or failure)
    //    }
    // })

}
