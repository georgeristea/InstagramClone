package com.risteageorge.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signUpName, logInName, signUpPass, logInPass;
    private Button signUpBtn, logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // EditText
        logInName = findViewById(R.id.LogInName);
        logInPass = findViewById(R.id.LogInPass);

        // Button
        signUpBtn = findViewById(R.id.SignUpBtn);
        logInBtn = findViewById(R.id.LogInBtn);


        signUpBtn.setOnClickListener(this);
        logInBtn.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {
            ParseUser.logOut();
        }

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.LogInBtn:
                ParseUser.logInInBackground(logInName.getText().toString(), logInPass.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (e == null && user != null) {
                            Toast.makeText(LogInActivity.this, "Log In successfully", Toast.LENGTH_SHORT).show();
                            LogInActivity.this.goToSocialMedia();
                        }
                        else if (logInPass.getText().toString().equals("")) {

                            Toast.makeText(LogInActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                        }
                        else if (user == null){
                            Toast.makeText(LogInActivity.this, "User does not exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case R.id.SignUpBtn:
                finish();
                break;
        }
    }
    public void goToSocialMedia() {

        Intent intent = new Intent(this, SocialMedia.class);
        startActivity(intent);
    }
    public void RootLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
