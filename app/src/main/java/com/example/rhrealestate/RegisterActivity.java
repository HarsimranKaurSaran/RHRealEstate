package com.example.rhrealestate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    // defining all the variables
    private TextView oldUser;
    private EditText nameEt, emailEt, contactNumberEt, passEt, confirmPasswordEt;
    private Button registerBtn;

    private DatabaseHelper dbhelper;
    private Boolean insertStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        oldUser = (TextView) findViewById(R.id.oldUser_tv);
        nameEt = (EditText) findViewById(R.id.name_et);
        emailEt = (EditText) findViewById(R.id.email_et);
        contactNumberEt = (EditText) findViewById(R.id.contactNumber_et);
        passEt = (EditText) findViewById(R.id.password_et);
        confirmPasswordEt = (EditText) findViewById(R.id.confirmPassword_et);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        dbhelper = new DatabaseHelper(getApplicationContext());

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = 0;
                String name = nameEt.getText().toString();
                String email = emailEt.getText().toString();
                String contactnumber = contactNumberEt.getText().toString();
                String pass = passEt.getText().toString();
                String confirmPass = confirmPasswordEt.getText().toString();

                // if the fields are empty show the message, otherwise insert the data into the table
                if (name.isEmpty() || email.isEmpty() || contactnumber.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields.", Toast.LENGTH_LONG).show();
                } else {
                    if (pass.equals(confirmPass)){
                        User user = new User(userId, name, email, contactnumber, pass, confirmPass);
                        insertStatus = dbhelper.insertUsers(user);
                        if (insertStatus == true) {
                            // if insertion successful display message and show login screen
                            Toast.makeText(getApplicationContext(), "Register successfully.", Toast.LENGTH_LONG).show();
                            Log.i("info", "dataaaaaaaaaaaaaa  " + user);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Not registered, please try again.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please check your entered values.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        oldUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the user is already registered show the Login Page
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}