package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.confCliente;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.cambiarCrede;
import sv.edu.ues.fia.eisi.pedidoscafeteria.login;

public class fragmentConfig extends Fragment {

   /* public fragmentConfig() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_config, container, false);
        Button cambiar = (Button) root.findViewById(R.id.btnCambiarC);
        Button ayuda = (Button)root.findViewById(R.id.button);
        Button salir = (Button)root.findViewById(R.id.button2);
        final String url = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/Manual.pdf";
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), cambiarCrede.class);
                startActivity(intent);
            }
        });

        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri URI = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,URI);
                startActivity(intent);
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
                getActivity().finish();

            }
        });
        return root;

    }
}
