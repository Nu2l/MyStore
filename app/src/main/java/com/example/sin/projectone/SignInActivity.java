package com.example.sin.projectone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            navigateToMainActivity();
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
    private void navigateToMainActivity(){
        Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainIntent);
    }
}
