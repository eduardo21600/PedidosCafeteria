package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackRespuestaString;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class detallePedidoEnc extends AppCompatActivity implements CallbackWS, CallbackRespuestaString {

    String idPedido, total,fecha;
    int idUbicacion,idDeP,idEstadoP,idLocal;
    EditText idCodigo, productos, cliente, totalPago, ubicacion,tipo;
    Button asignar;
    ControladorServicios cServ;
    int ordenResponse;
    List<PedidoRealizado> pR;
    List<DetallePedido> lstDp;
    List<Menu> lstMenu;
    ToggleButton estadoPedido;
    Pedido p;
    List <Ubicacion> ubiC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedido_enc);
        Intent intent = getIntent();
        idPedido = intent.getStringExtra("idPedido");
        total = intent.getStringExtra("total");
        idUbicacion = intent.getIntExtra("idUbicacion",3);
        idDeP = intent.getIntExtra("idDetalleP",0);
        idEstadoP = intent.getIntExtra("idEstadoP",0);
        idLocal = intent.getIntExtra("idLocal",0);
        fecha = intent.getStringExtra("fecha");
        idCodigo =(EditText)findViewById(R.id.etCodigo);
        productos =(EditText)findViewById(R.id.etProducto);
        cliente =(EditText)findViewById(R.id.etCliente);
        totalPago =(EditText)findViewById(R.id.etTotal);
        ubicacion =(EditText)findViewById(R.id.etUbicacion);
        tipo = (EditText)findViewById(R.id.etTipo);
        asignar = (Button)findViewById(R.id.btnAsignar);
        estadoPedido = (ToggleButton)findViewById(R.id.toggleButton);
        cServ = new ControladorServicios(this,this);
        idCodigo.setText(idPedido);
        totalPago.setText(total);
        //ubicacion.setText(String.valueOf(idUbicacion));
        productos.setText(String.valueOf(idDeP));

        if(idEstadoP==2){
            estadoPedido.setVisibility(View.INVISIBLE);
        }
        cServ.BuscarPedidoRealizado(Integer.parseInt(idPedido),getApplicationContext());
        ordenResponse=1;
        estadoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadoPedido.isChecked()){
                    p = new Pedido(
                            Integer.parseInt(idPedido), idDeP,2,idLocal,idUbicacion,fecha,Double.parseDouble(total)
                    );
                    MaterialDialog mDialog = new MaterialDialog.Builder(detallePedidoEnc.this)
                            .setTitle("Finalizar pedido")
                            .setMessage("¿Esta seguro que quiere terminar el pedido? Esta acción no puede cambiarse")
                            .setCancelable(false)
                            .setPositiveButton("Finalizar", new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    // Delete Operation
                                    cServ.Act(p,getApplicationContext());
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton("Cancelar", new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    dialogInterface.dismiss();
                                    estadoPedido.setChecked(false);
                                }
                            })
                            .build();

                    // Show Dialog
                    mDialog.show();
                }
            }
        });
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
            cServ.BuscarUbicacion(idUbicacion,getApplicationContext());
        }
        else if (ordenResponse==3){
            ubiC = (List<Ubicacion>)lista;
            ubicacion.setText(ubiC.get(0).getDirecUbicacion());
            ordenResponse=4;
            cServ.BuscarMenu(lstDp.get(0).getIdMenu(),getApplicationContext());
        }
        else if(ordenResponse==4){
            lstMenu =(List<Menu>) lista;
            productos.setText(lstMenu.get(0).getNomMenu());
            ordenResponse=5;
        }

    }

    @Override
    public void respuesta(String respuesta) {
        if(respuesta.equals("CONEXIÓN EXITOSA")){
            FancyToast.makeText(getApplicationContext(),"Se finalizó el pedido",
                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
            finish();
        }
    }
}
