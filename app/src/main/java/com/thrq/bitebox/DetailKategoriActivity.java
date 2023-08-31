package com.thrq.bitebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailKategoriActivity extends AppCompatActivity {

    TextView detailNama;
    FloatingActionButton dltbtn, editbtn;
    String key = "";

    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailKategoriActivity.this, MainActivity.class));
            }
        });

        detailNama = findViewById(R.id.detailNama);
        dltbtn = findViewById(R.id.deleteButton);
        editbtn = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailNama.setText(bundle.getString("Nama"));
            key = bundle.getString("Key");

        }

        dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key != null) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Kategori");
                    reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(DetailKategoriActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    });
                } else {
                    // Handle the case when key is null
                    Toast.makeText(DetailKategoriActivity.this, "Data Gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        dltbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Kategori");
//
//                // Menghapus data dari Database
//                reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(DetailKategoriActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();
//                    }
//                });
//            }
//        });



        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailKategoriActivity.this, UpdateKategoriActivity.class)
                        .putExtra("Title", detailNama.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}