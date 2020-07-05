package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.provider.ContactsContract;
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

public class AdapterRepartidor extends RecyclerView.Adapter<AdapterRepartidor.RepartidorViewHolder>  {

    Context mContext;
    List<Usuario> datos;
    private Activity mActivity;
    ControladorBD controladorBD;
    SoundPool sp;
    int sonido;

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
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido = sp.load(mContext,R.raw.audio_eliminar,1);
        return  vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepartidorViewHolder holder, final int position) {
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

        holder.btnELi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datos.get(position).getEstado().equals("2")){
                    FancyToast.makeText(mContext, mContext.getResources().getString(R.string.noSePuedeEliminarRepa),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }
                else{
                    sp.play(sonido,1,1,1,0,0);
                    final MaterialDialog mDialog = new MaterialDialog.Builder((Activity) mContext)
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
            }
        });

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ActualizarRepartidor.class);
                intent.putExtra("idRepa",datos.get(position).getIdUsuario());
                intent.putExtra("contraseña",datos.get(position).getContrasena());
                intent.putExtra("nombreRepa",datos.get(position).getNombreUsuario());
                intent.putExtra("telefono",datos.get(position).getTeleUsuario());
                intent.putExtra("apellidoRepa",datos.get(position).getApellidoUsuario());
                intent.putExtra("estado",datos.get(position).getEstado());
                mContext.startActivity(intent);
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
