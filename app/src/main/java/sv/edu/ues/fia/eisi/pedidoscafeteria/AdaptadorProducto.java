package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private LinearLayout linearProducto;
        private TextView nombre;
        private TextView id;
        private int idProducto;
        private TextView productoprecio;
        private TextView descripcion;
        private Button btnModificar;
        private Button btnEliminar;
        private Context vista;
        private ImageView imgProducto;
        private GestureDetector gestorDetector;


        public ViewHolder(View itemView)
        {
            super(itemView);
            context=itemView.getContext();
            nombre=(TextView)itemView.findViewById(R.id.textNombre);
            id=(TextView)itemView.findViewById(R.id.textIdProducto);
            productoprecio=(TextView)itemView.findViewById(R.id.productoprecio);
            descripcion=(TextView)itemView.findViewById(R.id.textDescripcionProducto);
            btnEliminar=(Button)itemView.findViewById(R.id.btnEliminarProducto);
            btnModificar=(Button)itemView.findViewById(R.id.btnModificarProducto);
            imgProducto=(ImageView)itemView.findViewById(R.id.imgProducto);
            linearProducto=(LinearLayout)itemView.findViewById(R.id.listaProducto);

        }
        void setOnClickListener()
        {
           // btnEliminar.setOnClickListener(this);
            btnModificar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnEliminarProducto:
                    Intent intentn= new Intent(context, ProductoEliminar.class);
                    intentn.putExtra("idy",id.getText());
                    intentn.putExtra("precio",productoprecio.getText());
                    intentn.putExtra("nombre",nombre.getText());
                    intentn.putExtra("desc",descripcion.getText());
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
    public Context contextM;
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
        holder.idProducto=productoLista.get(position).getIdProduto();
        holder.productoprecio.setText(String.valueOf(productoLista.get(position).getPrecioUnitario()));
        holder.descripcion.setText(productoLista.get(position).getNombreProducto());
        holder.vista=contextM;
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+holder.idProducto+"_producto.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.imgProducto);
        holder.setOnClickListener();

    }

    @Override
    public int getItemCount(){
        return productoLista.size();
    }
}
