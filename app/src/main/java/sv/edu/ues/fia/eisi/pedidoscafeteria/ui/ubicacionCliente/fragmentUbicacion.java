package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente;

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

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorUbicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.DetallePedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Pedido;
import sv.edu.ues.fia.eisi.pedidoscafeteria.PedidoRealizado;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Ubicacion;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.nuevaDIreccion.agregarDireccion;

public class fragmentUbicacion extends Fragment implements CallbackWS {

    private List<Ubicacion> listaUbi;
    private View v;
    private RecyclerView recyclerView;
    Button agregar;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;
    SharedPreferences sharedPreferences;
    String usu;
    AdaptadorUbicacion adaptadorUbicacion;
    SoundPool sp;
    int exito;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        controladorServicios = new ControladorServicios(this);
        controladorBD = new ControladorBD(getContext());
        controladorBD.abrir();
        listaUbi = controladorBD.consultarUbicacionUsuario(usu);
        controladorBD.cerrar();
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        exito = sp.load(getContext(),R.raw.audio_eliminar,1);
        //controladorServicios.BuscarUbicacionUsuario(usu, getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ubicacion_c, container, false);
        agregar = (Button) v.findViewById(R.id.agregar_nueva_direccion);
        recyclerView = (RecyclerView) v.findViewById(R.id.ubicacion_recycler);
        adaptadorUbicacion = new AdaptadorUbicacion(getContext(), listaUbi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorUbicacion);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), agregarDireccion.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public void ResponseWS(Object lista)
    {
        listaUbi = (List<Ubicacion>) lista;
        adaptadorUbicacion = new AdaptadorUbicacion(getContext(), listaUbi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorUbicacion);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final Ubicacion ubi = listaUbi.get(position);
            controladorBD.abrir();
            controladorBD.cerrar();
            MaterialDialog mDialog = new MaterialDialog.Builder((Activity) getActivity())
                    .setTitle(getContext().getResources().getString(R.string.eliminar_ubicacion))
                    .setAnimation(R.raw.delete)
                    .setMessage(getContext().getResources().getString(R.string.esta_seguro_ubi)+listaUbi.get(position).getNomUbicacion()+"'?")
                    .setCancelable(false)
                    .setPositiveButton(getContext().getResources().getString(R.string.borrar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            // Delete Operation
                            int id = position;
                            Ubicacion ubicacion = listaUbi.get(position);
                            listaUbi.remove(position);
                            adaptadorUbicacion = new AdaptadorUbicacion(getContext(), listaUbi);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adaptadorUbicacion);
                            dialogInterface.dismiss();
                            ControladorBD controladorBD = new ControladorBD(getContext());
                            controladorBD.abrir();
                            controladorBD.eliminar(ubicacion);
                            controladorBD.cerrar();
                            sp.play(exito,1,1,1,0,0);
                            //ControladorServicios controladorServicios = new ControladorServicios();
                            //controladorServicios.Eliminar(ubicacion, mContext);
                        }
                    })
                    .setNegativeButton(getContext().getResources().getString(R.string.cancelar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            adaptadorUbicacion = new AdaptadorUbicacion(getContext(), listaUbi);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adaptadorUbicacion);
                            dialogInterface.dismiss();
                        }
                    })
                    .build();
            mDialog.show();
        }
    };

}