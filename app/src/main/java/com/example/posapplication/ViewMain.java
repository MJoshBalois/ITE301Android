package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

public class ViewMain extends AppCompatActivity {

    private int[] mImages = new int[] {
            R.drawable.shimano, R.drawable.cannondale, R.drawable.giantbike, R.drawable.yeti
    };

    private String[] mImagesTitle = new String[] {
            "Shimano", "Cannondale", "Giant", "Yeti"

    };


    ImageView btncategoryview, btnbrandview, btnproductview, Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewmain);

        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);

            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(ViewMain.this, mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        btncategoryview = findViewById(R.id.btnCategoryView);
        btnbrandview = findViewById(R.id.btnBrandView);
        btnproductview = findViewById(R.id.btnProductView);
        Home = findViewById(R.id.home);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMain.this, SuperMain.class);
                startActivity(intent);
            }
        });

        btnproductview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewMain.this, ProductView.class);
                startActivity(i);
            }
        });

        btnbrandview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewMain.this, BrandView.class);
                startActivity(i);
            }
        });

        btncategoryview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewMain.this, CategoryView.class);
                startActivity(i);
            }
        });

    }
}