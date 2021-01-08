package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductView extends AppCompatActivity {

    ListView list1;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    ImageView Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productview);

        Back = findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductView.this, ViewMain.class);
                startActivity(intent);
            }
        });

        list1 = findViewById(R.id.list1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);

        final Cursor c = db.rawQuery("select * from product", null);
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("product");
        int productdes = c.getColumnIndex("productdes");
        int category = c.getColumnIndex("category");
        int brand = c.getColumnIndex("brand");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);
        list1.setAdapter(arrayAdapter);

        final ArrayList<prod> prodd = new ArrayList<prod>();
        if (c.moveToFirst()) {
            do {
                prod pr = new prod();
                pr.id = c.getString(id);
                pr.product = c.getString(product);
                pr.desc = c.getString(productdes);
                pr.category = c.getString(category);
                pr.brand = c.getString(brand);
                pr.qty = c.getString(qty);
                pr.price = c.getString(price);
                prodd.add(pr);

                titles.add(c.getString(id) + "\t" + c.getString(product) + "\t" + c.getString(category) + "\t" + c.getString(brand) + "\t" + c.getString(qty) + "\t" + c.getString(price));


            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list1.invalidateViews();
        }


        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                prod pr = prodd.get(position);
                Intent i = new Intent(getApplicationContext(), ProductEdit.class);
                i.putExtra("id", pr.id);
                i.putExtra("product", pr.product);
                i.putExtra("desc", pr.desc);
                i.putExtra("qty", pr.qty);
                i.putExtra("price", pr.price);
                startActivity(i);

            }
        });
    }
}
