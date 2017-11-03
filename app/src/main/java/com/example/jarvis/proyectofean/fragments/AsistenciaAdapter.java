package com.example.jarvis.proyectofean.fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jarvis.proyectofean.Objetos.Asistencias;
import com.example.jarvis.proyectofean.R;

import java.util.List;

/**
 * Created by JARVIS on 15/07/2017.
 */

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.AsistenciasviewHolder> {
    List<Asistencias> asistencias;
    Activity activity;

    public AsistenciaAdapter(Activity activity, List<Asistencias> asistencias){
        this.activity = activity;
        this.asistencias = asistencias;
    }

    @Override
    public AsistenciaAdapter.AsistenciasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item2, parent, false);
        AsistenciasviewHolder holder = new AsistenciasviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AsistenciaAdapter.AsistenciasviewHolder holder, int position) {
      final Asistencias asistencia = asistencias.get(position);
        holder.txtcurso.setText(asistencia.getCurso());
        holder.txtfecha.setText(asistencia.getFecha());
        holder.txthora.setText(asistencia.getHora());
    }

    @Override
    public int getItemCount() {
        return asistencias.size();
    }
    public class AsistenciasviewHolder extends RecyclerView.ViewHolder{
       public TextView txtcurso,txtprofesor,txthora,txtfecha;
        public AsistenciasviewHolder(View itemView) {
            super(itemView);
            txtcurso = (TextView) itemView.findViewById(R.id.txtacurso);
            txtfecha = (TextView)itemView.findViewById(R.id.txtafecha);
            txthora = (TextView)itemView.findViewById(R.id.txtahora);
        }
    }
}
