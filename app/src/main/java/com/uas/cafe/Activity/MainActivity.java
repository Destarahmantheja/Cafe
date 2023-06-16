package com.uas.cafe.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uas.cafe.API.APIRequestData;
import com.uas.cafe.API.RetroServer;
import com.uas.cafe.Adapter.AdapterCafe;
import com.uas.cafe.Model.ModelCafe;
import com.uas.cafe.Model.ModelRespon;
import com.uas.cafe.R;

import com.uas.cafe.Activity.MainActivity;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCafe;
    private FloatingActionButton fabTambah;
    private ProgressBar pbCafe;
    private RecyclerView.Adapter adCafe;

    private RecyclerView.LayoutManager lmCafe;
    private List<ModelCafe> listCafe = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCafe = findViewById(R.id.rv_cafe);
        fabTambah = findViewById(R.id.fab_tambah);
        pbCafe = findViewById(R.id.pb_cafe);

        lmCafe = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCafe.setLayoutManager(lmCafe);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveCafe();
    }

    public void retrieveCafe(){
        pbCafe.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        retrofit2.Call<ModelRespon> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelRespon>() {
            @Override
            public void onResponse(retrofit2.Call<ModelRespon> call, Response<ModelRespon> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listCafe = response.body().getData();

                adCafe = new AdapterCafe(MainActivity.this, listCafe);
                rvCafe.setAdapter(adCafe);
                adCafe.notifyDataSetChanged();

                pbCafe.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelRespon> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                pbCafe.setVisibility(View.GONE);

            }
        });

    }

}

