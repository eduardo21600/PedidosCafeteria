package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorLocal extends RecyclerView.Adapter<AdaptadorLocal.viewHolder>
{
    private Context mContext;
    private List<Local> mLocal;

    public AdaptadorLocal(Context mContext, List<Local> mLocal) {
        this.mContext = mContext;
        this.mLocal = mLocal;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_local_c, parent, false);
        viewHolder vHolder = new viewHolder(v);



        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position)
    {
        holder.tv_nombre.setText(mLocal.get(position).getNombreLocal());
        holder.iv_imagen.setImageResource(mLocal.get(position).getIdUsuario());
    }

    @Override
    public int getItemCount() {
        return mLocal.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder
    {

        private TextView tv_nombre;
        private ImageView iv_imagen;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_local_c);
            iv_imagen = (ImageView) itemView.findViewById(R.id.imagen_local_c);

        }
    }
}
