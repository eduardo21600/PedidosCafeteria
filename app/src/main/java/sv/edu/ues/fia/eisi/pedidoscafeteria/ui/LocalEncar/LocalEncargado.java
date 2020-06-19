package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.LocalEncar;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;


public class LocalEncargado extends Fragment {

    public LocalEncargado() {
        // Required empty public constructor
    }
    
    Button habilitar,actualizar;
    EditText etNomLocal;
    View root;
    ControladorBD controladorBD;
    List<Local> local;
    Local nLo;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_local_encargado, container, false);
        controladorBD = new ControladorBD(getContext());
        sharedPreferences = this.getActivity().getSharedPreferences("validacion",0);
        String idUsuario = sharedPreferences.getString("nombreUsuario","");
        habilitar = (Button)root.findViewById(R.id.btnActivar);
        actualizar = (Button)root.findViewById(R.id.btnGuardarNom);
        etNomLocal = (EditText)root.findViewById(R.id.editText);

        controladorBD.abrir();
        local = controladorBD.ConsultaLocales();
        for (int i = 0; i <local.size() ; i++) {
            if(local.get(i).getIdUsuario().equals(idUsuario)){
                etNomLocal.setText(local.get(i).getNombreLocal());
                nLo = local.get(i);
                break;
            }
        }
        habilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNomLocal.setEnabled(true);
                actualizar.setVisibility(View.VISIBLE);
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controladorBD.abrir();
                if(!(etNomLocal.getText().toString().isEmpty())){
                    nLo.setNombreLocal(etNomLocal.getText().toString());
                    controladorBD.ActualizarLocal(nLo);
                    FancyToast.makeText(getContext(),getResources().getString(R.string.localActualizado),
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }
                else{
                    FancyToast.makeText(getContext(),getResources().getString(R.string.camposVacios),
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }
            }
        });
        return root;
    }
}
