package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Image;
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

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorDetallePedidoC extends RecyclerView.Adapter<AdaptadorDetallePedidoC.viewHolder>
{
    private Context mContext;
    private List<DetallePedido> mDet;
    private ControladorBD controladorBD;
    SharedPreferences sharedPreferences;
    String usu;
    SoundPool sp;
    int exito;

    public AdaptadorDetallePedidoC(Context mContext, List<DetallePedido> mDet) {
        this.mContext = mContext;
        this.mDet = mDet;
        controladorBD = new ControladorBD(mContext);
        sharedPreferences = mContext.getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        exito = sp.load(mContext,R.raw.audio_eliminar,1);
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
    public void onBindViewHolder(@NonNull AdaptadorDetallePedidoC.viewHolder holder, final int position)
    {
        holder.tv_cantidad.setText(String.valueOf(mContext.getResources().getString(R.string.cantidad)+ mDet.get(position).getCantidad()));
        holder.tv_subtotal.setText(String.valueOf(mContext.getResources().getString(R.string.total_1) + mDet.get(position).getSubtotal()));
        holder.idMenu = mDet.get(position).getIdMenu();
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+holder.idMenu+"_menu.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.iv_imagen);
        controladorBD.abrir();
        final String nomMenu = controladorBD.ConsultaMenu(String.valueOf(mDet.get(position).getIdMenu())).getNomMenu();
        holder.tv_idMenu.setText(nomMenu);
        controladorBD.cerrar();
        holder.eleminarPedido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                controladorBD.abrir();
                    final List<Pedido> pedidoABorrar = controladorBD.consultarPedidoDetalle(mDet.get(position).getIdDetallePedido());
                controladorBD.cerrar();
                MaterialDialog mDialog = new MaterialDialog.Builder((Activity) mContext)
                        .setTitle(mContext.getResources().getString(R.string.cancelar_pedido))
                        .setAnimation(R.raw.delete)
                        .setMessage(mContext.getResources().getString(R.string.esta_seguro)+nomMenu+"'?")
                        .setCancelable(false)
                        .setPositiveButton(mContext.getResources().getString(R.string.borrar), new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delete Operation
                                int id = position;
                                mDet.remove(position);
                                notifyItemRemoved(position);
                                dialogInterface.dismiss();
                                PedidoRealizado pedidoRealizado = new PedidoRealizado(pedidoABorrar.get(0).getIdPedido(), usu, "");
                                controladorBD.abrir();
                                String resultado = controladorBD.eliminar(pedidoRealizado);
                                if(resultado.equals("Se elimino pedidoRealizado"))
                                {
                                    resultado = controladorBD.eliminar(pedidoABorrar.get(0));
                                    FancyToast.makeText(mContext, mContext.getResources().getString(R.string.cancelado), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
                                    sp.play(exito,1,1,1,0,0);
                                }
                                controladorBD.cerrar();
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

        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controladorBD.abrir();
                Pedido pedido = controladorBD.consultarPedidoDetalle(mDet.get(position).getIdDetallePedido()).get(0);
                controladorBD.cerrar();
                Intent intent = new Intent(mContext, Chat2.class);
                intent.putExtra("id", String.valueOf (pedido.getIdPedido()));
                //Toast.makeText(mContext, "IDPEDIDO: "+ pedido.getIdPedido(), Toast.LENGTH_SHORT).show();
                mContext.startActivity(intent);
            }
        });
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
        private ImageButton chat;
        private int idMenu;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ly_local = (LinearLayout) itemView.findViewById(R.id.pedido_card);
            tv_idMenu = (TextView) itemView.findViewById(R.id.nombre_pedido_c);
            tv_cantidad = (TextView) itemView.findViewById(R.id.cantidad_pedido_c);
            tv_subtotal = (TextView) itemView.findViewById(R.id.subtotal_pedido_c);
            iv_imagen = (ImageView) itemView.findViewById(R.id.imagen_pedido_c);
            eleminarPedido = (ImageButton) itemView.findViewById(R.id.eliminar_pedido_c);
            chat = (ImageButton) itemView.findViewById(R.id.chat_pedido_c);
        }
    }

}
