package com.gustu.adminmuka.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gustu.adminmuka.R;
import com.gustu.adminmuka.interfaces.ProfileView;
import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Petugas;
import com.gustu.adminmuka.presenter.ProfilePresenter;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;
import com.gustu.adminmuka.view.LoginActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileView {
    @BindView(R.id.tvNama)
    TextView nama;
    @BindView(R.id.tvNohp)
    TextView noHP;
    @BindView(R.id.tvUsername)
    TextView username;
    @BindView(R.id.tvNoHp1)
    TextView tvNoHp1;
    @BindView(R.id.btKeluar)
    Button keluar;
    private ProfilePresenter profilePresenter;
    private View view;
    private ProgressDialog progressDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Tunggu");
        progressDialog.setMessage("Memuat...");
        progressDialog.show();
        profilePresenter = new ProfilePresenter(this);
        profilePresenter.getProfile();
        return view;
    }

    @OnClick(R.id.btKeluar)
    void logout(View view) {
        SharedPrefUtil.saveBoolean("login", false);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public View _onPetugas(List<Petugas> petugases) {
        progressDialog.dismiss();
        nama.setText(petugases.get(0).getNama());
        username.setText(petugases.get(0).getUsername());
        noHP.setText(petugases.get(0).getTelepon());
        return view;
    }

    @Override
    public View _onAdmin(List<Admin> admins) {
        progressDialog.dismiss();
        nama.setText(admins.get(0).getNama());
        noHP.setVisibility(View.INVISIBLE);
        tvNoHp1.setVisibility(View.INVISIBLE);
        username.setText(admins.get(0).getUsername());
        return view;
    }
}
