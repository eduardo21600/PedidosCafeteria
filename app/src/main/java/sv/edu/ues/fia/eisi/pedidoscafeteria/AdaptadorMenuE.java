package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorMenuE extends RecyclerView.Adapter<AdaptadorMenuE.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private TextView nombre;
        private TextView id;
        private int idProducto;
        private TextView precioMenu;
        private Button btnModificar;
        private Button btnEliminar;
        private CardView cdvConsultar;
        private ImageView imageView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            context=itemView.getContext();
            nombre=(TextView)itemView.findViewById(R.id.textNombre);
            id=(TextView)itemView.findViewById(R.id.textIdProducto);
            precioMenu=(TextView)itemView.findViewById(R.id.productoprecio);
            btnEliminar=(Button)itemView.findViewById(R.id.btnEliminarProducto);
            btnModificar=(Button)itemView.findViewById(R.id.btnModificarProducto);
            cdvConsultar=(CardView) itemView.findViewById(R.id.itemMenuProductoEncargado);
            imageView=(ImageView) itemView.findViewById(R.id.imgProducto);
        }
        void setOnClickListener()
        {
            btnEliminar.setOnClickListener(this);
            btnModificar.setOnClickListener(this);
            cdvConsultar.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnEliminarProducto:
                    Intent intentn= new Intent(context, EliminarMenu.class);
                    intentn.putExtra("idy",id.getText());
                    intentn.putExtra("nobre",nombre.getText());
                    intentn.putExtra("prePro",precioMenu.getText());
                    context.startActivity(intentn);
                    break;

                case R.id.btnModificarProducto:
                    Intent intent= new Intent(context, ModificarMenu.class);
                    intent.putExtra("id",id.getText());
                    context.startActivity(intent);
                    break;

                case R.id.itemMenuProductoEncargado:
                    Intent intet= new Intent(context, AsignarProductoMenu.class);
                    intet.putExtra("id",id.getText());
                    context.startActivity(intet);
                    break;
            }
        }
    }

    public List<Menu> menuLista;

    public AdaptadorMenuE(List<Menu> menuLista){
        this.menuLista= menuLista;
    }

    @Override
    public AdaptadorMenuE.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_menu_producto, parent, false);
        final AdaptadorMenuE.ViewHolder viewHolder =new AdaptadorMenuE.ViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(AdaptadorMenuE.ViewHolder holder, int position){
        holder.nombre.setText(menuLista.get(position).getNomMenu());
        holder.id.setText(String.valueOf(menuLista.get(position).getIdMenu()));
        holder.idProducto= menuLista.get(position).getIdMenu();
        holder.precioMenu.setText(String.valueOf(menuLista.get(position).getPrecioMenu()));
        holder.setOnClickListener();
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+holder.idProducto+"_menu.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.locales)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount(){
        return menuLista.size();
    }
}
