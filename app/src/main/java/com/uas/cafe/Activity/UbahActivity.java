package com.uas.cafe.Activity;

import android.content.Intent;
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

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yAlamat, yDeskripsi, yRating, yKoordinat;
    private EditText etNama, etAlamat, etDeskripsi, etRating, etKoordinat;
    private Button btnUbah;
    private String nama, alamat, deskripsi, rating, koordinat;
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
        yKoordinat = ambil.getStringExtra("xKoordinat");

        //findview kan semua view/widget yang dipakai
        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etRating = findViewById(R.id.et_rating);
        etKoordinat = findViewById(R.id.et_koordinat);
        btnUbah = findViewById(R.id.btn_ubah);

        //set kiriman yang sudah ditangkap tadi, ke view/widget yang sudah difindview tadi
        etNama.setText(yNama);
        etAlamat.setText(yAlamat);
        etDeskripsi.setText(yDeskripsi);
        etRating.setText(yRating);
        etKoordinat.setText(yKoordinat);


        //mulai mainkan kalau tombol ubah diklik
        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                rating = etRating.getText().toString();
                koordinat = etKoordinat.getText().toString();

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
                else if (rating.trim().isEmpty()) {
                    etRating.setError("Deskripsi Tidak Boleh Kosong");
                }else {
                    ubahCafe();
                }
            }
        });
    }
    private void ubahCafe(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelRespon> proses = API.ardUpdate(yId, nama, alamat, deskripsi, rating, koordinat);

        proses.enqueue(new Callback<ModelRespon>() {
            @Override
            public void onResponse(Call<ModelRespon> call, Response<ModelRespon> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this,"Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelRespon> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                Log.d("Disini","Errornya itu: "+ t.getMessage());

            }
        });
    }
}

