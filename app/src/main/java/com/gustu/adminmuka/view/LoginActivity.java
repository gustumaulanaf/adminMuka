package com.gustu.adminmuka.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gustu.adminmuka.R;
import com.gustu.adminmuka.interfaces.LoginView;
import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Petugas;
import com.gustu.adminmuka.presenter.LoginPresenter;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    //ViewInject
    @BindView(R.id.spMode)
    Spinner spinner;
    @BindView(R.id.etUsername)
    EditText ETusername;
    @BindView(R.id.etPassword)
    EditText ETpassword;
    @BindView(R.id.btLogin)
    Button BTLogin;
    LoginPresenter loginPresenter;
    String selectSpinner;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        initPresenter();
        initView();

    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Login..");
        progressDialog.setCancelable(false);
        spinner = findViewById(R.id.spMode);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSpinner = spinner.getItemAtPosition(i).toString();
                SharedPrefUtil.saveString("modeSpinner", selectSpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(LoginActivity.this, "Login sebagai tidak boleh Kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.btLogin)
    public void login(){
        String username, password;
        username = ETusername.getText().toString();
        password = ETpassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Form Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            if (SharedPrefUtil.getString("modeSpinner").equals("Admin")) {
                loginPresenter.gotoLogin(username, password);
            } else if (SharedPrefUtil.getString("modeSpinner").equals("Petugas")) {

                loginPresenter.gotoLoginPetugas(username, password);
            }
        }
    }
    private void initPresenter() {
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setContext(this);
    }

    @Override
    public void _onLogin(List<Admin> admins) {
        progressDialog.dismiss();
        for (Admin admin : admins) {
            if (admins.size() != 0) {
                SharedPrefUtil.saveString("nama", admin.getNama());
                SharedPrefUtil.saveString("username",admin.getUsername());
                SharedPrefUtil.saveString("password",admin.getPassword());
                SharedPrefUtil.saveBoolean("login",true);
                SharedPrefUtil.saveBoolean("admin",true);
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPetugasLogin(List<Petugas> petugases) {
        progressDialog.dismiss();
        for (Petugas petugas : petugases){
            if (petugases.size()!=0){
                SharedPrefUtil.saveString("nama",petugas.getNama());
                SharedPrefUtil.saveString("username",petugas.getUsername());
                SharedPrefUtil.saveString("password",petugas.getPassword());
                SharedPrefUtil.saveString("no_hp",petugas.getTelepon());
                SharedPrefUtil.saveBoolean("login",true);
                SharedPrefUtil.saveBoolean("admin",false);
                startActivity(new Intent(LoginActivity.this,PetugasActivity.class));
                finish();
            }
            else {
                Toast.makeText(LoginActivity.this,"Login Gagal",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void _onFailure() {
        progressDialog.dismiss();
        Toast.makeText(this, "Username / Password Salah", Toast.LENGTH_SHORT).show();
    }
}
