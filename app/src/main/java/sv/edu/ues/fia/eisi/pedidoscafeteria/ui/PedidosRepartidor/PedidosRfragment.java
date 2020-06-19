package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.PedidosRepartidor;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class PedidosRfragment extends Fragment {

    private PedidosRfragmentViewModel mViewModel;

    public static PedidosRfragment newInstance() {
        return new PedidosRfragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pedidos_rfragment_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PedidosRfragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}
