package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EliminarMenu extends AppCompatActivity {

    ControladorBD helper;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_menu);
        helper = new ControladorBD(this);
        Bundle extras = getIntent().getExtras();
        id= extras.getString("idy");
    }
    public void eliminarMenuEncargado(View v){
        String regEliminadas;
        Menu menu=new Menu();
        menu.setIdMenu(Integer.valueOf(id));

        helper.abrir();
        regEliminadas=helper.eliminarMenu(menu);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

    }
}
