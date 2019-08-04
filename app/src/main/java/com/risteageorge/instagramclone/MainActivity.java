package com.risteageorge.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3;
    Button btn1, btn2, switchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editTxt1);
        editText2 = findViewById(R.id.editTxt2);
        editText3 = findViewById(R.id.editTxt3);

        btn1 = findViewById(R.id.btnId1);
        btn2 = findViewById(R.id.btnId2);
        switchBtn = findViewById(R.id.switchBtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseObject kickBoxer = new ParseObject("KickBoxer");
                kickBoxer.put("name", editText1.getText().toString());
                kickBoxer.put("punchSpeed", Integer.parseInt(editText2.getText().toString()));
                kickBoxer.put("punchPower", Integer.parseInt(editText3.getText().toString()));
                kickBoxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, kickBoxer.get("name") + " successfully added to server",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Something went Wrong !\n" + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("KickBoxer");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        String kickBoxers = "";
                        // daca nu da erore si exista obiecte
                        if (e == null && objects != null) {
                            for (ParseObject pObj : objects) {
                                kickBoxers += "Name: " + pObj.get("name") + ", punchSpeed: "  + pObj.get("punchSpeed") +
                                        ", punchPower: " + pObj.get("punchPower") + " !\n";
                            }
                            Toast.makeText(MainActivity.this, kickBoxers, Toast.LENGTH_SHORT).show();
                        }
                        else if (e != null) {
                            Toast.makeText(MainActivity.this, "There are no kickboxers to be shown" , Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, " ERROR", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignUpAndLogin.class);
                startActivity(intent);
            }
        });


    }

}
