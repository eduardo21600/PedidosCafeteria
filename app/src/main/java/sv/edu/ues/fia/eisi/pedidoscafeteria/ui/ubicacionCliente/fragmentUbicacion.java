package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorDetallePedidoC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorUbicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;

public class fragmentUbicacion extends Fragment {

    private List<Ubicacion> listaUbi;
    private View v;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        listaUbi = new ArrayList<Ubicacion>();

        listaUbi.add(new Ubicacion(1, "2", 3, "Av. Las Margaritas Casa #114", "Mi Casa", "Enfrente de Iglesia"));
        listaUbi.add(new Ubicacion(1, "2", 3, "Sierra Morena 1", "Lidy", "Enfrente de pupusería Delichely"));
        listaUbi.add(new Ubicacion(1, "2", 3, "Final calle las rosas", "Papá", "Casa 8-B"));
        listaUbi.add(new Ubicacion(1, "2", 3, "Las jacarandas", "Moya", "Enfrente de Balneario La Hacienda"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ubicacion_c, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.ubicacion_recycler);
        AdaptadorUbicacion adaptadorUbicacion = new AdaptadorUbicacion(getContext(), listaUbi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorUbicacion);

        return v;
    }
}