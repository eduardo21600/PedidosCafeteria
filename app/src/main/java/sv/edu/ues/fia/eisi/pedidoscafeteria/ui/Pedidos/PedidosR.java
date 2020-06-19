package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.Pedidos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorPedido1;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorProducto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;


public class PedidosR extends Fragment  {
    private RecyclerView recyclerView;
    private View v;
    private List<Pedido> listaPedido;
    //ControladorServicios controladorServicios;

    public PedidosR() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // controladorServicios = new ControladorServicios(this);
       // controladorServicios.BuscarProductos(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_pedidos_r, container, false);
        listaPedido = new ArrayList<Pedido>();
        ControladorBD help = new ControladorBD(getContext());
        help.abrir();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_pedidoR);
        AdaptadorPedido1 adaptadorPedido = new AdaptadorPedido1(help.ConsultaPedidos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorPedido);
        help.cerrar();


        // Inflate the layout for this fragment
        return v;
    }

   // @Override
  /*  public void ResponseWS(Object lista)
    {
        listaProducto = (List<Producto>) lista;
        AdaptadorProducto adaptadorProducto = new AdaptadorProducto (listaProducto);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorProducto);
    }*/
}
