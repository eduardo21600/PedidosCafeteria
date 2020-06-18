package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorAsignarProductoMenu extends RecyclerView.Adapter<AdaptadorAsignarProductoMenu.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private Context context;
        private TextView nombre;
        private TextView id;
        private TextView descripcion;
        private TextView precio;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombre = (TextView) itemView.findViewById(R.id.textNombreDelProductoENMenu);
            id = (TextView) itemView.findViewById(R.id.textIdDelProductoEnMenu);
            precio = (TextView) itemView.findViewById(R.id.textPrecioDelProductoEnElMenu);
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcionDelProductoMenu);

        }

    }

    public List<Producto> productoLista;

    public AdaptadorAsignarProductoMenu(List<Producto> productoLista) {
        this.productoLista = productoLista;
    }

    @Override
    public AdaptadorAsignarProductoMenu.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_producto_menu, parent, false);
        final AdaptadorAsignarProductoMenu.ViewHolder viewHolder = new AdaptadorAsignarProductoMenu.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptadorAsignarProductoMenu.ViewHolder holder, int position) {
        holder.nombre.setText(productoLista.get(position).getNombreProducto());
        holder.id.setText(String.valueOf(productoLista.get(position).getIdProduto()));
        holder.precio.setText(String.valueOf(productoLista.get(position).getPrecioUnitario()));
        holder.descripcion.setText(productoLista.get(position).getDescProducto());
    }


    @Override
    public int getItemCount() {
        return productoLista.size();
    }
}
