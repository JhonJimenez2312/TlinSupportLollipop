package com.example.admin.tlintest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 09/12/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AlmacenViewHolder>{

    List<Almacen> almacenes;
    private Context contexto;

    public Adapter(Context contexto,List<Almacen> almacenes) {
        this.almacenes = almacenes;
        this.contexto=contexto;
    }

    public static class AlmacenViewHolder extends RecyclerView.ViewHolder{
        TextView nombreAlmacen;
        ImageView foto;

        public AlmacenViewHolder(View itemView) {
            super(itemView);
            nombreAlmacen=itemView.findViewById(R.id.txtNombreAlmacen);
            foto = (ImageView)itemView.findViewById(R.id.imgFoto);
        }
    }

    @Override
    public AlmacenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        return new AlmacenViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final AlmacenViewHolder holder, int position) {
        Almacen almacen = almacenes.get(position);
        holder.nombreAlmacen.setText(almacen.getNombre());
        String url =almacen.getFoto();
        Picasso.with(contexto).load(url).into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return almacenes.size();
    }


}
