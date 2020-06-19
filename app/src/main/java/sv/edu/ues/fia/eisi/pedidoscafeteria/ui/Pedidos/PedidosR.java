package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.Pedidos;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

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
    String usu;
    SharedPreferences sharedPreferences;
    //ControladorServicios controladorServicios;

    public PedidosR() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ControladorBD help = new ControladorBD(getContext());
        sharedPreferences = getContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        help.abrir();
       listaPedido = help.ConsultaPedidoR(usu);
        help.cerrar();
        if(listaPedido.isEmpty())
        {
            FancyToast.makeText(getContext(), "No tienes Pedidos asignados",FancyToast.INFO, R.drawable.error,  false).show();
        }
       // controladorServicios = new ControladorServicios(this);
       // controladorServicios.BuscarProductos(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_pedidos_r, container, false);
        listaPedido = new ArrayList<Pedido>();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_pedidoR);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdaptadorPedido1 adaptadorPedido = new AdaptadorPedido1(listaPedido);
        recyclerView.setAdapter(adaptadorPedido);



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
