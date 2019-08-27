package com.gustu.adminmuka.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gustu.adminmuka.R;
import com.gustu.adminmuka.adapter.BerkasAdapter;
import com.gustu.adminmuka.interfaces.BerkasView;
import com.gustu.adminmuka.model.Berkas;
import com.gustu.adminmuka.presenter.BerkasPresenter;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerkasFragment extends Fragment implements BerkasView {
    BerkasAdapter berkasAdapter;
    BerkasPresenter berkasPresenter;
    @BindView(R.id.rvBerkas)
    RecyclerView recyclerView;
    @BindView(R.id.searchBerkas)
    androidx.appcompat.widget.SearchView searchView;
    @BindView(R.id.fabTambah)
    FloatingActionButton fabTambah;
    LinearLayoutManager linearLayoutManager;
    AppCompatDialog appCompatDialog;
    EditText namaPemohon,noBerkas,noHak,desa,hari,tanggal,no_hp,kecamatan,petugas;
    String sNamaPemohon,sNoberkas,snoHak,sDesa,sHari,sTanggal,sNoHp,sKecamatan,sPetugas;

    View view;
     FragmentManager fm;
    public static final int REQUEST_CODE = 11;
    String selectedDate=""
            ,selectedhari="";
    ProgressDialog progressDialog;
    private OnFragmentInteractionListener mListener;

    public BerkasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_berkas, container, false);
       fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        berkasPresenter = new BerkasPresenter(this);
        progressDialog.show();
        berkasPresenter.getAllBerkas();
        ButterKnife.bind(this, view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                berkasPresenter.searchBerkas(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                berkasPresenter.searchBerkas(newText);
                return false;
            }
        });
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
        return view;
    }

    private void showAddDialog() {
        appCompatDialog = new AppCompatDialog(getActivity());
        appCompatDialog.setContentView(R.layout.dialog_add_berkas);
        appCompatDialog.show();

       Button btTambah;
        ImageButton btTglUkur;
        namaPemohon = appCompatDialog.findViewById(R.id.etNamaPemohon);
        noBerkas = appCompatDialog.findViewById(R.id.etNoBerkas);
        noHak = appCompatDialog.findViewById(R.id.etNoHak);
        desa = appCompatDialog.findViewById(R.id.etDesaKelurahan);
        hari = appCompatDialog.findViewById(R.id.etHari);
        tanggal = appCompatDialog.findViewById(R.id.etTglUkur);
        tanggal.setText(selectedDate);
        hari.setText(selectedhari);
        no_hp = appCompatDialog.findViewById(R.id.etNoHP);
        no_hp.setText(SharedPrefUtil.getString("no_hp"));
        kecamatan = appCompatDialog.findViewById(R.id.etKecamatan);
        petugas = appCompatDialog.findViewById(R.id.etNamaPetugas);
        petugas.setText(SharedPrefUtil.getString("nama"));
        btTglUkur = appCompatDialog.findViewById(R.id.btTglUkur);
        btTambah= appCompatDialog.findViewById(R.id.btTambah);

        btTglUkur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(BerkasFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");
            }
        });
        btTambah.setOnClickListener(view -> {
            sNamaPemohon = namaPemohon.getText().toString();
            sNoberkas = noBerkas.getText().toString();
            snoHak = noHak.getText().toString();
            sDesa = desa.getText().toString();
            sHari = hari.getText().toString();
            sTanggal = tanggal.getText().toString();
            sKecamatan = kecamatan.getText().toString();
            sPetugas= petugas.getText().toString();
            sNoHp = no_hp.getText().toString();
           berkasPresenter.addBerkas(sNoberkas, sNamaPemohon, snoHak, sDesa, sKecamatan, sHari, sTanggal, sPetugas, sNoHp);
            progressDialog.setMessage("Menambahkan...");
            progressDialog.show();
        });
    }

    @Override
    public View _onBerkasLoad(List<Berkas> berkasList) {
        progressDialog.dismiss();
        berkasAdapter = new BerkasAdapter(berkasList);
        recyclerView.setAdapter(berkasAdapter);
        return view;
    }

    @Override
    public View _onBerkasEmpty() {
        Toast.makeText(getActivity(),"Tidak dapat Menemukan Berkas",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public View _onBerkasAdd() {
        progressDialog.dismiss();
        appCompatDialog.dismiss();
        berkasPresenter.getAllBerkas();
        berkasAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(),"Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public View _onBerkasFailedAdd() {
        progressDialog.dismiss();
        Toast.makeText(getActivity(),"Gagal Menambahkan",Toast.LENGTH_SHORT).show();
                return view;
    }
//DatePicker
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    // check for the results
    if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
        // get date from string
        selectedDate = data.getStringExtra("selectedDate");
        selectedhari = data.getStringExtra("hari");

        Log.d("SELECTED DATE", "onActivityResult: "+selectedDate);
        Log.d("SELECTED HARI", "onActivityResult: "+selectedhari);
        hari.setText(selectedhari);
        tanggal.setText(selectedDate);
        // set the value of the editText
  //      dateOfBirthET.setText(selectedDate);
    }
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
