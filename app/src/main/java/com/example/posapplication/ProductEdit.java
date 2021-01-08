package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class ProductEdit extends AppCompatActivity {

    EditText ProductId, Product, ProductDescription, Quantity, Price;
    Spinner Category, Brand;
    Button btnedit, btndelete, btncancel;

    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productedit);

        ProductId = findViewById(R.id.ProductID);
        Product = findViewById(R.id.Product);
        //ProductDescription = findViewById(R.id.ProductDesc);
        Quantity = findViewById(R.id.Quantity);
        Price = findViewById(R.id.Price);
        Category = findViewById(R.id.catId);
        Brand = findViewById(R.id.brandid);
        btnedit = findViewById(R.id.btnEdit);
        btndelete = findViewById(R.id.btnDelete);
        btncancel = findViewById(R.id.btnCancel);
        Back = findViewById(R.id.back);

        Intent i  = getIntent();

        String id = i.getStringExtra("id".toString());
        String product = i.getStringExtra("product".toString());
        //String desc = i.getStringExtra("desc".toString());
        String qty = i.getStringExtra("qty".toString());
        String price = i.getStringExtra("price".toString());

        String cat = i.getStringExtra("category".toString());
        String brand = i.getStringExtra("brand".toString());

        ProductId.setText(id);
        Product.setText(product);
        //ProductDescription.setText(desc);
        Quantity.setText(qty);
        Price.setText(price);
        //Category.getSelectedItem();
        //Brand.getSelectedItem();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductEdit.this, ProductView.class);
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
            String prodid = ProductId.getText().toString();
            String productname = Product.getText().toString();
            //String proddescription = ProductDescription.getText().toString();
            String Qty = Quantity.getText().toString();
            String Prce = Price.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,product VARCHAR, category VARCHAR, brand VARCHAR, qty VARCHAR, price VARCHAR)");

            String sql = "update product set product = ?, qty=?, price=? where id =?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, productname);
            //statement.bindString(2, proddescription);
            statement.bindString(2, Qty);
            statement.bindString(3, Prce);
            statement.bindString(4, prodid);
            statement.execute();
            Toast.makeText(this, "Product Updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ViewMain.class);
            startActivity(i);


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Product Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void Delete()
    {
        try
        {
            String prodid = ProductId.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,product VARCHAR, productdes VARCHAR,  category VARCHAR, brand VARCHAR, qty VARCHAR, price VARCHAR)");

            String sql = "delete from product where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1, prodid);
            statement.execute();
            Toast.makeText(this, "Product Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), ViewMain.class);
            startActivity(i);


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Product Failed", Toast.LENGTH_SHORT).show();
        }

    }
}