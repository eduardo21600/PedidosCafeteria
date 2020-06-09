package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.RepartidoresEncargado;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterPedidos;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterRepartidor;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Usuario;
import sv.edu.ues.fia.eisi.pedidoscafeteria.cambiarCrede;
import sv.edu.ues.fia.eisi.pedidoscafeteria.crearRepartidor;


public class fragmentReparEnc extends Fragment {

    private RecyclerView recyclerView;
    private List<Usuario> lstRepa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_repar_enc, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvRepar);
        AdapterRepartidor adapter = new AdapterRepartidor(getContext(),lstRepa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        Button crear = (Button)root.findViewById(R.id.btnAgregarR);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), crearRepartidor.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstRepa = new ArrayList<>();
        lstRepa.add(new Usuario("1","Pablo","22577777"));
        lstRepa.add(new Usuario("2","Pablo","22577777"));
        lstRepa.add(new Usuario("3","Pablo","22577777"));
    }
}
