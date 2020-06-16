package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoIngresar extends AppCompatActivity {
    private ControladorBD base;
    private ControladorServicios baseServicios;
    private EditText editnombreProducto;
    private EditText editprecioUnitario;
    private EditText editdescProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_ingresar);
        base = new ControladorBD(this);
        editnombreProducto = (EditText) findViewById(R.id.editnombreProducto);
        editprecioUnitario = (EditText) findViewById(R.id.editprecioUnitario);
        editdescProducto = (EditText) findViewById(R.id.editdescProducto);
    }
    public void insertarProducto(View v) {


        if(validar())
        {
            String nombre=editnombreProducto .getText().toString();
            Double precio=Double.valueOf(editprecioUnitario.getText().toString());
            String des=editdescProducto.getText().toString();
            String registro;

            Producto producto=new Producto();
            producto.setNombreProducto(nombre);
            producto.setPrecioUnitario(precio);
            producto.setDescProducto(des);

            base.abrir();
            registro=base.CrearProducto(producto);
            registro=baseServicios.CrearAct(producto, getApplicationContext(),true);
            base.cerrar();
            Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
            // Intent intentn= new Intent(this, Producto_Lista.class);
            //this.startActivity(intentn);
        }
        else
        {
            Toast.makeText(this, "Debe llenar los campos correspondiente", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean validar()
    {
        boolean retorno=true;
        String c1=editnombreProducto .getText().toString();
        String c2=editprecioUnitario.getText().toString();
        String c3=editdescProducto.getText().toString();
        if(c1.isEmpty())
        {
            editnombreProducto.setError("Debe ingresar el nombre");
            retorno=false;
        }
        if(c2.isEmpty())
        {
            editprecioUnitario.setError("Debe ingresar el precio");
            retorno=false;
        }
        if(c3.isEmpty())
        {
            editdescProducto.setError("Debe ingresar una descripcion");
            retorno=false;
        }
        return  retorno;
    }
}
