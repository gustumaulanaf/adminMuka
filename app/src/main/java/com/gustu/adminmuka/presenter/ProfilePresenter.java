package com.gustu.adminmuka.presenter;

import android.util.Log;

import com.gustu.adminmuka.interfaces.ProfileView;
import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Petugas;
import com.gustu.adminmuka.network.BaseUrl;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private ProfileView profileView;
    private BaseUrl baseUrl;
    private List<Petugas> petugasList = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private boolean mode = SharedPrefUtil.getBoolean("admin");
    public ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
        if (baseUrl==null){
            this.baseUrl = new BaseUrl();
        }
    }
    public  void getProfile(){
       if (mode){
           baseUrl.getApi().getAdmin(SharedPrefUtil.getString("username"),SharedPrefUtil.getString("password")).enqueue(new Callback<List<Admin>>() {
               @Override
               public void onResponse(Call<List<Admin>> call, Response<List<Admin>> response) {
                   if (response.isSuccessful()){
                       admins = response.body();
                       profileView._onAdmin(admins);
                   }
               }

               @Override
               public void onFailure(Call<List<Admin>> call, Throwable t) {
               }
           });
       }
       else {
           baseUrl.getApi().getPetugas(SharedPrefUtil.getString("username"),SharedPrefUtil.getString("password")).enqueue(new Callback<List<Petugas>>() {
               @Override
               public void onResponse(Call<List<Petugas>> call, Response<List<Petugas>> response) {
                if (response.isSuccessful()){
                    Log.d("usernamepassword", "onResponse: "+SharedPrefUtil.getString("nama")+SharedPrefUtil.getString("password"));

                    petugasList = response.body();
                    profileView._onPetugas(petugasList);
                }
               }

               @Override
               public void onFailure(Call<List<Petugas>> call, Throwable t) {

               }
           });
       }
    }
}
