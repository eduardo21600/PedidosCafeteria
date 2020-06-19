package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorLocal;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Menu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ProductoAsignar;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class MenusLocal extends AppCompatActivity implements CallbackWS {

    private List<Menu> listaMenu;
    private List<ProductoAsignar> PS;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_local);
        Intent intent = getIntent();
        int id = intent.getIntExtra("Local",0);
        //Toast.makeText(this, "Estas viendo el menu del local con id: " + id, Toast.LENGTH_SHORT).show();
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        listaMenu = controladorBD.ConsultaMenusLocal(id);
        controladorBD.cerrar();
        recyclerView = (RecyclerView) findViewById(R.id.menu_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorMenuC(this, listaMenu);
        recyclerView.setAdapter(adapter);
        //controladorServicios=new ControladorServicios(this);
        //controladorServicios.BuscarMenuLocal(id, getApplicationContext());

    }

    @Override
    public void ResponseWS(Object lista)
    {
        listaMenu = (List<Menu>) lista;
        adapter = new AdaptadorMenuC(this, listaMenu);
        recyclerView.setAdapter(adapter);
    }
}
