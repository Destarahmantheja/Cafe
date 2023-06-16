package com.uas.cafe.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
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

import java.util.ArrayList;
import java.util.List;

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

}
