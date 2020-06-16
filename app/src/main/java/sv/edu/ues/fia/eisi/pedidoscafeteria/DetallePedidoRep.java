package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class DetallePedidoRep extends AppCompatActivity {
    ControladorServicios serv;

    ControladorBD bd;
    private EditText codigo;
    private EditText descripcion;
    private EditText nomCliente;
    private EditText total;
    private EditText ubicacion;
    private Pedido ped;
    private Menu menu;
    private PedidoRealizado pediReal;
    private Usuario usuario;
    private Ubicacion ubic;
    private Button marcar;
    private List<Pedido> pedido1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_rep);

        Bundle bundle = getIntent().getExtras();
        bd.abrir();
        ped = bd.consultarPedido(bundle.getString("idPedido"));
        bd.cerrar();

        pedido1 = serv.BuscarPedido(Integer.parseInt(bundle.getString("idPedido")), this);

        codigo = (EditText) findViewById(R.id.edtCodigo);
        descripcion = (EditText) findViewById(R.id.edtDescripcion);
        nomCliente = (EditText) findViewById(R.id.edtCliente);
        total = (EditText) findViewById(R.id.edtTotal);
        ubicacion = (EditText) findViewById(R.id.edtUbicacion);
        marcar = (Button) findViewById(R.id.buttonMarcar);

        String cadDescripcion= "";
        List<DetallePedido> detallePedidos = ped.getDetallePedidos();
        for(int i=0; i<detallePedidos.size();i++)
        {
            menu.setIdMenu(detallePedidos.get(i).getIdMenu());
            cadDescripcion = cadDescripcion + detallePedidos.get(i).getCantidad() + " " + menu.getNomMenu() + ", ";
        }

        pediReal.setIdPedido(ped.getIdPedido());
        usuario.setIdUsuario(pediReal.getIdUsuario());

        ubic.setIdUbicacion(ped.getIdUbicacion());

        codigo.setText(ped.getIdPedido());
        descripcion.setText(cadDescripcion);
        nomCliente.setText(usuario.getNombreUsuario());
        total.setText(String.valueOf(ped.getTotalPedido()));
        ubicacion.setText(ubic.getNomUbicacion());
    }

    public void marcarEntregado(View view){

        if(ped.getIdEstadoPedido()==0){
            ped.setIdEstadoPedido(1);
            marcar.setText(R.string.estadoPedido);
            bd.abrir();
            bd.actualizar(ped);
            bd.cerrar();
        }
        else if(ped.getIdEstadoPedido()==1)
        {
            ped.setIdEstadoPedido(0);
            marcar.setText(R.string.marcar);
            bd.abrir();
            bd.actualizar(ped);
            bd.cerrar();
        }
    }
}
