package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.AbstractDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
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
    private Usuario usuario,usuario2;
    private Ubicacion ubic;
    private Button marcar;
    private DetallePedido detallePedido;
    List<Chat> chat=new ArrayList<> ();
    List<Mensaje> mensajes=new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_rep);

        bd = new ControladorBD(getApplicationContext());

        Intent intent = getIntent();
        bd.abrir();
        ped = bd.consultarPedidoRep(intent.getStringExtra("id"));

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
        usuario2=bd.ConsultaUsuario (pediReal.getIdUsuario ());
        chat= bd.ConsultaChat(new Chat(usuario.getIdUsuario (),usuario2.getIdUsuario ()));
        if(!(chat==null)){
            mensajes=bd.ConsultaMensaje(chat.get(0).getIdchat());
        }
        else
        {
            Toast.makeText(this, "No hay mensajes :c", Toast.LENGTH_SHORT).show();
        }


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

        MaterialDialog.Builder builder = new MaterialDialog.Builder(DetallePedidoRep.this);
        builder.setTitle("Finalizar pedido");
        builder.setMessage("¿Esta seguro que quiere terminar el pedido? Esta acción no puede cambiarse");
        builder.setCancelable(false);
        builder.setAnimation(R.raw.confirmar);
        builder.setPositiveButton("Finalizar", new MaterialDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                // Delete Operation
                if (ped.getIdEstadoPedido() == 1) {
                    ped.setIdEstadoPedido(2);
                    marcar.setText(R.string.estadoPedido);
                    marcar.setClickable(false);
                    if (ped.getIdEstadoPedido() == 2) {
                        usuario.setEstado("1");
                    }
                    bd.abrir();
                    bd.actualizar(ped);
                    bd.actualizarUsuario(usuario);
                    bd.eliminar(pedidoAsignado);
                    for (int i =0; i<mensajes.size ();i++){
                        bd.eliminarMensaje(mensajes.get(i));
                    }
                    bd.cerrar();
                    dialogInterface.dismiss();
                }
            }
        });
        builder.setNegativeButton("Cancelar", new MaterialDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                marcar.setClickable(true);
            }
        });
        MaterialDialog mDialog = builder
                .build();

        // Show Dialog
        mDialog.show();
    }
}
