package com.uas.cafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.cafe.API.APIRequestData;
import com.uas.cafe.API.RetroServer;
import com.uas.cafe.Activity.MainActivity;
import com.uas.cafe.Activity.UbahActivity;
import com.uas.cafe.Model.ModelCafe;
import com.uas.cafe.Model.ModelRespon;
import com.uas.cafe.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCafe extends RecyclerView.Adapter<AdapterCafe.VHCafe> {
    private Context ctx;
    private List<ModelCafe> listCafe;

    public AdapterCafe(Context ctx, List<ModelCafe> listCafe) {
        this.ctx = ctx;
        this.listCafe = listCafe;
    }
    public VHCafe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_cafe, parent, false);
        return new VHCafe(varView);
    }
    @Override
    public void onBindViewHolder(@NonNull VHCafe holder, int position) {
        ModelCafe MC = listCafe.get(position);
        holder.tvId.setText(MC.getId());
        holder.tvNama.setText(MC.getNama());
        holder.tvAlamat.setText(MC.getAlamat());
        holder.tvDeskripsi.setText(MC.getDeskripsi());
        holder.tvRating.setText(MC.getRating());
    }
    @Override
    public int getItemCount() {
        return listCafe.size();
    }
    public class VHCafe extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvAlamat, tvDeskripsi, tvRating;
        Button btnHapus, btnUbah;

        public VHCafe(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvRating = itemView.findViewById(R.id.tv_rating);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnUbah = itemView.findViewById(R.id.btn_ubah);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteCafe(tvId.getText().toString());
                }
            });

            btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xAlamat", tvAlamat.getText().toString());
                    pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    pindah.putExtra("xRating", tvRating.getText().toString());
                    ctx.startActivity(pindah);

                }
            });
        }

        void deleteCafe(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelRespon> proses = API.ardDelete(id);
            proses.enqueue(new Callback<ModelRespon>() {
                @Override
                public void onResponse(retrofit2.Call<ModelRespon> call, Response<ModelRespon> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "kode: " + kode+ "pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveCafe();
                }

                @Override
                public void onFailure(retrofit2.Call<ModelRespon> call, Throwable t) {
                    Toast.makeText(ctx, "Error! Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}








