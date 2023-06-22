package com.uas.cafe.API;


import com.uas.cafe.Model.ModelRespon;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    @GET("retrieve.php")
    Call<ModelRespon> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelRespon> ardCreate(
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("deskripsi") String deskripsi,
            @Field("rating") String rating,
            @Field("koordinat") String koordinat
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelRespon> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("deskripsi") String deskripsi,
            @Field("rating") String rating,
            @Field("koordinat") String koordinat
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelRespon> ardDelete(
            @Field("id") String id
    );
}

