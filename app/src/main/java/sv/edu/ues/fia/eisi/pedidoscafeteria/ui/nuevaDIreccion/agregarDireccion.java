package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackRespuestaString;

public class agregarDireccion extends AppCompatActivity implements CallbackRespuestaString {

    Button obtenerDireccion, agregarDireccion;
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
        obtenerDireccion = (Button) findViewById(R.id.obtener_direccion_m);
        agregarDireccion = (Button) findViewById(R.id.agregar_direccion);
        nombreUbicacion = (EditText) findViewById(R.id.et_nombre_ubicacion);
        dirUbicacion = (EditText) findViewById(R.id.et_dir_ubicacion);
        //facUbicacion = (EditText) findViewById(R.id.et_facultad_ubicacion);
        //facUbicacion.setVisibility(View.INVISIBLE);
        //tv = (TextView) findViewById(R.id.tv_facultad);
        //tv.setVisibility(View.INVISIBLE);
        puntoUbicacion = (EditText) findViewById(R.id.et_punto_ubicacion);
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
                    Ubicacion ubicacion = new Ubicacion(1, 4,  dirUbicacion.getText().toString(), nombreUbicacion.getText().toString(), puntoUbicacion.getText().toString(),usu);
                    resultado = controladorServicios.CrearAct(ubicacion, getApplicationContext(), INSERTAR);
                    controladorBD.abrir();
                    String respuesta = controladorBD.insertar(ubicacion);
                    controladorBD.cerrar();
                    //FancyToast.makeText(getApplicationContext(), respuesta, FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
                }

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
}