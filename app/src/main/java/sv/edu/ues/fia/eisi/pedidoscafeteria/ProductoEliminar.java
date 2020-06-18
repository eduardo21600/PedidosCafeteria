package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProductoEliminar extends AppCompatActivity {

    ControladorBD helper;
    private ControladorServicios baseServicios;
    TextView textidProduto;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        helper = new ControladorBD(this);
        baseServicios=new ControladorServicios();
        Bundle extras = getIntent().getExtras();
        id= extras.getString("idy");
        textidProduto=(TextView) findViewById(R.id.textEliminar);
        textidProduto.setText(id);
    }
    public void eliminarProductoH(View v){
        String regEliminadas;
        Producto producto=new Producto();
        producto.setIdProduto(Integer.valueOf(id));

        helper.abrir();
        regEliminadas=helper.eliminarProducto(producto);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        //Intent intentn= new Intent(this, Producto_Lista.class);
        //this.startActivity(intentn);
    }
    public void eliminarProductoEnLaWeb(View v){
        String regEliminadas;
        Producto producto=new Producto();
        producto.setIdProduto(Integer.valueOf(id));
        helper.abrir();
        regEliminadas=baseServicios.Eliminar(producto,getApplicationContext());
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        //Intent intentn= new Intent(this, Producto_Lista.class);
        //this.startActivity(intentn);
    }
}
