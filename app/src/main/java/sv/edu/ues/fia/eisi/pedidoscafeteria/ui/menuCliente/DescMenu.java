package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorDescripcionMenu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.DetallePedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoRealizado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente.SeleccionarUbicacion;

public class DescMenu extends AppCompatActivity implements CallbackWS {

    private final int SECOND_ACTIVITY = 1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    int idMenu,idLocal,c;
    String nomMenu, direccion;
    double precionMenu;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;
    List<Producto> productos;
    TextView tv_nom, tv_precio;
    Button agregarPedido;
    EditText cantidad;
    SharedPreferences sharedPreferences;
    String usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_menu);
        Intent intent = getIntent();
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        idMenu = intent.getIntExtra("idMenu", 0);
        idLocal = intent.getIntExtra("idLocal", 0);
        nomMenu = intent.getStringExtra("nomMenu");
        precionMenu = intent.getDoubleExtra("precioMenu", 0.00);
        tv_nom = (TextView) findViewById(R.id.nombre_menu_desc);
        tv_precio = (TextView) findViewById(R.id.precio_menu_desc);
        agregarPedido = (Button) findViewById(R.id.agregar_pedido_desc);
        cantidad = (EditText) findViewById(R.id.cantidad_menu);
        tv_nom.setText(nomMenu);
        tv_precio.setText("$"+String.valueOf(precionMenu));
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        productos = controladorBD.ConsultaProductosMenu(idMenu);
        controladorBD.cerrar();
        //controladorServicios = new ControladorServicios(this);
        //controladorServicios.BuscarProductoMenu(idMenu, getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.descMenu_productos_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorDescripcionMenu(this, productos);
        recyclerView.setAdapter(adapter);

        agregarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarPedido();
            }
        });

    }

    @Override
    public void ResponseWS(Object lista)
    {
        productos = (List<Producto>) lista;
        adapter = new AdaptadorDescripcionMenu(this, productos);
        recyclerView.setAdapter(adapter);
    }

    public void realizarPedido()
    {
        if(!String.valueOf(cantidad.getText()).isEmpty())
        {
            try
            {
                c = Integer.parseInt(String.valueOf(cantidad.getText()));
                Intent intent = new Intent(getApplicationContext(), SeleccionarUbicacion.class);
                startActivityForResult(intent, SECOND_ACTIVITY);
            }
            catch(Exception e)
            {
                FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.num_entero), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
            }
        }
        else
        {
            FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.inserte_cantidad), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SECOND_ACTIVITY)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                final int idUbicacion=data.getIntExtra("idUbicacion", 999);
                direccion = data.getStringExtra("direccion");
                MaterialDialog mDialog = new MaterialDialog.Builder(this)
                        .setTitle(getResources().getString(R.string.realizar_este_pedido))
                        .setAnimation(R.raw.confirmar)
                        .setMessage(nomMenu+"\n$"+precionMenu+"\n"+
                                getResources().getString(R.string.cantidad)+c+"\n"+
                                getResources().getString(R.string.total_a_cancelar)+c*precionMenu)
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.ordenar), new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which)
                            {
                                controladorBD.abrir();
                                DetallePedido nuevoDetallePedido = new DetallePedido();
                                nuevoDetallePedido.setCantidad(c);
                                nuevoDetallePedido.setSubtotal(c*precionMenu);
                                nuevoDetallePedido.setIdMenu(idMenu);
                                String resultado = controladorBD.Crear(nuevoDetallePedido);
                                if(resultado.equals("detalle de pedido creado "))
                                {
                                    DetallePedido detallePedido = controladorBD.ultimoIdDetallePedido();
                                    Pedido nuevoPedido = new Pedido();
                                    nuevoPedido.setIdDetalleP(detallePedido.getIdDetallePedido());
                                    nuevoPedido.setIdEstadoPedido(1);
                                    nuevoPedido.setIdLocal(idLocal);
                                    nuevoPedido.setIdUbicacion(idUbicacion);
                                    nuevoPedido.setTotalPedido(c*precionMenu);
                                    Date c = Calendar.getInstance().getTime();
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                    String fecha = df.format(c);
                                    nuevoPedido.setFechaPedido(fecha);
                                    resultado = controladorBD.insertar(nuevoPedido);
                                    if (resultado.equals("Se guardó correctamente nuevo pedido "))
                                    {
                                        PedidoRealizado pedidoRealizado = new PedidoRealizado();
                                        pedidoRealizado.setIdPedido(controladorBD.ultimoIdPedido());
                                        pedidoRealizado.setIdUsuario(usu);
                                        pedidoRealizado.setTipo("Llevar");
                                        resultado = controladorBD.insertar(pedidoRealizado);
                                        if (resultado.equals("Se guardó correctamente pedidoRealizado N°: "))
                                        {
                                            FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.pedido_realizado), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
                                            cantidad.setText("");
                                        }
                                        else
                                        {
                                            FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.pedido_realizado)+ "\n" + resultado, FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
                                        }
                                    }
                                    else
                                    {
                                        FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.pedido_realizado)+ "\n" + resultado, FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
                                    }
                                }
                                else
                                {
                                    FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.pedido_realizado)+ "\n" + resultado, FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
                                }
                                controladorBD.cerrar();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.cancelar), new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                mDialog.show();
            }
            else
            {
                FancyToast.makeText(getApplicationContext(), getResources().getString(R.string.no_ubicacion), FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
            }
        }
    }
}