package com.gustu.adminmuka.network;

import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Berkas;
import com.gustu.adminmuka.model.Petugas;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface API {
@GET("index.php/auth/apiLogin")
Call<List<Admin>> getAdmin(
            @Query("username") String username,
            @Query("password") String password
);
@GET("index.php/auth/apiLoginPetugas")
    Call<List<Petugas>> getPetugas(
        @Query("username") String username,
        @Query("password") String password
);

@GET("index.php/auth/apiLoginPetugas")
    Call<List<Petugas>> getAllPetugas();

@POST("index.php/petugas")
@FormUrlEncoded
    Call<List<Petugas>> addPetugas(
        @Field("username") String username,
        @Field("nama") String nama,
        @Field("telepon") String no_hp,
        @Field("password") String password
);
@PUT("index.php/petugas")
@FormUrlEncoded
    Call<List<Petugas>> editPetugas(
        @Field("username") String username,
        @Field("nama") String nama,
        @Field("telepon") String no_hp,
        @Field("password") String password
);

@DELETE("index.php/petugas")
@FormUrlEncoded
    Call<List<Petugas>> deletePetugas(
        @Field("username") String username
//        @Field("nama") String nama,
//        @Field("telepon") String no_hp,
//        @Field("password") String password
);
//Berkas
@GET("index.php/ukur")
Call<List<Berkas>> searchBerkas(
        @Query("no_berkas") String no_berkas
);
@GET("index.php/ukur")
Call<List<Berkas>> getAllBerkas();

    @POST("index.php/ukur")
    @FormUrlEncoded
    Call<List<Berkas>> addBerkas(
            @Field("no_berkas") String no_berkas,
            @Field("pemohon") String pemohon,
            @Field("no_hak") String no_hak,
            @Field("desa") String desa,
            @Field("kecamatan") String kecamatan,
            @Field("hari") String hari,
            @Field("tanggal") String tanggal,
            @Field("petugas") String petugas,
            @Field("no_hp") String no_hp
    );
};
