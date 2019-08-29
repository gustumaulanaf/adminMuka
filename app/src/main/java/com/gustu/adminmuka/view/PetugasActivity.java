package com.gustu.adminmuka.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gustu.adminmuka.R;
import com.gustu.adminmuka.fragment.BerkasFragment;
import com.gustu.adminmuka.fragment.HomeFragment;
import com.gustu.adminmuka.fragment.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetugasActivity extends AppCompatActivity implements BerkasFragment.OnFragmentInteractionListener {
    @BindView(R.id.buttonBottomPetugas)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas);
        ButterKnife.bind(this);
        gotoFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.menuProfilePetugas:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.menuBerkasPetugas:
                        fragment = new BerkasFragment();
                        break;
                    case R.id.menuUtamaPetugas:
                        fragment = new HomeFragment();
                        break;

                }
                return gotoFragment(fragment);
            }
        });
    }

    public boolean gotoFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameIsiPetugas, fragment).commit();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
