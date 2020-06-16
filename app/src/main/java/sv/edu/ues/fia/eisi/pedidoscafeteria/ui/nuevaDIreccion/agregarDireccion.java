package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class agregarDireccion extends AppCompatActivity {

    Button obtenerDireccion, agregarDireccion;
    EditText nombreUbicacion, dirUbicacion, facUbicacion, puntoUbicacion;
    int PLACEPICKER_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_direccion);

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
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(intentBuilder.build(agregarDireccion.this), PLACEPICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACEPICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                dirUbicacion.setText(place.getAddress());
            }
        }
    }
}