package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
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

import java.util.List;

public class AdaptadorUbicacion extends RecyclerView.Adapter<AdaptadorUbicacion.viewHolder>
{
    private Context mContext;
    private List<Ubicacion> mUbicacion;

    public AdaptadorUbicacion(Context mContext, List<Ubicacion> mUbicacion) {
        this.mContext = mContext;
        this.mUbicacion = mUbicacion;
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
    public void onBindViewHolder(@NonNull AdaptadorUbicacion.viewHolder holder, int position)
    {
        holder.tv_nombre.setText(mUbicacion.get(position).getNomUbicacion());
        holder.tv_dir.setText(mUbicacion.get(position).getDirecUbicacion());
        holder.tv_punto.setText(String.valueOf(mUbicacion.get(position).getPuntoRefUbicacion()));
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