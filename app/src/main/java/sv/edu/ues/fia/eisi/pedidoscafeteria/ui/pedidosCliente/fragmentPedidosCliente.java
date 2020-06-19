package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.pedidosCliente;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorDetallePedidoC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorLocal;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.DetallePedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPedidosCliente extends Fragment {

    private List<DetallePedido> listDP;
    private View v;
    private RecyclerView recyclerView;
    private ControladorBD controladorBD;
    SharedPreferences sharedPreferences;
    String usu;

    public fragmentPedidosCliente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_pedidos_cliente, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.pedidos_recycler_c);
        if(listDP.isEmpty())
        {
            FancyToast.makeText(getContext(), "No hay pedidos realizados", FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
        }
        else
        {
            AdaptadorDetallePedidoC adaptadorDetallePedidoC = new AdaptadorDetallePedidoC(getContext(), listDP);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adaptadorDetallePedidoC);
        }
        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorBD = new ControladorBD(getContext());
        controladorBD.abrir();
        listDP = controladorBD.ConsultaDetallePedidoRealizado(usu);
        controladorBD.cerrar();
    }
}
