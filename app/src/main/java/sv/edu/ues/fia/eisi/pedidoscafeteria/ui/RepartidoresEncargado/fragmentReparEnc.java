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
import android.widget.ImageView;

import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterPedidos;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdapterRepartidor;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoModelo;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Usuario;
import sv.edu.ues.fia.eisi.pedidoscafeteria.cambiarCrede;
import sv.edu.ues.fia.eisi.pedidoscafeteria.crearRepartidor;


public class fragmentReparEnc extends Fragment {

    private RecyclerView recyclerView;
    private List<Usuario> lstRepa,lstusers;
    private ImageView btnEliminar;
    private ControladorBD controladorBD;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_repar_enc, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rvRepar);
        AdapterRepartidor adapter = new AdapterRepartidor(getContext(),lstRepa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        btnEliminar = (ImageView)root.findViewById(R.id.btnEli);

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
        lstRepa = new ArrayList<>();
        controladorBD = new ControladorBD(getContext());
        controladorBD.abrir();
        lstusers=controladorBD.ConsultaUsuarios();
        controladorBD.cerrar();
        if(!lstusers.isEmpty()){
            for (int i = 0; i <lstusers.size() ; i++) {
                if(lstusers.get(i).getIdTipoUsuario()==2){
                    lstRepa.add(lstusers.get(i));
                }
            }
        }


    }
}
