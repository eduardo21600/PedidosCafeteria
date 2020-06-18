package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ProductoAsignadoFinal extends AppCompatActivity {
    private String idMenu;
    private String IDProducto;
    private ControladorBD base;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pr);
        base = new ControladorBD(this);

    }
    public void integrarProductoAlMenu (View v)
    {
        Bundle extras = getIntent().getExtras();
        idMenu= extras.getString("id");
        Bundle extras1 = getIntent().getExtras();
        IDProducto= extras1.getString("productoid");

        Integer IdProducto=Integer.valueOf(IDProducto);
        Integer IdMenu=Integer.valueOf(idMenu);
        String registro;
        ProductoAsignar productoAsignar=new ProductoAsignar();
        productoAsignar.setIdmenu(IdMenu);
        productoAsignar.setIdProducto(IdProducto);
        base.abrir();
        registro=base.AsignarProtoMenu(productoAsignar);
        base.cerrar();
        Toast.makeText(this, registro, Toast.LENGTH_SHORT).show();

    }
}
