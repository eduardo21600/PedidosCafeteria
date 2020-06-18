package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorDescripcionMenu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class DescMenu extends AppCompatActivity implements CallbackWS {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    int idMenu;
    String nomMenu;
    double precionMenu;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;
    List<Producto> productos;
    TextView tv_nom, tv_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_menu);
        Intent intent = getIntent();
        idMenu = intent.getIntExtra("idMenu", 0);
        nomMenu = intent.getStringExtra("nomMenu");
        precionMenu = intent.getDoubleExtra("precioMenu", 0.00);
        tv_nom = (TextView) findViewById(R.id.nombre_menu_desc);
        tv_precio = (TextView) findViewById(R.id.precio_menu_desc);
        tv_nom.setText(nomMenu);
        tv_precio.setText(String.valueOf(precionMenu));
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        productos = controladorBD.ConsultaProductosMenu(idMenu);
        controladorBD.cerrar();
        //controladorServicios = new ControladorServicios(this);
        //controladorServicios.BuscarProductoMenu(idMenu, getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.descMenu_productos_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorDescripcionMenu(this, productos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void ResponseWS(Object lista)
    {
        productos = (List<Producto>) lista;
        adapter = new AdaptadorDescripcionMenu(this, productos);
        recyclerView.setAdapter(adapter);
    }
}