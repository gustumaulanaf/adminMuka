package com.gustu.adminmuka.presenter;

import android.util.Log;

import com.gustu.adminmuka.interfaces.BerkasView;
import com.gustu.adminmuka.model.Berkas;
import com.gustu.adminmuka.model.Petugas;
import com.gustu.adminmuka.network.BaseUrl;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerkasPresenter {
    private BaseUrl baseUrl;
    private BerkasView berkasView;
    private List<Berkas> berkasList = new ArrayList<>();
    public BerkasPresenter(BerkasView berkasView) {
        this.berkasView = berkasView;
        if (baseUrl == null) {
            this.baseUrl = new BaseUrl();
        }
    }

    public void getAllBerkas() {
        baseUrl.getApi().getAllBerkas().enqueue(new Callback<List<Berkas>>() {
            @Override
            public void onResponse(Call<List<Berkas>> call, Response<List<Berkas>> response) {
                if (response.isSuccessful()) {
                    berkasList = response.body();
                    for (Berkas berkas : berkasList){
                        Log.d("HASIL", "onResponse: "+berkas.getPemohon());
                    }
                    berkasView._onBerkasLoad(berkasList);
                }
            }

            @Override
            public void onFailure(Call<List<Berkas>> call, Throwable t) {
                berkasView._onBerkasEmpty();
            }
        });
    }

    public void searchBerkas(String noberkas) {
        baseUrl.getApi().searchBerkas(noberkas).enqueue(new Callback<List<Berkas>>() {
            @Override
            public void onResponse(Call<List<Berkas>> call, Response<List<Berkas>> response) {
                if (response.isSuccessful()) {
                    berkasList = response.body();
                    if (berkasList != null) {
                        berkasView._onBerkasLoad(berkasList);
                    } else {
                        berkasView._onBerkasEmpty();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Berkas>> call, Throwable t) {
                berkasView._onBerkasEmpty();
            }
        });
    }

    public void addBerkas(String sNoberkas, String sNamaPemohon, String snoHak, String sDesa, String sKecamatan, String sHari, String sTanggal, String sPetugas, String sNoHp) {
        //Log.d("HASIL", "onResponse: "+"sukses");
        baseUrl.getApi().addBerkas(sNoberkas,sNamaPemohon,snoHak,sDesa,sKecamatan,sHari,sTanggal,sPetugas,sNoHp).enqueue(new Callback<Berkas>() {
            @Override
            public void onResponse(Call<Berkas> call, Response<Berkas> response) {
                if (response.isSuccessful()){

                    berkasView._onBerkasAdd();
                    //Log.d("HASIL", "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Berkas> call, Throwable t) {
                berkasView._onBerkasFailedAdd();
            }
        });
    //    Log.d("BerkasADD", "onResponse: "+"Success");
    }
    public  void editBerkas(String sNoberkas, String sNamaPemohon, String snoHak, String sDesa, String sKecamatan, String sHari, String sTanggal, String sPetugas, String sNoHp){
     //   Log.d("NOBERKAS", "editBerkas: "+sNoberkas+sNamaPemohon+snoHak+sDesa+sKecamatan+sHari+sTanggal+sPetugas+sNoHp);
        baseUrl.getApi().editBerkas(sNoberkas,sNamaPemohon,snoHak,sDesa,sKecamatan,sHari,sTanggal,sPetugas,sNoHp).enqueue(new Callback<List<Berkas>>() {
            @Override
            public void onResponse(Call<List<Berkas>> call, Response<List<Berkas>> response) {
                if (response.isSuccessful()){
                    berkasView._onEditBerkas();
                }
            }

            @Override
            public void onFailure(Call<List<Berkas>> call, Throwable t) {
                berkasView._onBerkasGagalEdit();
                Log.d("onFailed", "onFailure: "+t);
            }
        });
    }
    public void hapusBerkas(String no_berkas){
        Log.d("DELETE", "hapusBerkas: "+no_berkas);
        baseUrl.getApi().deleteBerkas(no_berkas).enqueue(new Callback<List<Berkas>>() {
            @Override
            public void onResponse(Call<List<Berkas>> call, Response<List<Berkas>> response) {
                if (response.isSuccessful()){
                    berkasView._onBerkasTerhapus();
                    Log.d("DELETE", "onResponse: "+"Berhasil hapus");
                }
            }

            @Override
            public void onFailure(Call<List<Berkas>> call, Throwable t) {
                Log.d("DELETE", "onResponse: "+"Gagal hapus");
                    berkasView._onBerkasGagalHapus();

            }
        });
    }
}
