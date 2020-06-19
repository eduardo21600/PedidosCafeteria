package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AsignarRepartidor extends AppCompatActivity {

    RecyclerView rvRepaElegir;
    ControladorBD controladorBD;
    List<Usuario> users,repartidores;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_repartidor);
        rvRepaElegir = (RecyclerView)findViewById(R.id.rvAsignar);
        repartidores =new ArrayList<>();
        users = new ArrayList<>();
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        users=controladorBD.ConsultaUsuarios();
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getIdTipoUsuario()==2 && users.get(i).getEstado().equals("1")){
                repartidores.add(users.get(i));
            }
        }
        controladorBD.cerrar();
        layoutManager = new LinearLayoutManager(this);
        rvRepaElegir.setLayoutManager(layoutManager);
        adapter = new AdapterSeleccionarRepa(this,repartidores,this);
        rvRepaElegir.setAdapter(adapter);
    }
}
