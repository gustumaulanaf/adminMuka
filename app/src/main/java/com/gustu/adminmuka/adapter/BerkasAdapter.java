package com.gustu.adminmuka.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gustu.adminmuka.R;
import com.gustu.adminmuka.fragment.BerkasFragment;
import com.gustu.adminmuka.model.Berkas;
import com.gustu.adminmuka.network.BaseUrl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BerkasAdapter extends RecyclerView.Adapter<BerkasAdapter.ViewHolder> {
    List<Berkas> berkasList;
    Activity activity;
    BerkasFragment berkasFragment;
    BaseUrl baseUrl;

    public void setBaseUrl(BaseUrl baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setContext(Activity activity) {
        this.activity = activity;
    }

    public void setBerkasFragment(BerkasFragment berkasFragment) {
        this.berkasFragment = berkasFragment;
    }

    public BerkasAdapter(List<Berkas> berkasList) {
        this.berkasList = berkasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berkas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final Berkas berkas = berkasList.get(position);
        if ((position+1)%2==0){
            holder.layout.setBackgroundColor(Color.LTGRAY);
        }
        else{
            holder.layout.setBackgroundColor(Color.WHITE);
        }
        holder.namaPemohon.setText(berkas.getPemohon());
        holder.noBerkas.setText(berkas.getNoBerkas());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // berkasFragment = new BerkasFragment();
                berkasFragment.showEditDialog(activity,berkasList.get(position).getPemohon(),berkasList.get(position).getNoBerkas(),berkasList.get(position).getNoHak(),berkasList.get(position).getDesa(),berkasList.get(position).getKecamatan(),berkasList.get(position).getHari(),berkasList.get(position).getTanggal(),berkasList.get(position).getPetugas(),berkasList.get(position).getNoHp(),berkasList.get(position).getPermasalahan());
            }
        });
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(activity);
                alertdialog.setTitle("Peringatan");
                alertdialog.setMessage("Apakah Anda Yakin Ingin Menghapus Berkas No :" + berkasList.get(position).getNoBerkas());
                alertdialog.setPositiveButton("YA",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                berkasFragment.hapusBerkas(berkasList.get(position).getNoBerkas());
                            }
                        });
              alertdialog.setNegativeButton("BATAL",
                      new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              dialogInterface.dismiss();
                          }
                      });
              alertdialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (berkasList !=null){
            return berkasList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layoutitemberkas)
        LinearLayout layout;
        @BindView(R.id.tvNoberkas)
        TextView noBerkas;
        @BindView(R.id.tvNamaPemohon)
        TextView namaPemohon;
        @BindView(R.id.imgEdit)
        ImageView edit;
        @BindView(R.id.imgHapus)
        ImageView hapus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
