package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        PedidosViewHolder vHolder = new PedidosViewHolder(v);
        return  vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPedidos.PedidosViewHolder holder, int position) {
        holder.tvIdPedido.setText(String.valueOf(datos.get(position).getIdPedido()));
        holder.tvCliente.setText(datos.get(position).getNomCliente());
        holder.tvTipo.setText(datos.get(position).getTipo());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class PedidosViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIdPedido, tvCliente, tvTipo;
        public PedidosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdPedido = (TextView)itemView.findViewById(R.id.tvIdPedido);
            tvCliente = (TextView)itemView.findViewById(R.id.tvCliente);
            tvTipo = (TextView)itemView.findViewById(R.id.tvTipo);
        }
    }
}
