package com.example.sin.projectone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SignInActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passText;
    private Button signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailText = (EditText) findViewById(R.id.sign_in_email);
        passText = (EditText) findViewById(R.id.sign_in_password);
        passText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    submitLogin();
                    return true;
                }
                return false;
            }
        });

        signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLogin();
            }
        });

    }
    private void submitLogin(){
        boolean formValid = true;
        String email = emailText.getText().toString();
        String pass = passText.getText().toString();
        if(TextUtils.isEmpty(email)|| !emailValid(email)){
            emailText.setError(getString(R.string.sign_in_error_invalid_email));
            formValid = false;
            System.out.println("email invalid");
        }
        if(!passwordValid(pass)){
            passText.setError(getString(R.string.sign_in_error_invalid_password));
            formValid = false;
            System.out.println("password invalid");
        }
        if(formValid){
            System.out.println("form is valid");
            signInProcess(email,pass);
        }
        else{
            System.out.println("form is invalid");
        }
    }
    private boolean passwordValid(String password){
        int passLen = password.length();
        System.out.println(passLen);
        return passLen>6 && passLen<17;
    }
    private boolean emailValid(String email){
        System.out.println(email.contains("@"));
        return email.contains("@");
    }
    private void signInProcess(final String user, String pass){
        RequestParams params = new RequestParams();
        params.put("user",user);
        params.put("pass",pass);
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;
        HttpUtilsAsync.post("http://188.166.239.218:3001/api/user/", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    String res = (String) response.get("Message");
                    if(res.equals("Welcome new user !")){
                        navigateToRegisterStore(user);

                        CharSequence text = res;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    else if(res.equals("Welcome Back !")){
                        if(response.get("ShopName").equals("null")){
                            navigateToRegisterStore(user);
                            CharSequence text = "Please create your shop or join in !";
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        else {
                            navigateToMainActivity(user);
                            CharSequence text = res;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                    else{
                        CharSequence text = res;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(errorResponse + " " + statusCode);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                System.out.println("Failed: "+ ""+statusCode);
                Log.d("Error : ", "" + throwable);
            }
        });
//        HttpUtilsAsync.post("http://188.166.239.218:3001/api/user/", new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//
//            }
//        });

    }
    private void navigateToRegisterStore(String user){
        Intent regisIntent = new Intent(getApplicationContext(),RegisterStoreActivity.class);
        regisIntent.putExtra("username",user);
        startActivity(regisIntent);
    }
    private void navigateToMainActivity(String user){
        Intent mainIntent = new Intent(getApplicationContext(),MainNav.class);
        mainIntent.putExtra("username",user);
        startActivity(mainIntent);
    }
}