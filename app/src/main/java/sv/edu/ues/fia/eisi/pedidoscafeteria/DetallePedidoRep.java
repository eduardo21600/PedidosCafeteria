package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class DetallePedidoRep extends AppCompatActivity {

    ControladorBD bd;
    private EditText codigo;
    private EditText descripcion;
    private EditText nomCliente;
    private EditText total;
    private EditText ubicacion;
    private Pedido ped;
    private Menu menu;
    private PedidoRealizado pediReal;
    private PedidoAsignado pedidoAsignado;
    private Usuario usuario;
    private Ubicacion ubic;
    private Button marcar;
    private DetallePedido detallePedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_rep);

        bd = new ControladorBD(getApplicationContext());

        Intent intent = getIntent();
        bd.abrir();
        ped = bd.consultarPedidoRep(intent.getStringExtra("idPedido"));

        codigo = (EditText) findViewById(R.id.edtCodigo);
        descripcion = (EditText) findViewById(R.id.edtDescripcion);
        nomCliente = (EditText) findViewById(R.id.edtCliente);
        total = (EditText) findViewById(R.id.edtTotal);
        ubicacion = (EditText) findViewById(R.id.edtUbicacion);
        marcar = (Button) findViewById(R.id.buttonMarcar);

        detallePedido = bd.ConsultaDetallePedido(ped.getIdDetalleP());
        menu = bd.ConsultaMenu(String.valueOf(detallePedido.getIdMenu()));
        ubic = bd.consultarUbicacion(String.valueOf(ped.getIdUbicacion()));
        pediReal = bd.consultarPedRealIdP(String.valueOf(ped.getIdPedido()));
        pedidoAsignado = bd.consultarPedAsigID(String.valueOf(ped.getIdPedido()));
        usuario = bd.ConsultaUsuario(pedidoAsignado.getIdUsuario());

        if(ped == null)
        {
            FancyToast.makeText(getApplicationContext(), String.valueOf(R.string.mensajeRep), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
        }else
        {
            codigo.setText(Integer.toString(ped.getIdPedido()));
            descripcion.setText(menu.getNomMenu());
            nomCliente.setText(pediReal.getIdUsuario());
            total.setText(String.valueOf(ped.getTotalPedido()));
            ubicacion.setText(ubic.getDirecUbicacion());
        }

        bd.cerrar();
    }

    public void marcarEntregado(View view){

        if(ped.getIdEstadoPedido()==1){
            ped.setIdEstadoPedido(2);
            marcar.setText(R.string.estadoPedido);
            if(ped.getIdEstadoPedido()==2){
                usuario.setEstado("1");
            }
            bd.abrir();
            bd.actualizar(ped);
            bd.actualizarUsuario(usuario);
            bd.cerrar();
        }
        else if(ped.getIdEstadoPedido()==2)
        {
            ped.setIdEstadoPedido(1);
            marcar.setText(R.string.marcar);
            if(ped.getIdEstadoPedido()==1){
                usuario.setEstado("2");
            }
            bd.abrir();
            bd.actualizar(ped);
            bd.actualizarUsuario(usuario);
            bd.cerrar();
        }
    }
}
