package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente;

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
import android.media.Image;
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
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;

public class ModificarUbicacion extends AppCompatActivity {

    Button obtenerDireccion, agregarDireccion, imagenReferencia;
    ImageView referencia;
    EditText nombreUbicacion, dirUbicacion, facUbicacion, puntoUbicacion;
    TextView tv;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;
    boolean ACTUALIZAR = false, INSERTAR = true;
    String resultado;
    SharedPreferences sharedPreferences;
    String usu;
    LocationManager locationManager;
    double latitud,longitud,altitud;
    int idUbi;
    Bitmap bitmap = null; // para convertir la imagen en un mapa de bits
    int SEARCH_IMAGE_REQUEST = 1; //codigo para la activity de seleccionar archivo y su resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_ubicacion);
        Intent intent = getIntent();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorServicios = new ControladorServicios();
        controladorBD = new ControladorBD(getApplicationContext());
        obtenerDireccion = (Button) findViewById(R.id.obtener_direccion_m);
        agregarDireccion = (Button) findViewById(R.id.modificar_direccion);
        referencia = (ImageView) findViewById(R.id.referencia_m);
        imagenReferencia = (Button) findViewById(R.id.imagen_referencia_m);
        nombreUbicacion = (EditText) findViewById(R.id.et_nombre_ubicacion_m);
        dirUbicacion = (EditText) findViewById(R.id.et_dir_ubicacion_m);
        //facUbicacion = (EditText) findViewById(R.id.et_facultad_ubicacion);
        //facUbicacion.setVisibility(View.INVISIBLE);
        //tv = (TextView) findViewById(R.id.tv_facultad);
        //tv.setVisibility(View.INVISIBLE);
        puntoUbicacion = (EditText) findViewById(R.id.et_punto_ubicacion_m);

        nombreUbicacion.setText(intent.getStringExtra("nombre"));
        dirUbicacion.setText(intent.getStringExtra("direccion"));
        puntoUbicacion.setText(intent.getStringExtra("punto"));
        idUbi = intent.getIntExtra("id", 0);

        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+idUbi+"_ubicacion.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(referencia);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

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

                if (nombreUbicacion.getText().toString().isEmpty() || dirUbicacion.getText().toString().isEmpty() || puntoUbicacion.getText().toString().isEmpty())
                {
                    FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.llene_campos), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
                }
                else
                {
                    Ubicacion ubicacion = new Ubicacion(idUbi, 4,  dirUbicacion.getText().toString(), nombreUbicacion.getText().toString(), puntoUbicacion.getText().toString(),usu);
                    //resultado = controladorServicios.CrearAct(ubicacion, getApplicationContext(), INSERTAR);
                    controladorBD.abrir();
                    String respuesta = controladorBD.actualizar(ubicacion);
                    controladorBD.cerrar();
                    referencia.setDrawingCacheEnabled(true);
                    bitmap = referencia.getDrawingCache();
                    controladorServicios.subirImagen(getApplicationContext(), bitmap, "ubicacion", String.valueOf(idUbi));
                    FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.ubicacion_guardada), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
                    nombreUbicacion.setText("");
                    dirUbicacion.setText("");
                    puntoUbicacion.setText("");
                    finish();
                    //FancyToast.makeText(getApplicationContext(), respuesta, FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
                }

            }
        });

        imagenReferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escogerImagen();
            }
        });
    }

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

    private void escogerImagen() //metodo para mostrar un explorador de archivos para imagenes
    {
        Intent intent = new Intent(); //crea un nuevo intent
        intent.setType("image/*");    //pone que solo sea para buscar cualquier tipo de imagen
        intent.setAction(Intent.ACTION_GET_CONTENT); // le pone la acción que es, escoger un contenido
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SEARCH_IMAGE_REQUEST); //Inicia la activitie para escoger una foto
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
                referencia.destroyDrawingCache();
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
        else
        {
            //no se seleccionó una foto, pueden poner aquí lo que les de la gana
        }
    }

}