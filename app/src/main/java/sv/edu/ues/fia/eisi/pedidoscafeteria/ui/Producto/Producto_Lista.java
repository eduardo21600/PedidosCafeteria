package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.Producto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorProducto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ProductoIngresar;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class Producto_Lista extends Fragment  {
    View v;
    private RecyclerView recyclerView;
    private List<Producto> listaProducto;

    public Producto_Lista() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_producto__lista, container, false);
        listaProducto = new ArrayList<Producto>();
        ControladorBD help = new ControladorBD(getContext());
        help.abrir();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_producto);
        AdaptadorProducto adaptadorProducto = new AdaptadorProducto (help.ConsultaProductos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorProducto);
        help.cerrar();


        // Inflate the layout for this fragment
        return v;
           }


}
