package com.example.jarvis.proyectofean.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jarvis.proyectofean.CursoAdapter;
import com.example.jarvis.proyectofean.Objetos.Cursos;
import com.example.jarvis.proyectofean.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JARVIS on 13/07/2017.
 */

public class HomeFragment extends Fragment {

    private RecyclerView rv;
    List<Cursos> cursos;
    private CursoAdapter adapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    FirebaseDatabase database;
    DatabaseReference reference;

    public HomeFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv = (RecyclerView) view.findViewById(R.id.curso_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cursos = new ArrayList<>();
        database = FirebaseDatabase.getInstance();

        adapter = new CursoAdapter(getActivity(),cursos);
        rv.setAdapter(adapter);

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        database.getReference("cursos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cursos.removeAll(cursos);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    Cursos curso = snapshot.getValue(Cursos.class);
                    // System.out.print(curso);
                    cursos.add(curso);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
    private void locationStart(){
        LocationManager mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Localizacion local = new Localizacion();
        local.setMain2Activity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            getActivity().startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) local);
    }

    public class Localizacion implements LocationListener{
        HomeFragment main2Activity;

        public HomeFragment getMain2Activity(){
            return main2Activity;
        }

        public void setMain2Activity(HomeFragment main2Activity){
            this.main2Activity = main2Activity;
        }


        @Override
        public void onLocationChanged(Location location) {
            //Button btnlocallizacion;
            //location.getLatitude();
            //location.getLongitude();
            String lt = location.getLatitude()+"";
            String lg = location.getLongitude()+"";
            //adapter
            //Toast.makeText(activity, lg, Toast.LENGTH_SHORT).show();
            //final String longintud = "-76.95";
            //final String latitud = "-12.0446";
            String lt1 = lt.substring(0,6);
            String lg1 = lg.substring(0,6);

            adapter.setLatitud(lt1);
            adapter.setLongitud(lg1);

//            Intent intent = new Intent(getActivity(), CursoAdapter.class);
//            intent.putExtra("latitud",lt1);
//            intent.putExtra("longitud",lg1);
//            getActivity().startActivity(intent);
            //Toast.makeText(getActivity(), lt, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status){
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAIBLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getActivity(), "Gps Activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getActivity(), "Gps Desactivado", Toast.LENGTH_SHORT).show();
        }
    }
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] granResults){
        if (requestCode == 1000){
            if (granResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }

    }


}

