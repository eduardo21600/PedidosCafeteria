package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class Chat2 extends AppCompatActivity {
    //MessagesListAdapter<Message> adapter;
    RequestQueue requestQueue;
    private ListView LV;
    private TextView tx;
    String resultado = "oh vaya, no crei que funcionara";
    private List<Chat> chats = new ArrayList<>();
    List<Mensaje> pps = new ArrayList<> ();
    String usu2 = "Fernando";
    SharedPreferences sharedPreferences;
    String usu ="";
    PedidoRealizado pedidoRealizado;
    PedidoAsignado pedidoAsignado;
    int idPed;
     //esto debe ser un putextra
    int ichat;
    AdaptadorMensaje mlP;
    List<Chat> chatito = new ArrayList<> ();
    ControladorBD controladorBD = new ControladorBD (this);
    String xa ="";
    Usuario usuB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");
        Intent intent = getIntent();
        String i = intent.getStringExtra("id");
        Toast.makeText(this,"es este pedido"+ i, Toast.LENGTH_SHORT).show();
        idPed = Integer.valueOf(i);
        Toast.makeText(this,usu, Toast.LENGTH_SHORT).show();
        setContentView (R.layout.activity_chat);
        iniciarVista();
        LV = (ListView)findViewById(R.id.msm);
        tx = (TextView)findViewById(R.id.editText2);
        controladorBD.abrir ();
        usuB =controladorBD.ConsultaUsuario(usu);
        if(usuB.getIdTipoUsuario() ==2)
        {
            Toast.makeText(this, "todo bien", Toast.LENGTH_SHORT).show();
            pedidoRealizado = controladorBD.consultarPedRealU(idPed);
            if(!(pedidoRealizado == null))
            {
                usu2 = pedidoRealizado.getIdUsuario();
                Toast.makeText(this,usu2, Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "No tendrías que estar viendo esto ¿no creaste un pedidorealizado?", Toast.LENGTH_SHORT).show();
            }

        }else
        {

            Toast.makeText(this, "eres un cliente", Toast.LENGTH_SHORT).show();
            pedidoAsignado = controladorBD.consultarPedAsig(idPed);
            if(!(pedidoAsignado == null))
            {
                usu2 = pedidoRealizado.getIdUsuario();
                Toast.makeText(this,usu2, Toast.LENGTH_SHORT).show();
                usuB.setIdUsuario(usu);
                usu = usu2;
                usu2 = usuB.getIdUsuario();
            }else
            {
                Toast.makeText(this, "No tendrías que estar viendo esto ¿no creaste un pedidorealizado?", Toast.LENGTH_SHORT).show();
            }
        }

        
       chats = controladorBD.ConsultaChat(new Chat(usu,usu2));


       if(!(chats == null)) {
           ichat = chats.get(0).getIdchat();
           Toast.makeText(this, String.valueOf(ichat), Toast.LENGTH_SHORT).show();
           Toast.makeText(this, "No esta vacio", Toast.LENGTH_SHORT).show();
       }else
       {
           String respuesta1 = controladorBD.CrearChat(new Chat(usu,usu2));
           Toast.makeText(this, respuesta1, Toast.LENGTH_SHORT).show();
           chats = controladorBD.ConsultaChat(new Chat(usu,usu2));
           ichat = chats.get(0).getIdchat();

       }
        //Toast.makeText(this,String.valueOf(ichat), Toast.LENGTH_SHORT).show();

        pps = controladorBD.ConsultaMensaje(ichat);
        controladorBD.cerrar();
        if(pps == null)
        {
            controladorBD.abrir();
            String m = controladorBD.CrearMensaje(new Mensaje(usu,ichat,"¿alguna duda sobre el pedido?","hoy"));
            pps = controladorBD.ConsultaMensaje(ichat);
            controladorBD.cerrar();
            Toast.makeText(this, "Parece que no tienes mensajes", Toast.LENGTH_SHORT).show();
        }


        mlP = new AdaptadorMensaje (this,R.layout.chat_lista,pps);
        LV.setAdapter(mlP);


    }

    private void iniciarVista() {
        MessageInput inputView = findViewById(R.id.input);
        ListView messagesList = findViewById (R.id.msm);

        inputView.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                //validate and send message
              //  adapter.addToStart(mensaje, true);
                crearMensaje(input.toString ());
                return true;
            }
        });

        // adapter = new MessagesListAdapter<>(senderId, imageLoader);
        // messagesList.setAdapter(adapter);
    }

    //con este método creas un mensaje nuevo
    public void crearMensaje(String text)
    { String m;
        controladorBD.abrir();
        if(usuB.getIdTipoUsuario()==2)
        {
           m = controladorBD.CrearMensaje(new Mensaje(usu,ichat,text,"hoy")); //cambiar texto por el input
        }else
        {
             m = controladorBD.CrearMensaje(new Mensaje(usu2,ichat,text,"hoy")); //cambiar texto por el input
        }

        controladorBD.cerrar();
        Toast.makeText(getApplicationContext(),m + " ¿se habrá creado?",Toast.LENGTH_SHORT); //mensaje de prueba
        tx.setText("");
    }
    public void BuscarChat(Chat chat)
    {

    }



    ///hasta aqui









    private void enviarMensaje(String mensaje){

    }


}
