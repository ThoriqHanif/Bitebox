package com.thrq.bitebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class BottomSheetLayout extends AppCompatActivity {

    LinearLayout addKategori, addResto, addFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_layout);

        addKategori = findViewById(R.id.addKategories);
        addKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetLayout.this, AddKategoriActivity.class));
            }
        });


        addResto = findViewById(R.id.addRestoran);

        addResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetLayout.this, AddRestoranAcitivity.class));
            }
        });

        addFood = findViewById(R.id.addMakanan);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BottomSheetLayout.this, AddFoodActivity.class));
            }
        });
    }
}