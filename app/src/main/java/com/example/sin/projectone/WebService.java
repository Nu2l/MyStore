package com.example.sin.projectone;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by nanth on 11/11/2016.
 */
public class WebService {
    private WebService(){}

    private static AsyncHttpClient _Client = new AsyncHttpClient();
    public static void getABC(AsyncHttpResponseHandler handler, String url){
        _Client.get(url,handler);
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
