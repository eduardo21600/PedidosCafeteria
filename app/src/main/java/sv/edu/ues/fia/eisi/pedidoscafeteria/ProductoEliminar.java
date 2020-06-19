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
    private TextView nombre;
    private TextView id;
    private TextView productoprecio;
    private TextView descripcion;
    private String idP;
    private String precio;
    private String descripcions;
    private String nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_eliminar);
        nombre=(TextView)findViewById(R.id.textNombreEliminarProducto);
        id=(TextView)findViewById(R.id.textIdProducto);
        productoprecio=(TextView)findViewById(R.id.textPrecioProducto);
        descripcion=(TextView)findViewById(R.id.textDescripcionProducto);

        helper = new ControladorBD(this);
        baseServicios=new ControladorServicios();
        Bundle extras = getIntent().getExtras();
        idP= extras.getString("idy");
        precio= extras.getString("precio");
        descripcions= extras.getString("desc");
        nombres= extras.getString("nombre");
        productoprecio.setText(precio);
        descripcion.setText(descripcions);
        nombre.setText(nombres);
    }
    public void eliminarProductoH(View v){
        String regEliminadas;
        Producto producto=new Producto();
        producto.setIdProduto(Integer.valueOf(idP));

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
        producto.setIdProduto(Integer.valueOf(idP));
        helper.abrir();
        regEliminadas=baseServicios.Eliminar(producto,getApplicationContext());
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        //Intent intentn= new Intent(this, Producto_Lista.class);
        //this.startActivity(intentn);
    }
}
