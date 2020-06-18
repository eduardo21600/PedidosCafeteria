package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarMenu extends AppCompatActivity {
    private ControladorBD base;
    private EditText editnombreMenu;
    private EditText editprecioMenu;
    private EditText editfechaDesde;
    private EditText editfechaHasta;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_menu);
        base = new ControladorBD(this);
        editnombreMenu = (EditText) findViewById(R.id.editnombreMMenu);
        editprecioMenu = (EditText) findViewById(R.id.editprecioMMenu);
        editfechaDesde = (EditText) findViewById(R.id.editFechaMDesde);
        editfechaHasta = (EditText) findViewById(R.id.editFechaMHasta);
        Bundle extras = getIntent().getExtras();
        id= extras.getString("id");
    }
    public void actualizarMenu(View v) {
        if(validarMenu()) {
            Integer idMenu = Integer.valueOf(id);
            String nombre = editnombreMenu.getText().toString();
            Double precio = Double.valueOf(editprecioMenu.getText().toString());
            String fechadesde = editfechaDesde.getText().toString();
            String fechahasta = editfechaHasta.getText().toString();
            String registro;

            Menu menu = new Menu();
            menu.setIdMenu(idMenu);
            menu.setNomMenu(nombre);
            menu.setPrecioMenu(precio);
            menu.setFechaDesdeMenu(fechadesde);
            menu.setFechaHastaMenu(fechahasta);

            base.abrir();
            registro = base.ActualizarMenu(menu);
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
    public boolean validarMenu()
    {
        boolean retorno=true;
        String c1=editnombreMenu .getText().toString();
        String c2=editprecioMenu.getText().toString();
        String c3=editfechaDesde.getText().toString();
        String c4=editfechaHasta.getText().toString();
        if(c1.isEmpty())
        {
            editnombreMenu.setError("Debe ingresar el nombre");
            retorno=false;
        }
        if(c2.isEmpty())
        {
            editprecioMenu.setError("Debe ingresar el precio");
            retorno=false;
        }
        if(c3.isEmpty())
        {
            editfechaDesde.setError("Debe ingresar una fechaDesde");
            retorno=false;
        }
        if(c4.isEmpty())
        {
            editfechaHasta.setError("Debe ingresar una fecha Hasta");
            retorno=false;
        }
        return  retorno;
    }
}
