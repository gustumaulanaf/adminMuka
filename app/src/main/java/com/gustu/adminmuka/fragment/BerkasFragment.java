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
import com.gustu.adminmuka.network.BaseUrl;
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
    BaseUrl baseUrl;
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
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        berkasPresenter = new BerkasPresenter(this);
        progressDialog.show();
        berkasPresenter.getAllBerkas();
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
                showAddDialog(getActivity());
            }
        });
        return view;
    }

    public void showAddDialog(Activity activity) {
        appCompatDialog = new AppCompatDialog(activity);
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
public void showEditDialog(Activity activity, String editNamaPemohon,String editNoberkas,String editnoHak,String editDesa,String editKecamatan,String editHari,String editTanggal,String editPetugas,String editNoHp ) {
        appCompatDialog = new AppCompatDialog(activity);
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

        no_hp = appCompatDialog.findViewById(R.id.etNoHP);
        kecamatan = appCompatDialog.findViewById(R.id.etKecamatan);
        petugas = appCompatDialog.findViewById(R.id.etNamaPetugas);
       // petugas.setText(SharedPrefUtil.getString("nama"));
        namaPemohon.setText(editNamaPemohon);
        noBerkas.setText(editNoberkas);
        noHak.setText(editnoHak);
        desa.setText(editDesa);
        no_hp.setText(editNoHp);
        tanggal.setText(editTanggal);
        kecamatan.setText(editKecamatan);
        hari.setText(editHari);
        petugas.setText(editPetugas);
        btTglUkur = appCompatDialog.findViewById(R.id.btTglUkur);
        btTambah= appCompatDialog.findViewById(R.id.btTambah);
        btTambah.setText("Update");
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
            berkasPresenter = new BerkasPresenter(this);
            String editNamaPemohon2 = namaPemohon.getText().toString();
            String editNoberkas2 = noBerkas.getText().toString();
            String editnoHak2 = noHak.getText().toString();
            String editDesa2 = desa.getText().toString();
            String editHari2 = hari.getText().toString();
            String editTanggal2 = tanggal.getText().toString();
            String editKecamatan2 = kecamatan.getText().toString();
            String editPetugas2 = petugas.getText().toString();
            String editNoHp2 = no_hp.getText().toString();
            Log.d("HASILEDIT", "showEditDialog: "+editNamaPemohon2);
            baseUrl = new BaseUrl();
            berkasAdapter.setBaseUrl(baseUrl);
            berkasPresenter = new BerkasPresenter(this);
          berkasPresenter.editBerkas(editNoberkas2, editNamaPemohon2, editnoHak2, editDesa2, editKecamatan2, editHari2, editTanggal2, editPetugas2, editNoHp2);
            progressDialog.setMessage("Mengupdate...");
            progressDialog.show();
        });
    }

    @Override
    public View _onBerkasLoad(List<Berkas> berkasList) {
        progressDialog.dismiss();
        berkasAdapter = new BerkasAdapter(berkasList);
        berkasAdapter.setContext(getActivity());
        berkasAdapter.setBerkasFragment(this);
        recyclerView.setAdapter(berkasAdapter);
        return view;
    }

    @Override
    public View _onBerkasEmpty() {
        progressDialog.dismiss();
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
     //   Log.d("GAGAL", "_onBerkasFailedAdd: "+"GAGAL");
//        Toast.makeText(getActivity(),"Gagal Menambahkan",Toast.LENGTH_SHORT).show();
                return view;
    }

    @Override
    public View _onEditBerkas() {
        Toast.makeText(getActivity(),"Berhasil Di Ubah",Toast.LENGTH_SHORT).show();
        berkasAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public View _onBerkasGagalEdit() {
        return view;
    }

    @Override
    public View _onBerkasTerhapus(
    ) {
        Toast.makeText(getActivity(),"Berkas Terhapus",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public View _onBerkasGagalHapus() {
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

    public void hapusBerkas(String noBerkas) {
        berkasPresenter.hapusBerkas(noBerkas);
        berkasPresenter.getAllBerkas();
        berkasAdapter.notifyDataSetChanged();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
