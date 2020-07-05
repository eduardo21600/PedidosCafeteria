package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    String resultado = "oh vaya, no crei que funcionara";
    private ListView LV;
    private TextView tx;
    private List<Chat> chats = new ArrayList<>();
    List<Mensaje> pps = new ArrayList<> ();
    String usu2 = "Fernando";
    SharedPreferences sharedPreferences;
    String usu ="";
    PedidoRealizado pedidoRealizado;
    int puExtra = 1; //esto debe ser un putextra
    int ichat;


    AdaptadorMensaje mlP;
    List<Chat> chatito = new ArrayList<> ();
    ControladorBD controladorBD = new ControladorBD (this);
    String xa ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0);
        usu = sharedPreferences.getString("nombreUsuario", "No Name");

        setContentView (R.layout.activity_chat);
        iniciarVista();
        LV = (ListView)findViewById(R.id.msm);
        tx = (TextView)findViewById(R.id.editText2);
        controladorBD.abrir ();


        pedidoRealizado = controladorBD.consultarPedRealU(puExtra);
        usu2 = pedidoRealizado.getIdUsuario();
        chats = controladorBD.ConsultaChat(new Chat(usu,usu2));
        ichat = chats.get(0).getIdchat();
        pps = controladorBD.ConsultaMensaje (1);
        //pps = controladorBD.ConsultaMensaje (ichat);
        controladorBD.cerrar();


        //Helper.cerrar();
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
                return true;
            }
        });

        // adapter = new MessagesListAdapter<>(senderId, imageLoader);
        // messagesList.setAdapter(adapter);
    }

    //con este método creas un mensaje nuevo
    public void crearMensaje(View v)
    {
        controladorBD.abrir();
        String m = controladorBD.CrearMensaje(new Mensaje(usu,chats.get(0).getIdchat(),String.valueOf(tx.getText()),"hoy"));
        controladorBD.cerrar();
        Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT); //mensaje de prueba
        tx.setText("");
    }
    public void BuscarChat(Chat chat)
    {

    }



    ///hasta aqui









    private void enviarMensaje(String mensaje){

    }


}
