package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;

public class AdaptadorUbicacionSeleccionar extends RecyclerView.Adapter<AdaptadorUbicacionSeleccionar.viewHolder>
{
    private Context mContext;
    private Activity mActivity;
    private List<Ubicacion> mUbicacion;

    public AdaptadorUbicacionSeleccionar(Context mContext, List<Ubicacion> mUbicacion, Activity mActivity) {
        this.mContext = mContext;
        this.mUbicacion = mUbicacion;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public AdaptadorUbicacionSeleccionar.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_ubicacion_c, parent, false);
        final AdaptadorUbicacionSeleccionar.viewHolder vHolder = new AdaptadorUbicacionSeleccionar.viewHolder(v);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUbicacionSeleccionar.viewHolder holder, final int position)
    {
        holder.tv_nombre.setText(mUbicacion.get(position).getNomUbicacion());
        holder.tv_dir.setText(mUbicacion.get(position).getDirecUbicacion());
        holder.tv_punto.setText(String.valueOf(mUbicacion.get(position).getPuntoRefUbicacion()));
        holder.idUbicacion = mUbicacion.get(position).getIdUbicacion();
        holder.elminarUbicacion.setVisibility(View.INVISIBLE);
        holder.ly_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("idUbicacion", mUbicacion.get(position).getIdUbicacion());
                result.putExtra("direccion", mUbicacion.get(position).getDirecUbicacion());
                mActivity.setResult(Activity.RESULT_OK, result);
                mActivity.finish();
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
