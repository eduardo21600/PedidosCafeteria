package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;

public class AdapterRepartidor extends RecyclerView.Adapter<AdapterRepartidor.RepartidorViewHolder>  {

    Context mContext;
    List<Usuario> datos;
    ControladorBD controladorBD;

    public AdapterRepartidor(Context mContext,List<Usuario> datos){
        this.mContext =mContext;
        this.datos =datos;
    }

    @NonNull
    @Override
    public RepartidorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cardview_repa_e,parent,false);
        AdapterRepartidor.RepartidorViewHolder vHolder = new AdapterRepartidor.RepartidorViewHolder(v);
        controladorBD = new ControladorBD(mContext);
        vHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return  vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepartidorViewHolder holder, final int position) {
        holder.imageView.setImageResource(R.drawable.job);
        holder.tvIdRepa.setText(String.valueOf(datos.get(position).getIdUsuario()));
        holder.tvNombre.setText(datos.get(position).getNombreUsuario());
        holder.tvTelefono.setText(datos.get(position).getTeleUsuario());

        holder.btnELi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog mDialog = new MaterialDialog.Builder((Activity) mContext)
                        .setTitle("Eliminar")
                        .setAnimation(R.raw.delete)
                        .setMessage("¿Está seguro que quiere eliminar este repartidor?")
                        .setCancelable(false)
                        .setPositiveButton("Borrar", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delete Operation
                                controladorBD.abrir();
                                controladorBD.eliminarUsuario(datos.get(position));
                                datos.remove(position);
                                notifyItemRemoved(position);
                                controladorBD.cerrar();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Cancelar", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                mDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class RepartidorViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout card;
        private ImageView imageView,btnELi;
        private TextView tvIdRepa, tvNombre, tvTelefono;
        public RepartidorViewHolder(@NonNull View itemView) {
            super(itemView);
            card = (LinearLayout)itemView.findViewById(R.id.card_repa);
            imageView =(ImageView)itemView.findViewById(R.id.imageView5);
            btnELi = (ImageView)itemView.findViewById(R.id.btnEli);
            tvIdRepa = (TextView)itemView.findViewById(R.id.tvIdRepa);
            tvNombre = (TextView)itemView.findViewById(R.id.tvNomRepa);
            tvTelefono = (TextView)itemView.findViewById(R.id.tvNumRepa);
        }
    }
}
