package com.gustu.adminmuka.interfaces;

import com.gustu.adminmuka.model.Admin;
import com.gustu.adminmuka.model.Petugas;

import java.util.List;

public interface LoginView {
    void _onLogin(List<Admin> admins);
    void  onPetugasLogin(List<Petugas> petugases);
    void _onFailure();
}
