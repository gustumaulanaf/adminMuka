package com.gustu.adminmuka.interfaces;

import android.view.View;

import com.gustu.adminmuka.model.Berkas;

import java.util.List;

public interface BerkasView {
    View _onBerkasLoad (List<Berkas> berkasList);
    View _onBerkasEmpty();
    View _onBerkasAdd();
    View _onBerkasFailedAdd();
}
