package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorUbicacionSeleccionar;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;

public class SeleccionarUbicacion extends AppCompatActivity {

    Button paraLlevar;
    List<Ubicacion> listUbicacion;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    ControladorBD controladorBD;
    SharedPreferences sharedPreferences;
    String usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_ubicacion);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        listUbicacion = controladorBD.consultarUbicacionUsuario(usu);
        controladorBD.cerrar();
        recyclerView = (RecyclerView) findViewById(R.id.ubicacion_seleccionar_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(listUbicacion.isEmpty())
        {
            FancyToast.makeText(getApplicationContext(), "No hay ubicaciones, vaya a crear una ubicacion", FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
        }
        else
        {
            adapter = new AdaptadorUbicacionSeleccionar(this, listUbicacion, this);
            recyclerView.setAdapter(adapter);
        }



    }
}