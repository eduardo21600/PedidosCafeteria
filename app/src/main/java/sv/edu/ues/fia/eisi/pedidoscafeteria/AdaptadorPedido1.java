package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPedido1 extends RecyclerView.Adapter<AdaptadorPedido1.ViewHolder> implements View.OnClickListener{

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView IDpedido;
        private TextView precio;
        private CardView cdP;
        private Button chat;


        public ViewHolder(View itemView)
        {
            super(itemView);
            context=itemView.getContext();
            IDpedido=(TextView)itemView.findViewById(R.id.txtIDpedido);
            precio=(TextView)itemView.findViewById(R.id.txtprecio);
            cdP=(CardView)itemView.findViewById(R.id.idCardViewPedidoR);
            chat=(Button)itemView.findViewById (R.id.chat);


        }
        void setOnClickListener()
        {
           cdP.setOnClickListener(this);
           chat.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.idCardViewPedidoR:
                    Intent intentn= new Intent(context, DetallePedidoRep.class);
                    intentn.putExtra("id",IDpedido.getText());
                    context.startActivity(intentn);
                    break;
                case R.id.chat:
                    Intent intent= new Intent(context, Chat2.class);
                    intent.putExtra("id",IDpedido.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }

    public List<Pedido> pedidoLista;

    public AdaptadorPedido1(List<Pedido> pedidoLista){
        this.pedidoLista= pedidoLista;
    }

    @Override
    public AdaptadorPedido1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardpedido, parent, false);
        final AdaptadorPedido1.ViewHolder viewHolder =new AdaptadorPedido1.ViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(AdaptadorPedido1.ViewHolder holder, int position){
        holder.IDpedido.setText(String.valueOf(pedidoLista.get(position).getIdPedido()));
        holder.precio.setText(String.valueOf(pedidoLista.get(position).getTotalPedido()));

        holder.setOnClickListener();
    }

    @Override
    public int getItemCount(){
        return pedidoLista.size();
    }

}


