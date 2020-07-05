package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.PedidosEncargado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterPedidos;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.DetallePedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Menu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoAsignado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoRealizado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Usuario;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;
import sv.edu.ues.fia.eisi.pedidoscafeteria.detallePedidoEnc;

public class SlideshowFragment extends Fragment implements CallbackWS {


    private RecyclerView recyclerView;
    private List<Pedido> lstPedidos;
    private ControladorServicios cServicios;
    private List<Pedido> pedido1;
    private ControladorBD controladorBD;
    private List<Local> local,localenCel;
    private List<PedidoRealizado> pRea;
    SharedPreferences sharedPreferences;
    private int ordenResponse,idLocal;
    private boolean seTieneLocal;
    private Chip verWS;
    private boolean hayPedidos;
    private List<DetallePedido> detallePedidos;
    private List<Menu> menus;
    TextView titulo;
    private SwipeRefreshLayout swipe;
    List<Pedido> pedidosEntregados;
    AdapterPedidos adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
       recyclerView = (RecyclerView) root.findViewById(R.id.pedidosRV);
        verWS = (Chip)root.findViewById(R.id.chipWSpedido);
        titulo = (TextView)root.findViewById(R.id.textView9);
        swipe = (SwipeRefreshLayout)root.findViewById(R.id.swipe);
        adapter = new AdapterPedidos(getContext(),pedido1,detallePedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        if(pedido1.isEmpty()){
            titulo.setText(getResources().getString(R.string.noHayPedidosLocal));
            FancyToast.makeText(getContext(),getResources().getString(R.string.noHayPedidosLocal),
                    FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
        }
        verWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verWS.isChecked()){
                    if(lstPedidos.isEmpty()){
                        FancyToast.makeText(getContext(),getResources().getString(R.string.Nohayws),
                                FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    }
                    else {
                        pedido1.addAll(lstPedidos);
                        adapter = new AdapterPedidos(getContext(),pedido1,detallePedidos);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(adapter);
                    }
                }
                else{
                    if(!lstPedidos.isEmpty()){
                        pedido1.removeAll(lstPedidos);
                        adapter = new AdapterPedidos(getContext(),pedido1,detallePedidos);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pedidosEntregados.clear();
                detallePedidos.clear();
                controladorBD.abrir();
                pedido1 = controladorBD.ConsultaPedidosLocal(idLocal);
                for (int i = 0; i <pedido1.size() ; i++) {
                    if(pedido1.get(i).getIdEstadoPedido()==2){
                        pedidosEntregados.add(pedido1.get(i));
                    }
                    else{
                        detallePedidos.add(controladorBD.ConsultaDetallePedido(pedido1.get(i).getIdDetalleP()));
                    }
                }
                pedido1.removeAll(pedidosEntregados);
                controladorBD.cerrar();
                cServicios.BuscarPedidosLocal(idLocal,getContext());
                ordenResponse=3;
                adapter = new AdapterPedidos(getContext(),pedido1,detallePedidos);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                swipe.setRefreshing(false);
            }
        });
        return root;
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final Pedido p = pedido1.get(position);
            final DetallePedido dp = detallePedidos.get(position);
            pedido1.remove(position);
            detallePedidos.remove(position);
            adapter.notifyItemRemoved(position);
                MaterialDialog mDialog = new MaterialDialog.Builder(getActivity())
                        .setTitle("Finalizar pedido")
                        .setMessage("¿Esta seguro que quiere terminar el pedido? Esta acción no puede cambiarse")
                        .setCancelable(false)
                        .setAnimation(R.raw.confirmar)
                        .setPositiveButton("Finalizar", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delete Operation
                                    p.setIdEstadoPedido(2);
                                    pedidosEntregados.add(p);
                                    controladorBD.abrir();
                                    String res = controladorBD.actualizar(p);
                                    controladorBD.cerrar();
                                    FancyToast.makeText(getContext(),res,FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS,false).show();
                                    dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Cancelar", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                pedido1.add(position,p);
                                detallePedidos.add(position,dp);
                                adapter.notifyItemInserted(position);
                                dialogInterface.dismiss();
                            }
                        })
                        .build();

                // Show Dialog
                mDialog.show();
            }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstPedidos = new ArrayList<>();
        pedido1 = new ArrayList<>();
        hayPedidos=false;
        detallePedidos = new ArrayList<>();
        menus = new ArrayList<>();
        sharedPreferences = this.getActivity().getSharedPreferences("validacion",0);
        String id = sharedPreferences.getString("nombreUsuario","");
        controladorBD = new ControladorBD(getContext());
        pedidosEntregados  = new ArrayList<>();

        controladorBD.abrir();
        localenCel = controladorBD.ConsultaLocales();
        cServicios = new ControladorServicios(this);
        for (int i = 0; i <localenCel.size() ; i++) {
            if(localenCel.get(i).getIdUsuario().equals(id)){
                seTieneLocal=true;
                hayPedidos = true;
                idLocal=localenCel.get(i).getIdLocal();
                break;
            }
            else {
                seTieneLocal=false;
            }
        }
        if(!seTieneLocal){
            cServicios.BuscarLocalUsu(id,getContext());
            ordenResponse=1;
        }
        else{
            pedido1 = controladorBD.ConsultaPedidosLocal(idLocal);
            for (int i = 0; i <pedido1.size() ; i++) {
                if(pedido1.get(i).getIdEstadoPedido()==2){
                    pedidosEntregados.add(pedido1.get(i));
                }
                else{
                    detallePedidos.add(controladorBD.ConsultaDetallePedido(pedido1.get(i).getIdDetalleP()));
                }
            }
            pedido1.removeAll(pedidosEntregados);
            controladorBD.cerrar();
            cServicios.BuscarPedidosLocal(idLocal,getContext());
            ordenResponse=3;
        }

    }

    @Override
    public void ResponseWS(Object lista) {
        if(ordenResponse==1){
            local = (List<Local>) lista;
            Local l = new Local(
                    local.get(0).getIdLocal(),
                    local.get(0).getNombreLocal(),
                    local.get(0).getIdUsuario()
            );
            controladorBD.abrir();
            controladorBD.CrearLocal(l);
            controladorBD.cerrar();
            cServicios.BuscarPedidosLocal(l.getIdLocal(),getContext());
            ordenResponse=2;
        }
        else if(ordenResponse==2){
            pedido1 = (List<Pedido>) lista;
            if(!pedido1.isEmpty()){
                hayPedidos = true;
                adapter = new AdapterPedidos(getContext(),pedido1);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
                ordenResponse=4;
            }
        }
        else if (ordenResponse==3){
            lstPedidos =(List<Pedido>)lista;
            ordenResponse=4;
        }
    }
}
