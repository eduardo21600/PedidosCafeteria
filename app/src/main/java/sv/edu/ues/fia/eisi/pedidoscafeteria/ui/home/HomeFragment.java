package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.shashank.sony.fancytoastlib.FancyToast;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class HomeFragment extends Fragment {

    Button btnLlenar;
    ControladorBD controladorBD;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnLlenar = (Button)root.findViewById(R.id.btnDatosPrueba);
        controladorBD = new ControladorBD(getContext());
        btnLlenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controladorBD.abrir();
                controladorBD.llenarUsuario();
                String res = controladorBD.llenarUsuario();
                FancyToast.makeText(getContext(),res,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                controladorBD.cerrar();
            }
        });


        return root;
    }
}
