package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
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

public class ModificarUbicacion extends AppCompatActivity {

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
        setContentView(R.layout.activity_modificar_ubicacion);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorServicios = new ControladorServicios();
        controladorBD = new ControladorBD(getApplicationContext());
        obtenerDireccion = (Button) findViewById(R.id.obtener_direccion);
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
}