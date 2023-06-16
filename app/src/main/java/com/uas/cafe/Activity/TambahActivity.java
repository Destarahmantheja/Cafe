package com.uas.cafe.Activity;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uas.cafe.API.APIRequestData;
import com.uas.cafe.API.RetroServer;
import com.uas.cafe.Model.ModelRespon;
import com.uas.cafe.R;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etDeskripsi, etRating;
    private Button btnTambah;
    private String nama, alamat, deskripsi, rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etRating = findViewById(R.id.et_rating);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama  = etNama.getText().toString();
                alamat  = etAlamat.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                rating = etRating.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("nama tidak boleh kosong");
                }
                else if (alamat.trim().isEmpty()) {
                    etAlamat.setError("sejarah tidak boleh kosong");
                }
                else if (deskripsi.trim().isEmpty()) {
                    etDeskripsi.setError("luas tidak boleh kosong");
                }
                else if (rating.trim().isEmpty()) {
                    etRating.setError("kota tidak boleh kosong");
                }

            }
        });
    }
}