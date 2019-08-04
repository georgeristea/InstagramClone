package com.risteageorge.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpAndLogin extends AppCompatActivity {

    private EditText signUpName, logInName, signUpPass, logInPass;
    private Button signUpBtn, logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_and_login);

        signUpName = findViewById(R.id.SignUpName);
        signUpPass = findViewById(R.id.SignUpPass);
        logInName = findViewById(R.id.LogInName);
        logInPass = findViewById(R.id.LogInPass);

        signUpBtn = findViewById(R.id.SignUpBtn);
        logInBtn = findViewById(R.id.LogInBtn);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser parseUser = new ParseUser();
                parseUser.setUsername(signUpName.getText().toString());
                parseUser.setPassword(signUpPass.getText().toString());

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {
                            Toast.makeText(SignUpAndLogin.this, "User SignUp successfully !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(logInName.getText().toString(), logInPass.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user == null) {
                                    Toast.makeText(SignUpAndLogin.this, "Wrong password or username !", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(SignUpAndLogin.this, "Log In successfully !", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpAndLogin.this, WelcomeToInstagram.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });


    }
}
