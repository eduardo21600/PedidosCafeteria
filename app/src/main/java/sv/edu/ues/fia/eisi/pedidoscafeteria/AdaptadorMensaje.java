package sv.edu.ues.fia.eisi.pedidoscafeteria;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorMensaje extends ArrayAdapter<Mensaje> {
    private List<Mensaje> mped;
    private Context conp;
    private int resourcep;

    public AdaptadorMensaje(@NonNull Context context, int resource, List<Mensaje> objects) {
        super(context, resource, objects);
        this.mped = objects;
        this.conp = context;
        this.resourcep = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(conp).inflate(resourcep, null);
        }

        Mensaje pp = mped.get(position);

        TextView cod = v.findViewById(R.id.idusu);
        cod.setText(String.valueOf(pp.getIdUsuario ()));
        TextView enviado = v.findViewById(R.id.enviado);
        enviado.setText(String.valueOf(pp.getFecha ()));
        TextView texto = v.findViewById(R.id.contmensaje);
        texto.setText(String.valueOf(pp.getTexto ()));



        return v;
    }

    public void CrearChat(View v)
    {

    }

}