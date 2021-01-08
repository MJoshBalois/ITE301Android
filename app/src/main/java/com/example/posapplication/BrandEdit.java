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

public class BrandEdit extends AppCompatActivity {

    EditText brandID, brand, branddescription;
    Button btnedit, btndelete, btncancel;

    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brandedit);

        brandID = findViewById(R.id.BrandID);
        brand = findViewById(R.id.Brand);
        branddescription = findViewById(R.id.BrandDesc);
        btnedit = findViewById(R.id.btnEdit);
        btndelete = findViewById(R.id.btnDelete);
        btncancel = findViewById(R.id.btnCancel);
        Back = findViewById(R.id.back);

        Intent i  = getIntent();

        String id = i.getStringExtra("id".toString());
        String brandd = i.getStringExtra("brand".toString());
        String desc = i.getStringExtra("brandesc".toString());

        brandID.setText(id);
        brand.setText(brandd);
        branddescription.setText(desc);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrandEdit.this, BrandView.class);
                startActivity(i);
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewMain.class);
                startActivity(i);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });
    }

    public void Edit()
    {
        try
        {
            String brandid = brandID.getText().toString();
            String brandname = brand.getText().toString();
            String brandescription = branddescription.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS brand(id INTEGER PRIMARY KEY AUTOINCREMENT,brand VARCHAR, brandesc VARCHAR)");

            String sql = "update brand set brand = ?, brandesc=? where id =?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, brandname);
            statement.bindString(2, brandescription);
            statement.bindString(3, brandid);
            statement.execute();
            Toast.makeText(this, "Brand Updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ViewMain.class);
            startActivity(i);


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Brand Failed", Toast.LENGTH_SHORT).show();
        }
    }
    public void Delete() {
        try {
            String branId = brandID.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS brand(id INTEGER PRIMARY KEY AUTOINCREMENT,brand VARCHAR, brandesc VARCHAR)");

            String sql = "delete from brand where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, branId);
            statement.execute();
            Toast.makeText(this, "Brand Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ViewMain.class);
            startActivity(i);


        } catch (Exception ex) {
            Toast.makeText(this, "Brand Failed", Toast.LENGTH_SHORT).show();
        }
    }
}