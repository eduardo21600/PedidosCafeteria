package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorDescripcionMenu extends RecyclerView.Adapter<AdaptadorDescripcionMenu.viewHolder>
{
    private Context mContext;
    private List<Producto> mDesc;

    public AdaptadorDescripcionMenu(Context mContext, List<Producto> mDesc) {
        this.mContext = mContext;
        this.mDesc = mDesc;
    }

    @NonNull
    @Override
    public AdaptadorDescripcionMenu.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_producto_desc_menu, parent, false);
        final AdaptadorDescripcionMenu.viewHolder vHolder = new AdaptadorDescripcionMenu.viewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDescripcionMenu.viewHolder holder, int position)
    {
        holder.tv_nombre.setText(mDesc.get(position).getNombreProducto());
        holder.idProducto = mDesc.get(position).getIdProduto();
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+holder.idProducto+"_producto.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.iv_imagen);
    }

    @Override
    public int getItemCount() {
        return mDesc.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout ly_desc;
        private TextView tv_nombre;
        private ImageView iv_imagen;
        private int idProducto;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ly_desc = (LinearLayout) itemView.findViewById(R.id.producto_desc_card);
            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_producto_c);
            iv_imagen = (ImageView) itemView.findViewById(R.id.imagen_producto_c);
        }
    }
}
