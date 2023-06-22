package com.uas.cafe.Activity;

import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etDeskripsi, etRating, etKoordinat;
    private Button btnTambah;
    private String nama, alamat, deskripsi, rating, koordinat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etRating = findViewById(R.id.et_rating);
        etKoordinat = findViewById(R.id.et_koordinat);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                rating = etRating.getText().toString();
                koordinat = etKoordinat.getText().toString();

                if (nama.trim().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                } else if (alamat.trim().isEmpty()) {
                    etAlamat.setError("Alamat tidak boleh kosong");
                } else if (deskripsi.trim().isEmpty()) {
                    etDeskripsi.setError("Deskripsi tidak boleh kosong");
                } else if (rating.trim().isEmpty()) {
                    etRating.setError("Rating tidak boleh kosong");
                } else if (koordinat.trim().isEmpty()) {
                    etKoordinat.setError("Koordinat tidak boleh kosong");
                } else {
                    tambahCafe();
                }
            }

        });
    }
    private void tambahCafe () {
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        retrofit2.Call<ModelRespon> proses = API.ardCreate(nama, alamat, deskripsi, rating, koordinat);

        proses.enqueue(new Callback<ModelRespon>() {
            @Override
            public void onResponse(retrofit2.Call<ModelRespon> call, Response<ModelRespon> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelRespon> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                Log.d("Disini", "Errornya itu: " + t.getMessage());

            }
        });
    }
}