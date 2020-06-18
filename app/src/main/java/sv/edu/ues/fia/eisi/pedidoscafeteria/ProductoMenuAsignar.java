package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoMenuAsignar extends AppCompatActivity {
    private ControladorBD base;
    private EditText editMenuProducto;
    private EditText editProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_menu_asignar);
        base = new ControladorBD(this);
        editProducto=(EditText)findViewById(R.id.editAsignarProductoMenu);
        editMenuProducto=(EditText)findViewById(R.id.editidMenuProducto);
    }
    public void insertarProductoEnMenu(View v) {

        Integer idProducto=Integer.valueOf(editMenuProducto.getText().toString());
        Integer idMenu=Integer.valueOf(editProducto.getText().toString());
        String registro;

        ProductoAsignar productoAsignar=new ProductoAsignar();
        productoAsignar.setIdmenu(idMenu);
        productoAsignar.setIdProducto(idProducto);


        base.abrir();
        registro=base.AsignarProtoMenu(productoAsignar);
        base.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
        // Intent intentn= new Intent(this, Producto_Lista.class);
        //this.startActivity(intentn);
    }
}
