package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente.MenusLocal;

public class AdaptadorMenuC extends RecyclerView.Adapter<AdaptadorMenuC.viewHolder>
{
    private Context mContext;
    private List<Menu> mMenus;

    public AdaptadorMenuC(Context mContext, List<Menu> mMenus) {
        this.mContext = mContext;
        this.mMenus = mMenus;
    }

    @NonNull
    @Override
    public AdaptadorMenuC.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_menu_c, parent, false);
        final AdaptadorMenuC.viewHolder vHolder = new AdaptadorMenuC.viewHolder(v);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMenuC.viewHolder holder, int position)
    {
        holder.tv_nombre.setText(mMenus.get(position).getNomMenu());
        holder.tv_desc.setText(mMenus.get(position).getFechaDesdeMenu());
        holder.tv_precio.setText(String.valueOf(mMenus.get(position).getPrecioMenu()));
        holder.iv_imagen.setImageResource(R.drawable.food);
    }

    @Override
    public int getItemCount() {
        return mMenus.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout ly_local;
        private TextView tv_nombre;
        private TextView tv_desc;
        private TextView tv_precio;
        private ImageView iv_imagen;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ly_local = (LinearLayout) itemView.findViewById(R.id.menu_card);
            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_menu_c);
            tv_desc = (TextView) itemView.findViewById(R.id.descripcion_menu_c);
            tv_precio = (TextView) itemView.findViewById(R.id.precio_menu_c);
            iv_imagen = (ImageView) itemView.findViewById(R.id.imagen_menu_c);

        }
    }
}
