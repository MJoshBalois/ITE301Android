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

public class CategoryEdit extends AppCompatActivity {

    EditText catID, cat, catdesc;
    Button btnedit, btndelete, btncancel;

    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoryedit);

        catID = findViewById(R.id.categoryID);
        cat = findViewById(R.id.category);
        catdesc = findViewById(R.id.CategoryDesc);
        btnedit = findViewById(R.id.btnEdit);
        btndelete = findViewById(R.id.btnDelete);
        btncancel = findViewById(R.id.btnCancel);
        Back = findViewById(R.id.back);

        Intent i  = getIntent();

        String id = i.getStringExtra("id".toString());
        String category = i.getStringExtra("category".toString());
        String desc = i.getStringExtra("catdesc".toString());

        catID.setText(id);
        cat.setText(category);
        catdesc.setText(desc);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryEdit.this, CategoryView.class);
                startActivity(intent);
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
            String catid = catID.getText().toString();
            String Categoryname = cat.getText().toString();
            String catdescription = catdesc.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR, catdesc VARCHAR)");

            String sql = "update category set category = ?, catdesc=? where id =?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, Categoryname);
            statement.bindString(2, catdescription);
            statement.bindString(3, catid);
            statement.execute();
            Toast.makeText(this, "Category Updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ViewMain.class);
            startActivity(i);


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Category Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void Delete()
    {
        try
        {
            String catid = catID.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS category(id INTEGER PRIMARY KEY AUTOINCREMENT,category VARCHAR, catdesc VARCHAR)");

            String sql = "delete from category where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, catid);
            statement.execute();
            Toast.makeText(this, "Category Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ViewMain.class);
            startActivity(i);


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Category Failed", Toast.LENGTH_SHORT).show();
        }

    }
}