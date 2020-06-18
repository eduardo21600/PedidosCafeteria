package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuEncargado;

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

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuE;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.InsertarMenu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Menu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class MenuFragment extends Fragment {

    View v;
    View card;
    private List<Menu> listaMenu;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_menu, container, false);

        listaMenu = new ArrayList<Menu>();
        ControladorBD help = new ControladorBD(getContext());
        help.abrir();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewMenu);
        AdaptadorMenuE adaptadorMenu = new AdaptadorMenuE (help.ConsultaMenus());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorMenu);
        help.cerrar();
        Button crear = (Button)v.findViewById(R.id.btnCrearMenu);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertarMenu.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return v;   }
}
