package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRepartidor extends RecyclerView.Adapter<AdapterRepartidor.RepartidorViewHolder>  {

    Context mContext;
    List<Usuario> datos;
    public AdapterRepartidor(Context mContext,List<Usuario> datos){
        this.mContext =mContext;
        this.datos =datos;
    }

    @NonNull
    @Override
    public RepartidorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cardview_pedidos_e,parent,false);
        AdapterRepartidor.RepartidorViewHolder vHolder = new AdapterRepartidor.RepartidorViewHolder(v);
        return  vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepartidorViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.job);
        holder.tvIdRepa.setText(String.valueOf(datos.get(position).getIdUsuario()));
        holder.tvNombre.setText(datos.get(position).getNombreUsuario());
        holder.tvTelefono.setText(datos.get(position).getTeleUsuario());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class RepartidorViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvIdRepa, tvNombre, tvTelefono;
        public RepartidorViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =(ImageView)itemView.findViewById(R.id.imageView4);
            tvIdRepa = (TextView)itemView.findViewById(R.id.tvIdPedido);
            tvNombre = (TextView)itemView.findViewById(R.id.tvCliente);
            tvTelefono = (TextView)itemView.findViewById(R.id.tvTipo);
        }
    }
}
