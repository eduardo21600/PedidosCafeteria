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
    private ListView LV;
    List<Pedido> pps = new ArrayList<>();
    AdaptadorPedidos mlP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        LV = (ListView)findViewById(R.id.lista);
        LV.setOnItemClickListener(this);
        pps.add(new Pedido(1,2,"Pendiente",4,5,"martes 4 de enero",7));
        pps.add(new Pedido(2,2,"Pendiente",4,5,"martes 4 de enero",7));
        pps.add(new Pedido(3,2,"Pendiente",4,5,"martes 4 de enero",7));
        pps.add(new Pedido(4,2,"Pendiente",4,5,"martes 4 de enero",7));
        mlP = new AdaptadorPedidos(this,R.layout.pedidos_items,pps);
        LV.setAdapter(mlP);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //imaginemos que Vane ya hizo su activity v: no lo ha hecho, pero imaginemos que si
       // Intent intx = new Intent(this,Detalle_del_pedido_uwu.class);
        //intx.putExtra("idpedido",String.valueOf(mLP.getItem(i).getIdPedido()));
//        startActivity(intx);


    }
}
