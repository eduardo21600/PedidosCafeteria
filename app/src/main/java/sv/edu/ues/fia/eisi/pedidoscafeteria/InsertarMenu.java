package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InsertarMenu extends AppCompatActivity {
    private ControladorBD base;
    private ControladorServicios baseServicios;
    private EditText editnombreMenu;
    private EditText editprecioMenu;
    private EditText editfechaDesde;
    private EditText editfechaHasta;

    SharedPreferences sharedPreferences;
    String idUsuario;
    Local nLo = new Local();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_menu);
        base = new ControladorBD(this);
        baseServicios=new ControladorServicios();
        editnombreMenu = (EditText) findViewById(R.id.editnombreMenu);
        editprecioMenu = (EditText) findViewById(R.id.editprecioMenu);
        editfechaDesde = (EditText) findViewById(R.id.editFechaDesde);
        editfechaHasta = (EditText) findViewById(R.id.editFechaHasta);
        sharedPreferences=getSharedPreferences("validacion",0);
        idUsuario=sharedPreferences.getString("nombreUsuario","");
    }
    public void insertarMenuEncargado(View v) {
        if(validarMenu()) {
            Integer id = 1;
            Integer local = 2;
            String nombre = editnombreMenu.getText().toString();
            Double precio = Double.valueOf(editprecioMenu.getText().toString());
            String fechadesde = editfechaDesde.getText().toString();
            String fechahasta = editfechaHasta.getText().toString();
            String registro;

            Menu menu = new Menu();
            // menu.setIdMenu(id);
            menu.setIdLocal(conocerIdlocal());
            menu.setNomMenu(nombre);
            menu.setPrecioMenu(precio);
            menu.setFechaDesdeMenu(fechadesde);
            menu.setFechaHastaMenu(fechahasta);

            base.abrir();
            registro = base.CrearMenu(menu);
            base.cerrar();
            Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Debe llenar los campos correspondiente", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertarMenuEncargadoEnLaWeb(View v) {
        if(validarMenu()) {
            Integer id = 1;
            Integer local = 2;
            String nombre = editnombreMenu.getText().toString();
            Double precio = Double.valueOf(editprecioMenu.getText().toString());
            String fechadesde = editfechaDesde.getText().toString();
            String fechahasta = editfechaHasta.getText().toString();
            String registro;

            Menu menu = new Menu();
            // menu.setIdMenu(id);
            menu.setIdLocal(conocerIdlocal());
            menu.setNomMenu(nombre);
            menu.setPrecioMenu(precio);
            menu.setFechaDesdeMenu(fechadesde);
            menu.setFechaHastaMenu(fechahasta);

            base.abrir();
            registro = baseServicios.CrearAct(menu,getApplicationContext(),true);
            base.cerrar();
            Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Debe llenar los campos correspondiente", Toast.LENGTH_SHORT).show();
        }
    }
    public int conocerIdlocal() {
        List<Local> local= new ArrayList<>();

        base.abrir();
        local = base.ConsultaLocales();
        for (int i = 0; i < local.size(); i++) {
            if (local.get(i).getIdUsuario().equals(idUsuario)) {
                nLo = local.get(i);
                break;
            }
        }
        return nLo.getIdLocal();
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
