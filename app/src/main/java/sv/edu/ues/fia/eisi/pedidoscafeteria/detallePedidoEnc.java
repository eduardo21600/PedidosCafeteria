package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class detallePedidoEnc extends AppCompatActivity implements CallbackWS {

    String idPedido, total;
    int idUbicacion,idDeP;
    EditText idCodigo, productos, cliente, totalPago, ubicacion,tipo;
    Button asignar;
    ControladorServicios cServ;
    int ordenResponse;
    List<PedidoRealizado> pR;
    List<DetallePedido> lstDp;
    List<Menu> lstMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_enc);
        Intent intent = getIntent();
        idPedido = intent.getStringExtra("idPedido");
        total = intent.getStringExtra("total");
        idUbicacion = intent.getIntExtra("idUbicacion",0);
        idDeP = intent.getIntExtra("idDetalleP",0);
        idCodigo =(EditText)findViewById(R.id.etCodigo);
        productos =(EditText)findViewById(R.id.etProducto);
        cliente =(EditText)findViewById(R.id.etCliente);
        totalPago =(EditText)findViewById(R.id.etTotal);
        ubicacion =(EditText)findViewById(R.id.etUbicacion);
        tipo = (EditText)findViewById(R.id.etTipo);
        asignar = (Button)findViewById(R.id.btnAsignar);
        cServ = new ControladorServicios(this);
        idCodigo.setText(idPedido);
        totalPago.setText(total);
        ubicacion.setText(String.valueOf(idUbicacion));
        productos.setText(String.valueOf(idDeP));

        cServ.BuscarPedidoRealizado(Integer.parseInt(idPedido),getApplicationContext());
        ordenResponse=1;
    }

    @Override
    public void ResponseWS(Object lista) {
        if(ordenResponse==1){
            pR = (List<PedidoRealizado>) lista;
            cliente.setText(pR.get(0).getIdUsuario());
            tipo.setText(pR.get(0).getTipo());
            ordenResponse=2;
            cServ.BuscarDetallePedido(idDeP,getApplicationContext());
        }
        else if (ordenResponse==2){
            lstDp = (List<DetallePedido>)lista;
            ordenResponse=3;
            cServ.BuscarMenu(lstDp.get(0).getIdMenu(),getApplicationContext());
        }
        else if (ordenResponse==3){
            lstMenu =(List<Menu>) lista;
            productos.setText(lstMenu.get(0).getNomMenu());
            ordenResponse=4;
        }

    }
}
