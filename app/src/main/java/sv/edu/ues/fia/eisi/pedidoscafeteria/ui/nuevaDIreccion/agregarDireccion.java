package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.io.IOException;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackRespuestaString;
import sv.edu.ues.fia.eisi.pedidoscafeteria.cambiarCrede;
import sv.edu.ues.fia.eisi.pedidoscafeteria.login;

public class agregarDireccion extends AppCompatActivity implements CallbackRespuestaString {

    Button obtenerDireccion, agregarDireccion, imagenReferencia;
    ImageView referencia;
    EditText nombreUbicacion, dirUbicacion, facUbicacion, puntoUbicacion;
    TextView tv;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;
    boolean ACTUALIZAR = false, INSERTAR = true;
    String resultado,id;
    SharedPreferences sharedPreferences;
    String usu;
    LocationManager locationManager;
    double latitud,longitud,altitud;
    Bitmap bitmap = null; // para convertir la imagen en un mapa de bits
    int SEARCH_IMAGE_REQUEST = 1, REQUEST_TAKE_PHOTO = 2; //codigo para la activity de seleccionar archivo y su resultado
    SoundPool sp;
    int sonido,incorrecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_direccion);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorServicios = new ControladorServicios(this);
        controladorBD = new ControladorBD(getApplicationContext());
        obtenerDireccion = (Button) findViewById(R.id.obtener_direccion);
        agregarDireccion = (Button) findViewById(R.id.agregar_direccion);
        imagenReferencia = (Button) findViewById(R.id.imagen_referencia);
        referencia = (ImageView) findViewById(R.id.referencia);
        nombreUbicacion = (EditText) findViewById(R.id.et_nombre_ubicacion);
        dirUbicacion = (EditText) findViewById(R.id.et_dir_ubicacion);
        //facUbicacion = (EditText) findViewById(R.id.et_facultad_ubicacion);
        //facUbicacion.setVisibility(View.INVISIBLE);
        //tv = (TextView) findViewById(R.id.tv_facultad);
        //tv.setVisibility(View.INVISIBLE);
        puntoUbicacion = (EditText) findViewById(R.id.et_punto_ubicacion);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido = sp.load(agregarDireccion.this,R.raw.audio_inicio,1);
        incorrecto = sp.load(agregarDireccion.this,R.raw.audio_error,1);

        obtenerDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Geocoder g = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses = g.getFromLocation(latitud, longitud, 1);
                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder("");

                        for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }
                        dirUbicacion.setText(strReturnedAddress.toString());

                    }
                    else
                    {
                        FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.no_se_pudo_ubicacion), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        agregarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreUbicacion.getText().toString().isEmpty() || dirUbicacion.getText().toString().isEmpty() || puntoUbicacion.getText().toString().isEmpty() || bitmap == null)
                {
                    FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.llene_campos), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
                    sp.play(incorrecto,1,1,1,0,0);
                }
                else
                {
                    Ubicacion ubicacion = new Ubicacion(1, 4,  dirUbicacion.getText().toString(), nombreUbicacion.getText().toString(), puntoUbicacion.getText().toString(),usu);
                    resultado = controladorServicios.CrearAct(ubicacion, getApplicationContext(), INSERTAR);
                    controladorBD.abrir();
                    String respuesta = controladorBD.insertar(ubicacion);
                    id = String.valueOf(controladorBD.ultimoIdUbicacion());
                    controladorBD.cerrar();
                    controladorServicios.subirImagen(getApplicationContext(), bitmap, "ubicacion", id);
                    //FancyToast.makeText(getApplicationContext(), respuesta, FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
                        sp.play(sonido,1,1,1,0,0);
                }

            }
        });

        imagenReferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mDialog = new MaterialDialog.Builder(agregarDireccion.this)
                        .setTitle("Agregar foto de referencia")
                        .setAnimation(R.raw.camera)
                        .setMessage("¡Cambia o agrega una fotografía!")
                        .setCancelable(true)
                        .setPositiveButton("Galería", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delete Operation
                                escogerImagen();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Tomar Foto", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                tomarFoto();
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                mDialog.show();
            }
        });


    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            latitud = location.getLatitude();
            longitud = location.getLongitude() ;
            altitud = location.getAltitude();
        }
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    };


    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions,
            // int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }



    @Override
    public void respuesta(String respuesta)
    {
        resultado = respuesta;

        if(resultado == "CONEXIÓN EXITOSA")
        {
            FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.ubicacion_guardada), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
            nombreUbicacion.setText("");
            dirUbicacion.setText("");
            puntoUbicacion.setText("");
        }
        else
        {
            FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.ubicacion_no_guardada), FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
        }
    }

    private void escogerImagen() //metodo para mostrar un explorador de archivos para imagenes
    {
        Intent intent = new Intent(); //crea un nuevo intent
        intent.setType("image/*");    //pone que solo sea para buscar cualquier tipo de imagen
        intent.setAction(Intent.ACTION_GET_CONTENT); // le pone la acción que es, escoger un contenido
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SEARCH_IMAGE_REQUEST); //Inicia la activitie para escoger una foto
    }

    public void tomarFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    //Este otro metodo es para el resultado de la activity de arriba.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Este if verifica si el resultado es igual al código de nuestra variable
        //y verifica si la foto no está vacía
        if(requestCode == SEARCH_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri filePath = data.getData();//obtiene dirección del archivo
            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath); //hace un mapa de bits de la imagen
                referencia.setImageBitmap(bitmap); // ese imageView es dependiende de su activity, muestra la imagen seleccionada
                //Pueden llamar el método de subirImagen aqui o donde ustedes les convenga más
                //Solo tienen que recordar que tienen que mandar los parametros necesarios estos son:
                //Contexto, el bitmap de aquí arriba, el nombre de la tabla, y el id del registro al que le pertenece la imagen
            }
            catch (IOException e)//captura excepción de conversión a bitmap
            {
                e.printStackTrace();
            }
        }
        else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
        {
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                referencia.setImageBitmap(bitmap);
        }
        else
        {
            //no se seleccionó una foto, pueden poner aquí lo que les de la gana
        }
    }
}