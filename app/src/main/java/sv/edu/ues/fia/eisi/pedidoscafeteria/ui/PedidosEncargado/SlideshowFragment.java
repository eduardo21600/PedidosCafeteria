package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.PedidosEncargado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterPedidos;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class SlideshowFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<PedidoModelo> lstPedidos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);*/

       /* final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
       View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
       recyclerView = (RecyclerView) root.findViewById(R.id.pedidosRV);
        AdapterPedidos adapter = new AdapterPedidos(getContext(),lstPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstPedidos = new ArrayList<>();
        lstPedidos.add(new PedidoModelo(1,"Claudia","Llevar"));
        lstPedidos.add(new PedidoModelo(2,"Roberto","Traer"));
        lstPedidos.add(new PedidoModelo(3,"Paulina","Llevar"));
    }
}
