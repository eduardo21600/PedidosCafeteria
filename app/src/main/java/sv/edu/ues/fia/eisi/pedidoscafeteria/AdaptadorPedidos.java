package sv.edu.ues.fia.eisi.pedidoscafeteria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorPedidos extends ArrayAdapter<Pedido> {

    private List<Pedido> mped;
    private Context conp;
    private int resourcep;
    public AdaptadorPedidos(@NonNull Context context, int resource, List<Pedido> objects) {
        super(context, resource, objects);
        this.mped = objects;
        this.conp = context;
        this.resourcep = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null)
        {
            v = LayoutInflater.from(conp).inflate(resourcep,null);
        }

        Pedido pp = mped.get(position);

        TextView cod = v.findViewById(R.id.cod);
        cod.setText(String.valueOf(pp.getIdPedido()));
        TextView precio = v.findViewById(R.id.txp);
        precio.setText(String.valueOf(pp.getTotalPedido()));





        return v;
    }
}

