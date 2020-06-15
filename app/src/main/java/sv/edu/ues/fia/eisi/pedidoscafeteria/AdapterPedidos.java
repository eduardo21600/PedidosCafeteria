package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.PedidosViewHolder> {


    Context mContext;
    List<PedidoModelo> datos;

    public AdapterPedidos(Context mContext, List<PedidoModelo> datos) {
        this.mContext = mContext;
        this.datos = datos;
    }

    @NonNull
    @Override
    public AdapterPedidos.PedidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cardview_pedidos_e,parent,false);
        final PedidosViewHolder vHolder = new PedidosViewHolder(v);

        vHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,detallePedidoEnc.class);
                mContext.startActivity(intent);
            }
        });

        return  vHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterPedidos.PedidosViewHolder holder, int position) {
        holder.tvIdPedido.setText("Id: "+String.valueOf(datos.get(position).getIdPedido()));
        holder.tvCliente.setText("Cliente: "+datos.get(position).getNomCliente());
        holder.tvTipo.setText("Entrega: "+datos.get(position).getTipo());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class PedidosViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;
        private TextView tvIdPedido, tvCliente, tvTipo;
        public PedidosViewHolder(@NonNull View itemView) {
            super(itemView);
            card = (LinearLayout)itemView.findViewById(R.id.cardview_pedidos_e_id);
            tvIdPedido = (TextView)itemView.findViewById(R.id.tvIdPedido);
            tvCliente = (TextView)itemView.findViewById(R.id.tvCliente);
            tvTipo = (TextView)itemView.findViewById(R.id.tvTipo);
        }
    }
}
