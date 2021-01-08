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

public class BrandView extends AppCompatActivity {

    ListView list1;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brandview);

        Back = findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BrandView.this, ViewMain.class);
                startActivity(intent);
            }
        });

        list1 =  findViewById(R.id.list1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE, null);

        final Cursor c = db.rawQuery("select * from brand", null);
        int id = c.getColumnIndex("id");
        int brand = c.getColumnIndex("brand");
        int branddesc = c.getColumnIndex("brandesc");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, titles);
        list1.setAdapter(arrayAdapter);

        final ArrayList<bran> brann = new ArrayList<bran>();
        if (c.moveToFirst())
        {
            do{
                bran br = new bran();
                br.id = c.getString(id);
                br.brand = c.getString(brand);
                br.desc = c.getString(branddesc);
                brann.add(br);

                titles.add(c.getString(id) + "\t" + c.getString(brand));
                //+ "\t" + c.getString(branddesc)

            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list1.invalidateViews();
        }

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                bran br = brann.get(position);
                Intent i = new Intent(getApplicationContext(), BrandEdit.class);
                i.putExtra("id", br.id);
                i.putExtra("brand", br.brand);
                i.putExtra("brandesc", br.desc);
                startActivity(i);
            }
        });

    }
}