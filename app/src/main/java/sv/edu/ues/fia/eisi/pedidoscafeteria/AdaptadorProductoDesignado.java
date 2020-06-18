package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorProductoDesignado extends RecyclerView.Adapter<AdaptadorProductoDesignado.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView idProducto;
        private TextView nombre;
        private TextView precio;
        private TextView descripcion;
        private CardView cdvConsultar;

        public ViewHolder(View itemView)
        {
            super(itemView);
            context=itemView.getContext();
            nombre = (TextView) itemView.findViewById(R.id.textNombreDelProductoENMenu);
            idProducto = (TextView) itemView.findViewById(R.id.textIdDelProductoEnMenu);
            precio = (TextView) itemView.findViewById(R.id.textPrecioDelProductoEnElMenu);
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcionDelProductoMenu);
            cdvConsultar=(CardView) itemView.findViewById(R.id.CardProducto);
        }
        void setOnClickListener()
        {
            cdvConsultar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {

                case R.id.CardProducto:
                    Intent intents= new Intent(context, ProductoAsignadoFinal.class);
                    intents.putExtra("productoid",idProducto.getText());
                    context.startActivity(intents);
                    break;
            }
        }
    }

    public List<Producto> productoLista;

    public AdaptadorProductoDesignado (List<Producto> productoLista){
        this.productoLista = productoLista;
    }

    @Override
    public AdaptadorProductoDesignado.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_producto_menu, parent, false);
        final AdaptadorProductoDesignado.ViewHolder viewHolder =new  AdaptadorProductoDesignado.ViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(AdaptadorProductoDesignado.ViewHolder holder, int position){
        holder.nombre.setText(productoLista.get(position).getNombreProducto());
        holder.idProducto.setText(String.valueOf(productoLista.get(position).getIdProduto()));
        holder.precio.setText(String.valueOf(productoLista.get(position).getPrecioUnitario()));
        holder.descripcion.setText(productoLista.get(position).getDescProducto());
        holder.setOnClickListener();
    }


    @Override
    public int getItemCount(){
        return productoLista.size();
    }
}
