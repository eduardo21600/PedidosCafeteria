package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView nombre;
        private TextView id;
        private TextView productoprecio;
        private Button btnModificar;
        private Button btnEliminar;

        public ViewHolder(View itemView)
        {
            super(itemView);
            context=itemView.getContext();
            nombre=(TextView)itemView.findViewById(R.id.textNombre);
            id=(TextView)itemView.findViewById(R.id.textIdProducto);
            productoprecio=(TextView)itemView.findViewById(R.id.productoprecio);
            btnEliminar=(Button)itemView.findViewById(R.id.btnEliminarProducto);
            btnModificar=(Button)itemView.findViewById(R.id.btnModificarProducto);
        }
        void setOnClickListener()
        {
            btnEliminar.setOnClickListener(this);
            btnModificar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnEliminarProducto:
                    Intent intentn= new Intent(context, ProductoEliminar.class);
                    intentn.putExtra("idy",id.getText());
                    context.startActivity(intentn);
                    break;

                case R.id.btnModificarProducto:
                    Intent intent= new Intent(context, ProductoModificar.class);
                    intent.putExtra("id",id.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }

    public List<Producto> productoLista;

    public AdaptadorProducto(List<Producto> productoLista){
        this.productoLista= productoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_producto, parent, false);
        final ViewHolder viewHolder =new ViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.nombre.setText(productoLista.get(position).getNombreProducto());
        holder.id.setText(String.valueOf(productoLista.get(position).getIdProduto()));
        holder.productoprecio.setText(String.valueOf(productoLista.get(position).getPrecioUnitario()));
        holder.setOnClickListener();
    }


    @Override
    public int getItemCount(){
        return productoLista.size();
    }
}
