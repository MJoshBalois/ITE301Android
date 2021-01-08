package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SuperMain extends AppCompatActivity {

    ImageView Manage, ViewInventory, ViewTransaction, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermain);

        Manage = findViewById(R.id.ivmanage);
        ViewInventory = findViewById(R.id.ivviewinventory);
        ViewTransaction = findViewById(R.id.ivviewtransaction);
        Logout = findViewById(R.id.ivlogout);

        ViewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuperMain.this, Transcation.class);
                startActivity(intent);
            }
        });

        Manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuperMain.this, Main.class);
                startActivity(intent);
            }
        });

        ViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuperMain.this, ViewMain.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuperMain.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}