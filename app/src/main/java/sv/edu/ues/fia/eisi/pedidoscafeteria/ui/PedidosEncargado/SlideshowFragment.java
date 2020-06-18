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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterPedidos;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoAsignado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoRealizado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Usuario;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

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
    private ImageView ivRefresh;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
       recyclerView = (RecyclerView) root.findViewById(R.id.pedidosRV);
        verWS = (Chip)root.findViewById(R.id.chipWSpedido);
        ivRefresh = (ImageView)root.findViewById(R.id.ivRefrescar);
        final AdapterPedidos adapter = new AdapterPedidos(getContext(),pedido1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
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
                        adapter.notifyDataSetChanged();
                    }
                }
                else{
                    if(!lstPedidos.isEmpty()){
                        pedido1.removeAll(lstPedidos);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstPedidos = new ArrayList<>();
        sharedPreferences = this.getActivity().getSharedPreferences("validacion",0);
        String id = sharedPreferences.getString("nombreUsuario","");
        controladorBD = new ControladorBD(getContext());

        controladorBD.abrir();
        localenCel = controladorBD.ConsultaLocales();
        cServicios = new ControladorServicios(this);
        for (int i = 0; i <localenCel.size() ; i++) {
            if(localenCel.get(i).getIdUsuario().equals(id)){
                seTieneLocal=true;
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
            AdapterPedidos adapter = new AdapterPedidos(getContext(),pedido1);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            ordenResponse=4;
        }
        else if (ordenResponse==3){
            lstPedidos =(List<Pedido>)lista;
            ordenResponse=4;
        }
    }
}
