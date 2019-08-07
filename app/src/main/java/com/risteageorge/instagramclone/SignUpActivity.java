package com.risteageorge.instagramclone;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail, edtUsername, edtPass;
    Button signUpBtnMain, logInBtnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //setTitle("SignUp");

        // EditText
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);

        // Butoane

        signUpBtnMain = findViewById(R.id.SignUpBtnMain);
        logInBtnMain = findViewById(R.id.LogInBtnMain);

        // signUp cand se apasa ENTER

        edtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(signUpBtnMain);
                    return true;
                }

                return false;
            }
        });


        signUpBtnMain.setOnClickListener(this);
        logInBtnMain.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            startActivity(new Intent(SignUpActivity.this, SocialMedia.class));

        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.SignUpBtnMain:
                if (edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") ||
                        edtPass.getText().toString().equals("")) {

                    Toast.makeText(SignUpActivity.this, "Please complete all fields",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    ParseUser parseUser = new ParseUser();
                    parseUser.setEmail(edtEmail.getText().toString());
                    parseUser.setUsername(edtUsername.getText().toString());
                    parseUser.setPassword(edtPass.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Wait a moment...");
                    progressDialog.show();
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {
                                Toast.makeText(SignUpActivity.this, "User " + edtUsername.getText().toString() + " sign up" +
                                        " successfully ", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                    startActivity(new Intent(SignUpActivity.this, SocialMedia.class));
                }
                break;
            case R.id.LogInBtnMain:
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                break;
        }
    }


}
