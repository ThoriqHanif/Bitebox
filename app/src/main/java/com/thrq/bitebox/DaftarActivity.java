package com.thrq.bitebox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class DaftarActivity extends AppCompatActivity {

    TextView tvLogin;
    Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DaftarActivity.this, LoginActivity.class));
            }
        });

        btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DaftarActivity.this, VerificationAccount.class));
            }
        });
    }
}