package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorLocal;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Menu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class MenusLocal extends AppCompatActivity {

    private List<Menu> listaMenu;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_local);
        Intent intent = getIntent();
        int id = intent.getIntExtra("Local",0);
        Toast.makeText(this, "Estas viendo el menu del local con id: " + id, Toast.LENGTH_SHORT).show();
        listaMenu = new ArrayList<Menu>();
        List<Producto> lista=new ArrayList<Producto>();
        lista.add(new Producto(1, "Papas", 5, "papas"));
        listaMenu.add(new Menu(1,lista,3,20.0,"Anvorguesa con papas","","Anvorguesa"));
        listaMenu.add(new Menu(1,lista,3,20.0,"Con mucho queso","","Pizzita"));
        listaMenu.add(new Menu(1,lista,3,20.0,"De chocolate y banano","","crepas"));
        listaMenu.add(new Menu(1,lista,3,20.0,"Con salsa de hongos","","Pollito"));
        listaMenu.add(new Menu(1,lista,3,20.0,"Empanizados y salsa tartara","","Camarones"));
        recyclerView = (RecyclerView) findViewById(R.id.menu_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorMenuC(this, listaMenu);
        recyclerView.setAdapter(adapter);
    }
}
