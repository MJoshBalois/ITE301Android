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

public class Main extends AppCompatActivity {

    private int[] mImages = new int[] {
            R.drawable.shimano, R.drawable.cannondale, R.drawable.giantbike, R.drawable.yeti
    };

    private String[] mImagesTitle = new String[] {
      "Shimano", "Cannondale", "Giant", "Yeti"

    };

    ImageView btncategory, btnbrand, btnproduct, Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
                Toast.makeText(Main.this, mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        btncategory = findViewById(R.id.btnCategory);
        btnbrand = findViewById(R.id.btnBrand);
        btnproduct = findViewById(R.id.btnProduct);
        Home = findViewById(R.id.home);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, SuperMain.class);
                startActivity(intent);
            }
        });

        btnproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, Product.class);
                startActivity(i);
            }
        });


        btnbrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, Brand.class);
                startActivity(i);
            }
        });

        btncategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this,Category.class);
                startActivity(i);
            }
        });


    }
}