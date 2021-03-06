package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
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

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente.DescMenu;
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

            vHolder.agregarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DescMenu.class);
                    intent.putExtra("idMenu", vHolder.idMenu);
                    intent.putExtra("nomMenu", vHolder.nomMenu);
                    intent.putExtra("precioMenu", vHolder.precioMenu);
                    intent.putExtra("idLocal", vHolder.idLocal);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

            vHolder.ly_local.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(mContext, DescMenu.class);
                    intent.putExtra("idMenu", vHolder.idMenu);
                    intent.putExtra("nomMenu", vHolder.nomMenu);
                    intent.putExtra("precioMenu", vHolder.precioMenu);
                    intent.putExtra("idLocal", vHolder.idLocal);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

            return vHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorMenuC.viewHolder holder, int position)
        {
            holder.tv_nombre.setText(mMenus.get(position).getNomMenu());
            holder.tv_desc.setText(mMenus.get(position).getFechaDesdeMenu());
            holder.tv_precio.setText(String.valueOf("$"+mMenus.get(position).getPrecioMenu()));
            holder.idMenu = mMenus.get(position).getIdMenu();
            String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+holder.idMenu+"_menu.jpg";
            Picasso.get().load(imageUri)
                    .placeholder(R.drawable.locales)
                    .error(R.drawable.error)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(holder.iv_imagen);
            holder.nomMenu = mMenus.get(position).getNomMenu();
            holder.precioMenu = mMenus.get(position).getPrecioMenu();
            holder.idLocal = mMenus.get(position).getIdLocal();

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
            private Button agregarPedido;
            int idMenu, idLocal;
            String nomMenu;
            double precioMenu;


            public viewHolder(@NonNull View itemView) {
                super(itemView);
                ly_local = (LinearLayout) itemView.findViewById(R.id.menu_card);
                tv_nombre = (TextView) itemView.findViewById(R.id.nombre_menu_c);
                tv_desc = (TextView) itemView.findViewById(R.id.descripcion_menu_c);
                tv_precio = (TextView) itemView.findViewById(R.id.precio_menu_c);
                iv_imagen = (ImageView) itemView.findViewById(R.id.imagen_menu_c);
                agregarPedido = (Button) itemView.findViewById(R.id.agregar_pedido);
            }
        }
}
