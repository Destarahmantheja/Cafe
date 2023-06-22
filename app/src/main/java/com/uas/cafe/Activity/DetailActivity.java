package com.uas.cafe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.uas.cafe.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvId, tvNama, tvAlamat, tvDeskripsi, tvRating, tvKoordinat;
    private String detId, detNama, detAlamat, detDeskripsi, detRating, detKoordinat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvId = findViewById(R.id.tv_id);
        tvNama = findViewById(R.id.tv_nama);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvRating = findViewById(R.id.tv_rating);
        tvKoordinat = findViewById(R.id.tv_koordinat);


        Intent terima = getIntent();
        detId = terima.getStringExtra("xId");
        detNama = terima.getStringExtra("xNama");
        detAlamat = terima.getStringExtra("xAlamat");
        detDeskripsi = terima.getStringExtra("xDeskripsi");
        detRating = terima.getStringExtra("xRating");
        detKoordinat = terima.getStringExtra("xKoordinat");


        tvId.setText(detId);
        tvNama.setText(detNama);
        tvAlamat.setText(detAlamat);
        tvDeskripsi.setText(detDeskripsi);
        tvRating.setText(detRating);
        tvKoordinat.setText(detKoordinat);

    }
}
