package com.example.jarvis.proyectofean;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarvis.proyectofean.Objetos.Asistencias;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class Main3Activity extends AppCompatActivity {
    public SurfaceView cameraView;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    public String name, fecha, horallegada, nombrecurse,aula;
    private TextView txtcurso, txthora, txtfecha;


    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Button btnqr;
    List list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtcurso = (TextView) findViewById(R.id.formcurso);
        txtfecha = (TextView) findViewById(R.id.formfecha);
        txthora = (TextView) findViewById(R.id.formhora);
        //---------fecha
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("South America/Lima"));
        int monthDay = calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH);
        int year = calendarNow.get(Calendar.YEAR);
        fecha = (monthDay + "/" + month + "/" + year);
//-------------hora
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        horallegada = (hora + ":" + minutos);

        //---------------pasar el nombre del curso
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        nombrecurse = (String) extras.get("nombre");
        aula = (String) extras.get("aula");


        txthora.setText(horallegada);
        txtfecha.setText(fecha);
        txtcurso.setText(nombrecurse);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        cameraView = (SurfaceView) findViewById(R.id.camera_view);

        final BarcodeDetector barcodeDetector;
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE | Barcode.PDF417)
                .build();

        //final CameraSource cameraSource;
        final CameraSource cameraSource = new CameraSource
                //.setFacing(CameraSource.CAMERA_FACING_BACK)
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(24.0f)
                .build();
        //.Builder(this, barcodeDetector)

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }

            }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
              final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if(barcodes.size() != 0){
                     // barcodes.valueAt(0).displayValue.toString();
                   // String obtenido = barcodes.valueAt(0).displayValue.toString();
                    //Toast.makeText(getApplicationContext(), obtenido, Toast.LENGTH_SHORT).show();
                //finish();
                    //String resultado = barcodes.valueAt(0).displayValue.toString();
                    //txtcurso.setText(resultado);
                    //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();

                    txtcurso.post(new Runnable() {
                        @Override
                        public void run() {
                            //modificar();
                            //txtcurso.setText(
                                   if(aula.equals(barcodes.valueAt(0).displayValue.toString())){
                                       cameraSource.stop();
                                       modificar();
                                   }else{
                                       Toast.makeText(getApplicationContext(), "Codigo QR incorrecto", Toast.LENGTH_SHORT).show();
                                   }
                            //);
                        }
                    });
                }
               // barcodeDetector.release();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    setUserData(user);
                } else {
                    goLogInScreen();
                }
            }
        };
        btnqr = (Button)findViewById(R.id.btnqr);


        btnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
               startActivity(intent);
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();

    }
   public void modificar (){
       Asistencias asitencia = new Asistencias(ref.push().getKey(),nombrecurse,name,horallegada,fecha);
       ref.child("asistencias").child(asitencia.getId()).setValue(asitencia);

       Toast.makeText(getApplicationContext(), "Asistencia Registrada", Toast.LENGTH_SHORT).show();
       Main3Activity.this.finish();
   }
    private void setUserData(FirebaseUser user){
        name = user.getDisplayName();
        String nombre = name;

        //String email = user.getEmail();
    }
    private void goLogInScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

}
