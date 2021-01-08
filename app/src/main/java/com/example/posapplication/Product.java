package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Product extends AppCompatActivity {

    EditText product, productdesc, quantity, price;
    Spinner spinC, spinB;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> titles1 = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;

    ImageView btnback;

    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);

        product = findViewById(R.id.Product);
        productdesc = findViewById(R.id.ProductDesc);
        quantity = findViewById(R.id.Quantity);
        price = findViewById(R.id.Price);
        spinC = findViewById(R.id.catId);
        spinB = findViewById(R.id.brandid);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnback = findViewById(R.id.back);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setText("");
                productdesc.setText("");
                quantity.setText("");
                price.setText("");
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product.this, Main.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();
            }
        });

        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from category", null);
        int category = c.getColumnIndex("category");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, titles);
        spinC.setAdapter(arrayAdapter);

        final ArrayList<cate> catee = new ArrayList<cate>();
        if (c.moveToFirst())
        {
            do{
                cate ca = new cate();
                ca.category = c.getString(category);
                catee.add(ca);

                titles.add(c.getString(category));



            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
         }

        final Cursor b = db.rawQuery("select * from brand", null);
        int brand = b.getColumnIndex("brand");

        titles1.clear();

        arrayAdapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, titles1);
        spinB.setAdapter(arrayAdapter1);

        final ArrayList<bran> brann = new ArrayList<bran>();
        if (b.moveToFirst())
        {
            do{
                bran ba = new bran();
                ba.brand = b.getString(brand);
                brann.add(ba);

                titles1.add(b.getString(brand));



            }while (b.moveToNext());
            arrayAdapter1.notifyDataSetChanged();
        }

    }

    public void Insert()
    {
        try
        {
            String prod = product.getText().toString();
            String prodescription = productdesc.getText().toString();
            String cat = spinC.getSelectedItem().toString();
            String brand = spinB.getSelectedItem().toString();
            String quant = quantity.getText().toString();
            String pricee = price.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT,product VARCHAR, productdes VARCHAR, category VARCHAR, brand VARCHAR, qty VARCHAR, price VARCHAR)");

            String sql = "insert into product (product, productdes, category, brand, qty, price)values(?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, prod);
            statement.bindString(2, prodescription);
            statement.bindString(3, cat);
            statement.bindString(4, brand);
            statement.bindString(5, quant);
            statement.bindString(6, pricee);

            statement.execute();
            Toast.makeText(this, "Product Created", Toast.LENGTH_LONG).show();
            product.setText("");
            productdesc.setText("");
            quantity.setText("");
            price.setText("");
            product.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Product Failed", Toast.LENGTH_SHORT).show();
        }
    }
}