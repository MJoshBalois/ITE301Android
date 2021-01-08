package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Brand extends AppCompatActivity {

    EditText brandname, branddesc;
    Button btnadd, btncancel;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand);

        brandname = findViewById(R.id.Brand);
        branddesc = findViewById(R.id.BrandDesc);
        btnadd = findViewById(R.id.btnAdd);
        btncancel = findViewById(R.id.btnCancel);
        btnback = findViewById(R.id.back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Brand.this, Main.class);
                startActivity(intent);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brandname.setText("");
                branddesc.setText("");
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();
            }
        });
    }
    public void Insert() {
        try {
            String brand = brandname.getText().toString();
            String branddescription = branddesc.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS brand(id INTEGER PRIMARY KEY AUTOINCREMENT,brand VARCHAR, brandesc VARCHAR)");

            String sql = "insert into brand (brand, brandesc)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, brand);
            statement.bindString(2, branddescription);
            statement.execute();
            Toast.makeText(this, "Brand Created", Toast.LENGTH_LONG).show();
            brandname.setText("");
            branddesc.setText("");
            brandname.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Brand Failed", Toast.LENGTH_SHORT).show();
        }
    }
}