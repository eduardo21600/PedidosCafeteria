package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorProductoDesignado extends RecyclerView.Adapter<AdaptadorProductoDesignado.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProductoMenuAsignar prueba;
        private ImageView imgProducto;
        private TextView idProducto;
        private TextView descripcion;
        private CardView cdvConsultar;
        private TextView nombre;
        private TextView precio;
        private int idProducto1;
        private Context context;
        private int idMenu2;

        private Context context1;

        public ViewHolder(View itemView)
        {
            super(itemView);
            context=itemView.getContext();
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcionDelProductoMenu);
            nombre = (TextView) itemView.findViewById(R.id.textNombreDelProductoENMenu);
            idProducto = (TextView) itemView.findViewById(R.id.textIdDelProductoEnMenu);
            precio = (TextView) itemView.findViewById(R.id.textPrecioDelProductoEnElMenu);
            cdvConsultar=(CardView) itemView.findViewById(R.id.CardProducto);
            imgProducto=(ImageView)itemView.findViewById(R.id.imgProducto);
            prueba=new ProductoMenuAsignar();
        }

        void setOnClickListener()
        {
            cdvConsultar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

                    String registro;
                    ProductoAsignar productoAsignar=new ProductoAsignar();
                    productoAsignar.setIdmenu(idMenu2);
                    productoAsignar.setIdProducto(Integer.valueOf(idProducto.getText().toString()));
                    ControladorBD base;
                    base = new ControladorBD(context1);
                    base.abrir();
                    registro=base.AsignarProtoMenu(productoAsignar);
                    base.cerrar();
                    Toast.makeText(context1, registro, Toast.LENGTH_SHORT).show();
        }

    }

    public List<Producto> productoLista;
    public int idMenu;
    public Context contextM;
    public AdaptadorProductoDesignado (List<Producto> productoLista, int idmenu, Context contextM){
        this.productoLista = productoLista;
        this.idMenu=idmenu;
        this.contextM=contextM;
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
        holder.idProducto1=productoLista.get(position).getIdProduto();
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+holder.idProducto1+"_producto.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.imgProducto);
        holder.idMenu2=idMenu;
        holder.context1=contextM;
        holder.setOnClickListener();
    }


    @Override
    public int getItemCount(){
        return productoLista.size();
    }
}
