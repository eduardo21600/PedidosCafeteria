package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
    String nomLocal;
    ImageView imageLocal;
    TextView nLocal;

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
        nomLocal = controladorBD.ConsultaLocal(String.valueOf(id)).getNombreLocal();
        controladorBD.cerrar();
        recyclerView = (RecyclerView) findViewById(R.id.menu_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorMenuC(this, listaMenu);
        recyclerView.setAdapter(adapter);
        nLocal = (TextView) findViewById(R.id.nombre_local_menu);
        imageLocal = (ImageView) findViewById(R.id.imagen_local_menu);
        nLocal.setText(nomLocal);
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+id+"_local.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imageLocal);
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
