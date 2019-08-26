package com.gustu.adminmuka.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gustu.adminmuka.interfaces.LoginView;
import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Petugas;
import com.gustu.adminmuka.network.BaseUrl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
   LoginView loginView;
    Context context;
    BaseUrl baseUrl;
    List<Admin> adminList = new ArrayList<>();
    List<Petugas> petugasList = new ArrayList<>();

    public void setContext(Context context) {
        this.context = context;
    }
    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        if (baseUrl==null){
            this.baseUrl=new BaseUrl();
        }
    }
    public  void gotoLogin(String username ,String password){
        baseUrl.getApi().getAdmin(username,password).enqueue(new Callback<List<Admin>>() {
            @Override
            public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                if (response.isSuccessful()){
                    adminList = response.body();

                    if (adminList.size()>0){
                        loginView._onLogin(adminList);
                    }
                    else {
                        loginView._onFailure();
                    }
                   // progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Admin>> call, Throwable t) {
                  //  progressDialog.dismiss();
                    Toast.makeText(context,"Tidak ada Koneksi Internet",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void gotoLoginPetugas(String username, String password) {
        baseUrl.getApi().getPetugas(username,password).enqueue(new Callback<List<Petugas>>() {
            @Override
            public void onResponse(Call<List<Petugas>> call, Response<List<Petugas>> response) {
                    petugasList = response.body();
                    loginView.onPetugasLogin(petugasList);
            }

            @Override
            public void onFailure(Call<List<Petugas>> call, Throwable t) {

            }
        });
    }
}
