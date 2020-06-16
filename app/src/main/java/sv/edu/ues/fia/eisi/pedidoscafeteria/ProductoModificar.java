package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductoModificar extends AppCompatActivity {
    private ControladorBD helper;
    private TextView textIdProducto;
    private EditText editnombreProducto;
    private EditText editprecioUnitario;
    private EditText editdescProducto;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_modificar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_modificar);
        helper = new ControladorBD(this);
        editnombreProducto = (EditText) findViewById(R.id.editnombreMProducto);
        editprecioUnitario = (EditText) findViewById(R.id.editprecioMUnitario);
        editdescProducto = (EditText) findViewById(R.id.editdescMProducto);
        textIdProducto = (TextView) findViewById(R.id.textProductoId);
        Bundle extras = getIntent().getExtras();
        id= extras.getString("id");
        textIdProducto.setText(id);
    }
    public void actualizarProducto(View v) {
        if(validar()){

            Producto alumno = new Producto();
            alumno.setIdProduto(Integer.valueOf(textIdProducto.getText().toString()));
            alumno.setNombreProducto(editnombreProducto.getText().toString());
            alumno.setPrecioUnitario(Double.valueOf(editprecioUnitario.getText().toString()));
            alumno.setDescProducto(editdescProducto.getText().toString());
            helper.abrir();
            String estado = helper.ActualizarProducto(alumno);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
            // Intent intentn= new Intent(this, Producto_Lista.class);
            // this.startActivity(intentn);
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
