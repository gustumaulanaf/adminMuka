package com.gustu.adminmuka.interfaces;

import android.view.View;

import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Petugas;

import java.util.List;

public interface ProfileView {
    View _onPetugas(List<Petugas> petugases);
    View _onAdmin(List<Admin> admins);
}
