package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuEncargado;

import android.app.Activity;
import android.content.Intent;
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

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuE;
import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorProducto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.InsertarMenu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Menu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Producto;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class MenuFragment extends Fragment {

    private ControladorBD controladorBD;
    private AdaptadorMenuE adaptadorMenu;
    private RecyclerView recyclerView;
    private List<Menu> listaMenu;
    private View card;
    private View v;
    SoundPool sp;
    int exito, error;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_menu, container, false);
        listaMenu = new ArrayList<Menu>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewMenu);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        exito = sp.load(getContext(),R.raw.audio_eliminar,1);
        controladorBD = new ControladorBD(getContext());
        controladorBD.abrir();
        listaMenu = controladorBD.ConsultaMenus();
        controladorBD.cerrar();
        adaptadorMenu = new AdaptadorMenuE (listaMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorMenu);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Button crear = (Button)v.findViewById(R.id.btnCrearMenu);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertarMenu.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return v;   }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final Menu dp = listaMenu.get(position);
            MaterialDialog mDialog = new MaterialDialog.Builder((Activity) getActivity())
                    .setTitle(getContext().getResources().getString(R.string.cancelar_pedido))
                    .setAnimation(R.raw.delete)
                    .setMessage(getContext().getResources().getString(R.string.esta_seguro)+dp.getNomMenu() +"'?")
                    .setCancelable(false)
                    .setPositiveButton(getContext().getResources().getString(R.string.borrar), new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            // Delete Operation
                            int id = position;
                            listaMenu.remove(position);
                            adaptadorMenu.notifyItemRemoved(position);
                            dialogInterface.dismiss();
                            controladorBD.abrir();
                            String resultado = controladorBD.eliminarMenu(dp);
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
                            adaptadorMenu= new AdaptadorMenuE(listaMenu);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(adaptadorMenu);
                            dialogInterface.dismiss();
                        }
                    })
                    .build();
            mDialog.show();
        }
    };
}
