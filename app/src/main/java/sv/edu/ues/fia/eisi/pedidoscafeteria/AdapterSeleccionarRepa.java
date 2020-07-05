package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSeleccionarRepa extends RecyclerView.Adapter<AdapterSeleccionarRepa.SeleccRepartidorViewHolder> {

    Context mContext;
    List<Usuario> datos;
    private Activity mActivity;

    public AdapterSeleccionarRepa(Context mContext, List<Usuario> datos, Activity mActivity) {
        this.mContext = mContext;
        this.datos = datos;
        this.mActivity = mActivity;
    }


    @NonNull
    @Override
    public AdapterSeleccionarRepa.SeleccRepartidorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.cardview_repa_e, parent, false);
        AdapterSeleccionarRepa.SeleccRepartidorViewHolder vHolder = new AdapterSeleccionarRepa.SeleccRepartidorViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSeleccionarRepa.SeleccRepartidorViewHolder holder, final int position) {
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+datos.get(position).getIdUsuario()+"_usuario.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.job)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.imageView);
        holder.tvIdRepa.setText(String.valueOf(datos.get(position).getIdUsuario()));
        holder.tvNombre.setText(datos.get(position).getNombreUsuario());
        holder.tvTelefono.setText(datos.get(position).getTeleUsuario());
        holder.btnELi.setVisibility(View.INVISIBLE);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog mDialog = new MaterialDialog.Builder(mActivity)
                        .setTitle("Asignar Repartidor")
                        .setMessage("¿Esta seguro que quiere asignar este repartidor para la entrega de este pedido? Esta acción no puede cambiarse")
                        .setCancelable(false)
                        .setAnimation(R.raw.alert)
                        .setPositiveButton("Asignar", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                // Delete Operation
                                Intent result = new Intent();
                                result.putExtra("idRepa",datos.get(position).getIdUsuario());
                                mActivity.setResult(Activity.RESULT_OK, result);
                                mActivity.finish();
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

                // Show Dialog
                mDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class SeleccRepartidorViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;
        private ImageView imageView,btnELi;
        private TextView tvIdRepa, tvNombre, tvTelefono;

        public SeleccRepartidorViewHolder(@NonNull View itemView) {
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
