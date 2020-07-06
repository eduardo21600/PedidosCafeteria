package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.pedidosCliente;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorDetallePedidoC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorLocal;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.DetallePedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoRealizado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion.agregarDireccion;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentPedidosCliente extends Fragment {

    private List<DetallePedido> listDP;
    private View v;
    private RecyclerView recyclerView;
    private ControladorBD controladorBD;
    SharedPreferences sharedPreferences;
    String usu;
    AdaptadorDetallePedidoC adaptadorDetallePedidoC;
    SoundPool sp;
    int exito, error;

    public fragmentPedidosCliente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_pedidos_cliente, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.pedidos_recycler_c);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        exito = sp.load(getContext(),R.raw.audio_eliminar,1);
        if(listDP.isEmpty())
        {
            FancyToast.makeText(getContext(), "No hay pedidos realizados", FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.error, false).show();
        }
        else
        {
            adaptadorDetallePedidoC = new AdaptadorDetallePedidoC(getContext(), listDP);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adaptadorDetallePedidoC);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorBD = new ControladorBD(getContext());
        controladorBD.abrir();
        listDP = controladorBD.ConsultaDetallePedidoRealizado(usu);
        controladorBD.cerrar();
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final DetallePedido dp = listDP.get(position);
            controladorBD.abrir();
            final String nomMenu = controladorBD.ConsultaMenu(String.valueOf(listDP.get(position).getIdMenu())).getNomMenu();
            final List<Pedido> pedidoABorrar = controladorBD.consultarPedidoDetalle(listDP.get(position).getIdDetallePedido());
            controladorBD.cerrar();
            MaterialDialog mDialog = new MaterialDialog.Builder((Activity) getActivity())
                    .setTitle(getContext().getResources().getString(R.string.cancelar_pedido))
                    .setAnimation(R.raw.delete)
                    .setMessage(getContext().getResources().getString(R.string.esta_seguro)+nomMenu+"'?")
                    .setCancelable(false)
                    .setPositiveButton(getContext().getResources().getString(R.string.borrar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            // Delete Operation
                            int id = position;
                            listDP.remove(position);
                            adaptadorDetallePedidoC.notifyItemRemoved(position);
                            dialogInterface.dismiss();
                            PedidoRealizado pedidoRealizado = new PedidoRealizado(pedidoABorrar.get(0).getIdPedido(), usu, "");
                            controladorBD.abrir();
                            String resultado = controladorBD.eliminar(pedidoRealizado);
                            if(resultado.equals("Se elimino pedidoRealizado"))
                            {
                                resultado = controladorBD.eliminar(pedidoABorrar.get(0));
                                FancyToast.makeText(getContext(), getContext().getResources().getString(R.string.cancelado), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
                                sp.play(exito,1,1,1,0,0);
                            }
                            controladorBD.cerrar();
                        }
                    })
                    .setNegativeButton(getContext().getResources().getString(R.string.cancelar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            adaptadorDetallePedidoC = new AdaptadorDetallePedidoC(getContext(), listDP);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adaptadorDetallePedidoC);
                            dialogInterface.dismiss();
                        }
                    })
                    .build();
            mDialog.show();
        }
    };
}
