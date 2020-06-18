package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoMenuAsignar extends AppCompatActivity {

    private ControladorBD base;
    private RecyclerView recycler_asignar_producto;
    private EditText editMenuProducto;
    private EditText editProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_menu_asignar);
        base = new ControladorBD(this);
        ControladorBD help = new ControladorBD(getApplicationContext());
        recycler_asignar_producto=(RecyclerView)findViewById(R.id.recycler_asignar_producto);
        recycler_asignar_producto.setLayoutManager(new LinearLayoutManager(this));
        help.abrir();
        AdaptadorProductoDesignado adaptadorProductoDesignado=new AdaptadorProductoDesignado(help.ConsultaProductos());
        recycler_asignar_producto.setAdapter(adaptadorProductoDesignado);
        help.cerrar();

    }


}

