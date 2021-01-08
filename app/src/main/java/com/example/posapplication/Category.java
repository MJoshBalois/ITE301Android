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

public class Category extends AppCompatActivity {

    EditText category, categorydesc;
    Button btnAdd, btnCancel;
    ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        category = findViewById(R.id.category);
        categorydesc = findViewById(R.id.CategoryDesc);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnback = findViewById(R.id.back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Main.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category.setText("");
                categorydesc.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();

            }
        });
    }
    public void Insert()
    {
        try
        {
            String Category = category.getText().toString();
            String description = categorydesc.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR, catdesc VARCHAR)");

            String sql = "insert into category (category, catdesc)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, Category);
            statement.bindString(2,description);
            statement.execute();
            Toast.makeText(this, "Category Created", Toast.LENGTH_LONG).show();
            category.setText("");
            categorydesc.setText("");
            category.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Category Failed", Toast.LENGTH_SHORT).show();
        }
    }
}