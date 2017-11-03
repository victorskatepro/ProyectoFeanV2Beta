package com.example.jarvis.proyectofean;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarvis.proyectofean.Objetos.Cursos;

import java.util.List;

/**
 * Created by JARVIS on 4/07/2017.
 */

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursosviewHolder> {

    private static final String TAG = CursoAdapter.class.getSimpleName();

    List<Cursos> cursos;
    Activity activity;


    private String latitud;
    private String longitud;

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    private String nombrecurso;

    public CursoAdapter(Activity activity, List<Cursos> cursos) {
        this.activity = activity;
        this.cursos = cursos;
    }

    @Override
    public CursoAdapter.CursosviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        CursosviewHolder holder = new CursosviewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(CursoAdapter.CursosviewHolder holder, int position) {
        final Cursos curso = cursos.get(position);
        holder.txtfechas.setText(curso.getFechas());
        holder.txtprofesor.setText(curso.getProfesor());
        holder.txtaula.setText(curso.getAula());
        holder.txtnombre.setText(curso.getNombre());
        holder.txthorario.setText(curso.getHorario());
        // nombrecurso = curso.getNombre();

        holder.btnlocallizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if(latitud.equals(curso.getLatitud()) && longitud.equals(curso.getLongitud())){
                 Intent intent = new Intent(activity, Main3Activity.class);
                 String nombre = curso.getNombre();
                  intent.putExtra("nombre",nombre);
                 String aula = curso.getAula();
                 intent.putExtra("aula",aula);
                  activity.startActivity(intent);
             }else if( curso.getLatitud().equals("Default")) {

                 Intent intent = new Intent(activity, Main3Activity.class);
                 String nombre = curso.getNombre();
                 intent.putExtra("nombre",nombre);
                 String aula = curso.getAula();
                 intent.putExtra("aula",aula);
                 activity.startActivity(intent);
             }else {
                 Toast.makeText(activity, "no estas dentro del salon:"+curso.getAula(), Toast.LENGTH_SHORT).show();
             }
                //Log.e(TAG, "Actual Latitud:" + latitud);
                //Log.e(TAG, "Actual Longitud:" + longitud);

                //Log.e(TAG, "Curso Latitud:" + curso.getLatitud());
                //Log.e(TAG, "Curso Longitud:" + curso.getLongitud());

//                curso.getLatitud()
                // Intent intent = new Intent(activity, Main3Activity.class);
                // Cursos curso = cursos.get(getAdapterPosition());
                // String nombre = curso.getNombre();
                // intent.putExtra("nombre",nombre);
                // activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }


    public class CursosviewHolder extends RecyclerView.ViewHolder {
        // public Button btnlocallizacion;
        public TextView txtnombre, txtaula, txtprofesor, txthorario, txtfechas;
        public Button btnlocallizacion;

        public CursosviewHolder(View itemView) {
            super(itemView);
            btnlocallizacion = (Button) itemView.findViewById(R.id.btnregistrar);
            txtnombre = (TextView) itemView.findViewById(R.id.nombre);
            txtfechas = (TextView) itemView.findViewById(R.id.fechas);
            txthorario = (TextView) itemView.findViewById(R.id.horario);
            txtaula = (TextView) itemView.findViewById(R.id.aula);
            txtprofesor = (TextView) itemView.findViewById(R.id.profesor);


        }

    }

}
