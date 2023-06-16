package com.uas.cafe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uas.cafe.R;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yAlamat, yDeskripsi, yRating;
    private EditText etNama, etAlamat, etDeskripsi, etRating;
    private Button btnUbah;
    private String nama, alamat, deskripsi, rating;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        //tangkap kiriman dari sbelah
        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yAlamat = ambil.getStringExtra("xAlamat");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");
        yRating = ambil.getStringExtra("xRating");

        //findview kan semua view/widget yang dipakai
        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etRating = findViewById(R.id.et_rating);
        btnUbah = findViewById(R.id.btn_ubah);

        //set kiriman yang sudah ditangkap tadi, ke view/widget yang sudah difindview tadi
        etNama.setText(yNama);
        etAlamat.setText(yAlamat);
        etDeskripsi.setText(yDeskripsi);
        etRating.setText(yRating);


        //mulai mainkan kalau tombol ubah diklik
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                rating = etRating.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if (alamat.trim().isEmpty()){
                    etAlamat.setError("Alamat Tidak Boleh Kosong");
                }
                else if (deskripsi.trim().isEmpty()){
                    etDeskripsi.setError("Deskripsi Tidak Boleh Kosong");
                }
                else if (rating.trim().isEmpty()){
                    etRating.setError("Deskripsi Tidak Boleh Kosong");
                }
                else {

                }
            }
        });
    }
}
