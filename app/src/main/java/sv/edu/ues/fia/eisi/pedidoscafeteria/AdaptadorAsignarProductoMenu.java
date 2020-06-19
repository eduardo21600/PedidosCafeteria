package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorAsignarProductoMenu extends RecyclerView.Adapter<AdaptadorAsignarProductoMenu.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nombre;
        private TextView id;
        private TextView descripcion;
        private TextView precio;
        private Context context;
        private AsignarProductoMenu prueba;
        private int idMenu2;
        private Context context1;
        private CardView cdvConsultar;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombre = (TextView) itemView.findViewById(R.id.textNombreDelProductoENMenu);
            id = (TextView) itemView.findViewById(R.id.textIdDelProductoEnMenu);
            precio = (TextView) itemView.findViewById(R.id.textPrecioDelProductoEnElMenu);
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcionDelProductoMenu);
            cdvConsultar=(CardView) itemView.findViewById(R.id.CardProducto);
            prueba=new AsignarProductoMenu();

        }
        void setOnClickListener()
        {
            cdvConsultar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            // prueba.integrarProductoAlMenu(idProducto.getText().toString(), idMenu2);
            //Integer IdProducto=Integer.valueOf(idProducto);
            String registro;
            ProductoAsignar productoAsignar=new ProductoAsignar();
            productoAsignar.setIdmenu(idMenu2);
            productoAsignar.setIdProducto(Integer.valueOf(id.getText().toString()));
            ControladorBD base;
            base = new ControladorBD(context1);
            base.abrir();
            registro=base.QuitarProducto(productoAsignar);
            base.cerrar();
            Toast.makeText(context1, registro, Toast.LENGTH_SHORT).show();

        }

    }

    public List<Producto> productoLista;
    public int idMenu;
    public Context contextM;
    public AdaptadorAsignarProductoMenu(List<Producto> productoLista, int idmenu, Context contextM) {
        this.productoLista = productoLista;
        this.idMenu=idmenu;
        this.contextM=contextM;
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


        holder.idMenu2=idMenu;
        holder.context1=contextM;
        holder.setOnClickListener();
    }


    @Override
    public int getItemCount() {
        return productoLista.size();
    }
}
