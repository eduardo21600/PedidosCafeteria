package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.localCliente;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorLocal;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentLocalCliente extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private List<Local> listLocal;


    public fragmentLocalCliente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v =  inflater.inflate(R.layout.fragment_local_cliente, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.local_recycler);
        AdaptadorLocal adaptadorLocal = new AdaptadorLocal(getContext(), listLocal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorLocal);
        // Inflate the layout for this fragment
        return v;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listLocal = new ArrayList<Local>();
        listLocal.add(new Local(1,R.drawable.food,"Pollo Campero"));
        listLocal.add(new Local(2,R.drawable.food,"KFC"));
        listLocal.add(new Local(3,R.drawable.food,"Pizza Hut"));
    }
}
