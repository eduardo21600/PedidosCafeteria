package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PedidosRActivity extends AppCompatActivity  {

    //ontroladorBD Helper;
    private ListView LV;
    List<Pedido> pps = new ArrayList<>();
    // ControladorServicios controladorServicios = new ControladorServicios();
    //controladorServicios


    AdaptadorPedidos mlP  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LV = (ListView)findViewById(R.id.lista);
        pps.add(new Pedido(1,2,3,4,5,"sd",6));
        pps.add(new Pedido(2,2,3,4,5,"sd",6));
        pps.add(new Pedido(3,2,3,4,5,"sd",6));
        //LV.setOnItemClickListener(this);
        // Helper.abrir();
        // pps = Helper.ConsultaPedidos();

        //Helper.cerrar();
        mlP = new AdaptadorPedidos(this,R.layout.pedidos_items,pps);
        LV.setAdapter(mlP);
    }

  /*  @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

        //Intent intx = new Intent(this,DetallePedidoRep.class);
        //intx.putExtra("idpedido",String.valueOf(mlP.getItem(i).getIdPedido()));
        //startActivity(intx);
    } */
    public void Ir(View v){
        Intent i = new Intent(this,DetallePedidoRep.class);
        startActivity(i);
    }
}
