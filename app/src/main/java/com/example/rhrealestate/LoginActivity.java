package com.example.rhrealestate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // defining all the variables
    private TextView newUser;
    private EditText usernameEt, pwordEt;
    private Button loginBtn;

    private DatabaseHelper dbhelper;
    private Boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        newUser = (TextView) findViewById(R.id.newUser_tv);
        usernameEt = (EditText) findViewById(R.id.username_et);
        pwordEt = (EditText) findViewById(R.id.passworduser_et);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        dbhelper = new DatabaseHelper(getApplicationContext());

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                String pass = pwordEt.getText().toString();

                // if the fields are empty show message, otherwise verify the user and show the MainActivity
                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    status = dbhelper.verifyUser(username, pass);
                    if (status == true) {
                        Toast.makeText(getApplicationContext(),"Login Successful.", Toast.LENGTH_SHORT).show();
                        Log.i("info","successsssssssssssss");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid username or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the user is new show the Registration Page
                Intent intenttwo = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intenttwo);
            }
        });

    }
}