package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.Producto;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorDetallePedidoC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorProducto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorProductoDesignado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.DetallePedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoRealizado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ProductoEliminar;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ProductoIngresar;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class Producto_Lista extends Fragment  {

    private List<Producto> listaProducto;
    private RecyclerView recyclerView;
    private ControladorBD controladorBD;
    AdaptadorProducto adaptadorProducto;
    private View v;
    SoundPool sp;
    int exito, error;

    public Producto_Lista()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_producto__lista, container, false);
        listaProducto = new ArrayList<Producto>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_producto);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        exito = sp.load(getContext(),R.raw.audio_eliminar,1);
        controladorBD = new ControladorBD(getContext());
        controladorBD.abrir();
        listaProducto = controladorBD.ConsultaProductos();
        controladorBD.cerrar();
        adaptadorProducto = new AdaptadorProducto (listaProducto);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorProducto);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        Button crear = (Button)v.findViewById(R.id.butonAgregarProducto);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductoIngresar.class);
                startActivity(intent);
            }
        });

        return v;
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final Producto dp = listaProducto.get(position);
            MaterialDialog mDialog = new MaterialDialog.Builder((Activity) getActivity())
                    .setTitle(getContext().getResources().getString(R.string.cancelar_producto))
                    .setAnimation(R.raw.delete)
                    .setMessage(getContext().getResources().getString(R.string.esta_seguro)+dp.getNombreProducto() +"'?")
                    .setCancelable(false)
                    .setPositiveButton(getContext().getResources().getString(R.string.borrar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            // Delete Operation
                            int id = position;
                            listaProducto.remove(position);
                            adaptadorProducto.notifyItemRemoved(position);
                            dialogInterface.dismiss();
                            controladorBD.abrir();
                            String resultado = controladorBD.eliminarProducto(dp);
                            controladorBD.cerrar();
                            {
                                FancyToast.makeText(getContext(), getContext().getResources().getString(R.string.cancelado), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
                                sp.play(exito,1,1,1,0,0);
                            }

                        }
                    })
                    .setNegativeButton(getContext().getResources().getString(R.string.cancelar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            adaptadorProducto = new AdaptadorProducto(listaProducto);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adaptadorProducto);
                            dialogInterface.dismiss();
                        }
                    })
                    .build();
            mDialog.show();
        }
    };
}
