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
    private int idMenu;
    private String IDProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_menu_asignar);
        ControladorBD help = new ControladorBD(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        idMenu= Integer.valueOf(extras.getString("id"));
        recycler_asignar_producto=(RecyclerView)findViewById(R.id.recycler_asignar_producto);
        recycler_asignar_producto.setLayoutManager(new LinearLayoutManager(this));
        help.abrir();
        AdaptadorProductoDesignado adaptadorProductoDesignado=new AdaptadorProductoDesignado(help.ConsultaProductos(),idMenu,getApplicationContext());
        recycler_asignar_producto.setAdapter(adaptadorProductoDesignado);
        help.cerrar();


    }
    public void integrarProductoAlMenu (String idProducto, int men)
    {

        Integer IdProducto=Integer.valueOf(idProducto);
        String registro;
        ProductoAsignar productoAsignar=new ProductoAsignar();
        productoAsignar.setIdmenu(men);
        productoAsignar.setIdProducto(IdProducto);

        base = new ControladorBD(getApplicationContext());
        base.abrir();
        registro=base.AsignarProtoMenu(productoAsignar);
        base.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();

    }


}

