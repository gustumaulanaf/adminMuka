package com.gustu.adminmuka.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gustu.adminmuka.R;
import com.gustu.adminmuka.fragment.BerkasFragment;
import com.gustu.adminmuka.fragment.ProfileFragment;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminActivity extends AppCompatActivity {
@BindView(R.id.buttonBottom)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.menuProfileAdmin :
                        fragment = new ProfileFragment() ;
                        break;
                    case R.id.menuBerkas:
                        fragment=new BerkasFragment();
                        break;
                    
                }
                return gotoFragment(fragment);
            }
        });
    }
    public boolean gotoFragment(Fragment fragment){
        if (fragment!=null){

            getSupportFragmentManager().beginTransaction().replace(R.id.frameIsi,fragment).commit();
            return true;
        }
        else {
            return false;
        }
    }
}
