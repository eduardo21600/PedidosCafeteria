package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.shashank.sony.fancytoastlib.FancyToast;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;

public class agregarDireccion extends AppCompatActivity {

    Button obtenerDireccion, agregarDireccion;
    EditText nombreUbicacion, dirUbicacion, facUbicacion, puntoUbicacion;
    ControladorServicios controladorServicios;
    boolean ACTUALIZAR = false, INSERTAR = true;
    String resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_direccion);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        controladorServicios = new ControladorServicios();
        obtenerDireccion = (Button) findViewById(R.id.obtener_direccion);
        agregarDireccion = (Button) findViewById(R.id.agregar_direccion);
        nombreUbicacion = (EditText) findViewById(R.id.et_nombre_ubicacion);
        dirUbicacion = (EditText) findViewById(R.id.et_dir_ubicacion);
        facUbicacion = (EditText) findViewById(R.id.et_facultad_ubicacion);
        puntoUbicacion = (EditText) findViewById(R.id.et_punto_ubicacion);

        obtenerDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });

        agregarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ubicacion ubicacion = new Ubicacion(1, facUbicacion.getText().toString(), 1, dirUbicacion.getText().toString(), nombreUbicacion.getText().toString(), puntoUbicacion.getText().toString());
                resultado = controladorServicios.CrearAct(ubicacion, getApplicationContext(), INSERTAR);

                if(resultado == "CONEXIÓN EXITOSA")
                {
                    FancyToast.makeText(getApplicationContext(), "Se ha guardado su ubicacion", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
                }
                else
                {
                    FancyToast.makeText(getApplicationContext(), "No se pudo guardar su ubicacion", FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
                }
            }
        });

    }

}