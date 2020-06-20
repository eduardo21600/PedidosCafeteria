package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.annotation.Nullable;
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
import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente.SeleccionarUbicacion;

public class detallePedidoEnc extends AppCompatActivity implements CallbackWS, CallbackRespuestaString {

    String total,fecha,idPedido,idRepa;
    private final int SECOND_ACTIVITY = 1;
    int idUbicacion,idDeP,idEstadoP,idLocal;
    EditText idCodigo, productos, cliente, totalPago, ubicacion,tipo;
    Button asignar;
    ControladorServicios cServ;
    ControladorBD controladorBD;
    int ordenResponse;
    List<PedidoRealizado> pR;
    List<DetallePedido> lstDp;
    List<Menu> lstMenu;
    ToggleButton estadoPedido;
    Pedido p;
    List <Ubicacion> ubiC;
    PedidoRealizado pediReaLocal;
    DetallePedido detallePedidoLocal;
    Menu menuLocal;
    Ubicacion ubicacionLocal;
    boolean estaLocal;

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
        estaLocal=true;
        idCodigo =(EditText)findViewById(R.id.etCodigoDP);
        productos =(EditText)findViewById(R.id.etProductoDP);
        cliente =(EditText)findViewById(R.id.etClienteDP);
        totalPago =(EditText)findViewById(R.id.etTotalDP);
        ubicacion =(EditText)findViewById(R.id.etUbicacionDP);
        tipo = (EditText)findViewById(R.id.etTipoDP);
        asignar = (Button)findViewById(R.id.btnAsignar);
        estadoPedido = (ToggleButton)findViewById(R.id.toggleButton);
        controladorBD = new ControladorBD(getApplicationContext());
        cServ = new ControladorServicios(this,this);
        idCodigo.setText(String.valueOf(idPedido));
        totalPago.setText(total);
        //ubicacion.setText(String.valueOf(idUbicacion));
        productos.setText(String.valueOf(idDeP));

        if(idEstadoP==2){
            estadoPedido.setVisibility(View.INVISIBLE);
            asignar.setVisibility(View.INVISIBLE);
        }

        controladorBD.abrir();
        pediReaLocal = controladorBD.consultarPedRealIdP(idPedido);

        if(pediReaLocal==null){
            cServ.BuscarPedidoRealizado(Integer.parseInt(idPedido),getApplicationContext());
            ordenResponse=1;
            estaLocal=false;
        }
        else{
            detallePedidoLocal=controladorBD.ConsultaDetallePedido(idDeP);
            menuLocal = controladorBD.ConsultaMenu(String.valueOf(detallePedidoLocal.getIdMenu()));
            ubicacionLocal = controladorBD.consultarUbicacion(String.valueOf(idUbicacion));
            tipo.setText(pediReaLocal.getTipo());
            productos.setText(menuLocal.getNomMenu());
            cliente.setText(pediReaLocal.getIdUsuario());
            ubicacion.setText(ubicacionLocal.getDirecUbicacion());
        }

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarRepaAlPedido();
            }
        });

        estadoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadoPedido.isChecked()){
                    p = new Pedido(
                            Integer.valueOf(idPedido), idDeP,2,idLocal,idUbicacion,fecha,Double.parseDouble(total)
                    );
                    MaterialDialog mDialog = new MaterialDialog.Builder(detallePedidoEnc.this)
                            .setTitle("Finalizar pedido")
                            .setMessage("¿Esta seguro que quiere terminar el pedido? Esta acción no puede cambiarse")
                            .setCancelable(false)
                            .setPositiveButton("Finalizar", new MaterialDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    // Delete Operation
                                    if(estaLocal){
                                        String res = controladorBD.actualizar(p);
                                        FancyToast.makeText(getApplicationContext(),res,FancyToast.LENGTH_LONG,
                                                FancyToast.SUCCESS,false).show();
                                        estadoPedido.setVisibility(View.INVISIBLE);
                                        asignar.setVisibility(View.INVISIBLE);
                                        dialogInterface.dismiss();
                                    }
                                    else{
                                        cServ.Act(p,getApplicationContext());
                                        dialogInterface.dismiss();
                                    }
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

    public void agregarRepaAlPedido(){
        Intent intent = new Intent(getApplicationContext(), AsignarRepartidor.class);
        startActivityForResult(intent, SECOND_ACTIVITY);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SECOND_ACTIVITY){
            if(resultCode==Activity.RESULT_OK){
                idRepa = data.getStringExtra("idRepa");
                Usuario repaAct= controladorBD.ConsultaUsuario(idRepa);
                repaAct.setEstado("2");
                PedidoAsignado pedidoAsignado= new PedidoAsignado(
                        Integer.valueOf(idPedido),idRepa);
                controladorBD.insertar(pedidoAsignado);
                controladorBD.actualizarUsuario(repaAct);

            }
            else{
                FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.noSeleccionoRepa),
                        FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controladorBD.cerrar();
    }
}


