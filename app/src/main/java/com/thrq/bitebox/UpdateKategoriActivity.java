package com.thrq.bitebox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateKategoriActivity extends AppCompatActivity {

    Button updateButton;
    EditText updateNama;
    String nama, key;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kategori);

        updateButton = findViewById(R.id.updateButton);
        updateNama = findViewById(R.id.updateNama);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            updateNama.setText(bundle.getString("Nama"));
            key = bundle.getString("Key");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Kategori").child(key);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(UpdateKategoriActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateKategoriActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        updateData();
        dialog.dismiss();
    }

    public void updateData() {


        nama = updateNama.getText().toString().trim();
        KategoriClass kategoriClass = new KategoriClass(nama);


        databaseReference.setValue(kategoriClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UpdateKategoriActivity.this, "Berhasil Update Kategori", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateKategoriActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}