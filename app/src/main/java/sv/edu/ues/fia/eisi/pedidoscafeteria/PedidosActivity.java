package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


//le falta estetica

public class PedidosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ControladorBD Helper;
    private ListView LV;
    List<Pedido> pps = new ArrayList<>();
    List<Pedido> ppx = new ArrayList<>();
    AdaptadorPedidos mlP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        LV = (ListView)findViewById(R.id.lista);

        LV.setOnItemClickListener(this);
        Helper.abrir();
        //pps = Helper.consultarPedidos();
        pps = ppx;
        Helper.cerrar();
        mlP = new AdaptadorPedidos(this,R.layout.pedidos_items,pps);
        LV.setAdapter(mlP);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        //imaginemos que Vane ya hizo su activity v: no lo ha hecho, pero imaginemos que si
        Intent intx = new Intent(this,DetallePedidoAFragment.class);
        intx.putExtra("idpedido",String.valueOf(mlP.getItem(i).getIdPedido()));
        startActivity(intx);
    }
}
