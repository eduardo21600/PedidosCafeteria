package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorUbicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion.agregarDireccion;

public class fragmentUbicacion extends Fragment implements CallbackWS {

    private List<Ubicacion> listaUbi;
    private View v;
    private RecyclerView recyclerView;
    Button agregar;
    ControladorServicios controladorServicios;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        controladorServicios = new ControladorServicios(this);
        controladorServicios.BuscarUbicaciones(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ubicacion_c, container, false);
        agregar = (Button) v.findViewById(R.id.agregar_nueva_direccion);
        recyclerView = (RecyclerView) v.findViewById(R.id.ubicacion_recycler);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), agregarDireccion.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void ResponseWS(Object lista)
    {
        listaUbi = (List<Ubicacion>) lista;
        AdaptadorUbicacion adaptadorUbicacion = new AdaptadorUbicacion(getContext(), listaUbi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorUbicacion);
    }
}