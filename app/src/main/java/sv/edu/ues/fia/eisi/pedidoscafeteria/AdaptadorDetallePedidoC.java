package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.media.Image;
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

public class AdaptadorDetallePedidoC extends RecyclerView.Adapter<AdaptadorDetallePedidoC.viewHolder>
{
    private Context mContext;
    private List<DetallePedido> mDet;
    private ControladorBD controladorBD;

    public AdaptadorDetallePedidoC(Context mContext, List<DetallePedido> mDet) {
        this.mContext = mContext;
        this.mDet = mDet;
        controladorBD = new ControladorBD(mContext);
    }

    @NonNull
    @Override
    public AdaptadorDetallePedidoC.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_pedido_c, parent, false);
        final AdaptadorDetallePedidoC.viewHolder vHolder = new AdaptadorDetallePedidoC.viewHolder(v);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDetallePedidoC.viewHolder holder, int position)
    {
        holder.tv_cantidad.setText(String.valueOf(mDet.get(position).getCantidad()));
        holder.tv_subtotal.setText(String.valueOf(mDet.get(position).getSubtotal()));
        holder.iv_imagen.setImageResource(R.drawable.food);
        controladorBD.abrir();
        String nomMenu = controladorBD.ConsultaMenu(String.valueOf(mDet.get(position).getIdMenu())).getNomMenu();
        holder.tv_idMenu.setText(nomMenu);
        controladorBD.cerrar();
    }

    @Override
    public int getItemCount() {
        return mDet.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout ly_local;
        private TextView tv_idMenu;
        private TextView tv_cantidad;
        private TextView tv_subtotal;
        private ImageView iv_imagen;
        private ImageButton eleminarPedido;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ly_local = (LinearLayout) itemView.findViewById(R.id.pedido_card);
            tv_idMenu = (TextView) itemView.findViewById(R.id.nombre_pedido_c);
            tv_cantidad = (TextView) itemView.findViewById(R.id.cantidad_pedido_c);
            tv_subtotal = (TextView) itemView.findViewById(R.id.subtotal_pedido_c);
            iv_imagen = (ImageView) itemView.findViewById(R.id.imagen_pedido_c);
            eleminarPedido = (ImageButton) itemView.findViewById(R.id.eliminar_pedido_c);
        }
    }

}
