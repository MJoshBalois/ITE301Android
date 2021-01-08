package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button btnLogin, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);
        //btnCancel = findViewById(R.id.btnCancel);

        //btnCancel.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
               // finish();
          //  }
       // });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });
    }

    public void Login()
    {
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if (username.equals("") || password.equals(""))
        {
            Toast.makeText(this, "Username or Password Blank", Toast.LENGTH_LONG).show();
        }
        else if (username.equals("admin") && password.equals("password123"))
        {
            Intent i = new Intent(LoginActivity.this, SuperMain.class);
            startActivity(i);
            Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Username and Password do not match", Toast.LENGTH_LONG).show();
        }
    }
}