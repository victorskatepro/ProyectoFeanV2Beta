package com.example.jarvis.proyectofean.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jarvis.proyectofean.Objetos.Asistencias;
import com.example.jarvis.proyectofean.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentoInicio extends Fragment {
    private RecyclerView rv;
    List<Asistencias> asistencias;
    private AsistenciaAdapter adapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    FirebaseDatabase database;
    DatabaseReference reference;

    private BottomNavigationView bottomNavigationView;

    public FragmentoInicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);
        rv = (RecyclerView) view.findViewById(R.id.asistencia_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        asistencias = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        adapter = new AsistenciaAdapter(getActivity(),asistencias);
        rv.setAdapter(adapter);

        database.getReference("asistencias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                asistencias.removeAll(asistencias);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    Asistencias asistencia = snapshot.getValue(Asistencias.class);
                    // System.out.print(curso);
                    asistencias.add(asistencia);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }


}
