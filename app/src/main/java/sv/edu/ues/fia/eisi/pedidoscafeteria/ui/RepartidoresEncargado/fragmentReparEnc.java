package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.RepartidoresEncargado;

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

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterPedidos;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterRepartidor;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Usuario;



public class fragmentReparEnc extends Fragment {
  /*  // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragmentReparEnc() {
        // Required empty public constructor
    }*/

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentReparEnc.
     */
    // TODO: Rename and change types and number of parameters
    /*public static fragmentReparEnc newInstance(String param1, String param2) {
        fragmentReparEnc fragment = new fragmentReparEnc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    */
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

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstRepa = new ArrayList<>();
        lstRepa.add(new Usuario(1,"Pablo","22577777"));
        lstRepa.add(new Usuario(2,"Pablo","22577777"));
        lstRepa.add(new Usuario(3,"Pablo","22577777"));
    }
}
