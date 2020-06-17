package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;


//le falta estetica

public class PedidosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CallbackWS {
    ControladorBD Helper;
    private ListView LV;
    List<Pedido> pps = new ArrayList<>();
    ControladorServicios controladorServicios = new ControladorServicios();
    //controladorServicios


    AdaptadorPedidos mlP  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        LV = (ListView)findViewById(R.id.lista);

        LV.setOnItemClickListener(this);
       // Helper.abrir();
       // pps = Helper.ConsultaPedidos();

        Helper.cerrar();
        mlP = new AdaptadorPedidos(this,R.layout.pedidos_items,pps);
        LV.setAdapter(mlP);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

      /* Intent intx = new Intent(this,DetallePedidoAFragment.class);
        intx.putExtra("idpedido",String.valueOf(mlP.getItem(i).getIdPedido()));
        startActivity(intx); */
    }

    @Override
    public void ResponseWS(Object lista) {

    }
}
