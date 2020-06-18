package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AsignarProductoMenu extends AppCompatActivity {

    private RecyclerView recyclerViewProductoDelMenu;
    private Button btnAgregarProducto;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_producto_menu);
        btnAgregarProducto=(Button)findViewById(R.id.AgregarBotonProductoMenu);
        recyclerViewProductoDelMenu=(RecyclerView)findViewById(R.id.recycleViewConsultarMenuPorducto);
        recyclerViewProductoDelMenu.setLayoutManager(new LinearLayoutManager(this));
        Bundle extras = getIntent().getExtras();
        id= extras.getString("id");
        ControladorBD help = new ControladorBD(getApplicationContext());
        help.abrir();
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductoMenuAsignar.class);
                startActivity(intent);
            }
        });
        AdaptadorAsignarProductoMenu adaptadorAsignarProductoMenu=new AdaptadorAsignarProductoMenu(help.ConsultaTablaAsignarProducto(id));
        recyclerViewProductoDelMenu.setAdapter(adaptadorAsignarProductoMenu);
        help.cerrar();
    }
}
