package com.gustu.adminmuka.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gustu.adminmuka.R;
import com.gustu.adminmuka.model.Berkas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BerkasAdapter extends RecyclerView.Adapter<BerkasAdapter.ViewHolder> {
    List<Berkas> berkasList;

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
        holder.namaPemohon.setText(berkasList.get(position).getPemohon());
        holder.noBerkas.setText(berkasList.get(position).getNoBerkas());
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
