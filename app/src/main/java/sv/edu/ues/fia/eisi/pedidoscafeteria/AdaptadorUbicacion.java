package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.ubicacionCliente.ModificarUbicacion;

public class AdaptadorUbicacion extends RecyclerView.Adapter<AdaptadorUbicacion.viewHolder>
{
    private Context mContext;
    private List<Ubicacion> mUbicacion;
    SoundPool sp;
    int sonido,incorrecto;

    public AdaptadorUbicacion(Context mContext, List<Ubicacion> mUbicacion) {
        this.mContext = mContext;
        this.mUbicacion = mUbicacion;
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido = sp.load(mContext,R.raw.audio_eliminar,1);

    }

    @NonNull
    @Override
    public AdaptadorUbicacion.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_ubicacion_c, parent, false);
        final AdaptadorUbicacion.viewHolder vHolder = new AdaptadorUbicacion.viewHolder(v);



        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUbicacion.viewHolder holder, final int position)
    {
        holder.tv_nombre.setText(mUbicacion.get(position).getNomUbicacion());
        holder.tv_dir.setText(mUbicacion.get(position).getDirecUbicacion());
        holder.tv_punto.setText(String.valueOf(mUbicacion.get(position).getPuntoRefUbicacion()));
        holder.idUbicacion = mUbicacion.get(position).getIdUbicacion();
        holder.elminarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog mDialog = new MaterialDialog.Builder((Activity) mContext)
                        .setTitle(mContext.getResources().getString(R.string.eliminar_ubicacion))
                        .setAnimation(R.raw.delete)
                        .setMessage(mContext.getResources().getString(R.string.esta_seguro_ubi)+mUbicacion.get(position).getNomUbicacion()+"'?")
                        .setCancelable(false)
                        .setPositiveButton(mContext.getResources().getString(R.string.borrar), new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delete Operation
                                int id = position;
                                Ubicacion ubicacion = mUbicacion.get(position);
                                mUbicacion.remove(position);
                                notifyItemRemoved(position);
                                dialogInterface.dismiss();
                                ControladorBD controladorBD = new ControladorBD(mContext);
                                controladorBD.abrir();
                                controladorBD.eliminar(ubicacion);
                                controladorBD.cerrar();
                                sp.play(sonido,1,1,1,0,0);
                                //ControladorServicios controladorServicios = new ControladorServicios();
                                //controladorServicios.Eliminar(ubicacion, mContext);
                            }
                        })
                        .setNegativeButton(mContext.getResources().getString(R.string.cancelar), new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                mDialog.show();
            }
        });

        holder.ly_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ModificarUbicacion.class);
                intent.putExtra("direccion", mUbicacion.get(position).getDirecUbicacion());
                intent.putExtra("nombre", mUbicacion.get(position).getNomUbicacion());
                intent.putExtra("punto", mUbicacion.get(position).getPuntoRefUbicacion());
                intent.putExtra("id", mUbicacion.get(position).getIdUbicacion());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUbicacion.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout ly_local;
        private TextView tv_nombre;
        private TextView tv_dir;
        private TextView tv_punto;
        private ImageButton elminarUbicacion;
        int idUbicacion;
        Ubicacion ubicacion;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ly_local = (LinearLayout) itemView.findViewById(R.id.ubicacion_card);
            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_ubicacion_c);
            tv_dir = (TextView) itemView.findViewById(R.id.dir_ubicacion_c);
            tv_punto = (TextView) itemView.findViewById(R.id.punto_ubicacion_c);
            elminarUbicacion = (ImageButton) itemView.findViewById(R.id.eliminar_ubicacion_c);
        }
    }
}
