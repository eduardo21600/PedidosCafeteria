package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePedidoAFragment extends Fragment {
    private EditText descPedido;
    private EditText nomCliente;
    private EditText totPedido;
    private EditText direccion;

    public DetallePedidoAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_pedido_a,container,false);
        descPedido=view.findViewById(R.id.editDescripcion);
        nomCliente=view.findViewById(R.id.editCliente);
        totPedido=view.findViewById(R.id.editTotal);
        direccion=view.findViewById(R.id.editDireccion);

        String codigo = getArguments().getString("idpedido");

        return view;
    }
}
