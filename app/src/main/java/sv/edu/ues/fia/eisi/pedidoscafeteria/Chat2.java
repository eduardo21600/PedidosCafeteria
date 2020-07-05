package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
    //aquí los URL de los servicios php para que se vea más ordenado
   /* String URLBuscarM = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_consulta.php?IDUSUARIO=";
    String URLBuscarC = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_chat_query.php?IDUSUARIO=Vanessa&IDUSUARIO2=Fernando";
    String URLCrearC = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_insertar.php";
    String URLCrearM = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_actualizar.php";*/
    String resultado = "oh vaya, no crei que funcionara";
    private ListView LV;
    //ControladorServicios controladorServicios;

    List<Mensaje> pps = new ArrayList<> ();

    AdaptadorMensaje mlP;
    List<Chat> chatito = new ArrayList<> ();
    ControladorBD controladorBD = new ControladorBD (this);
    String xa ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        setContentView (R.layout.activity_chat);
        //controladorServicios = new ControladorServicios();
        //controladorServicios.BuscarCHAT ( URLBuscarC,this);




            /**for(int i=0;i<chatito.size ();i++) {
                String dato = chatito.get (i).getIdUsuario ();
                Toast.makeText (this, dato+"lo logró", Toast.LENGTH_LONG).show ();
            }*/

       // Toast.makeText (this,chatito.get(0).getIdUsuario (),Toast.LENGTH_LONG).show ();

        iniciarVista();
        LV = (ListView)findViewById(R.id.msm);
        pps.add(new Mensaje ("josefo1",12,"¿para cuanod vergas llega mi pedido?","julio"));
        pps.add(new Mensaje ("josefina1",12,"¿para cuanod vergas llega mi pedido?","julio"));
        pps.add(new Mensaje ("carlos1",12,"¿para cuanod vergas llega mi pedido?","julio"));
        pps.add(new Mensaje ("corcel1",12,"¿para cuanod vergas llega mi pedido?","julio"));
        controladorBD.abrir ();
        xa= controladorBD.CrearMensaje (new Mensaje (1,"VANESSA",1,"HOLA COMO ESTAN AMIGOS","MAÑANA"));
        Toast.makeText (this,xa+"1",Toast.LENGTH_SHORT).show ();
        xa= controladorBD.CrearMensaje (new Mensaje (2,"Pablo",1,"COMO VAN ","MAÑANA"));
        Toast.makeText (this,xa+"2",Toast.LENGTH_SHORT).show ();
        controladorBD.CrearMensaje (new Mensaje (3,"VANESSA",1,"ME DIO COVID AMIKOS ):","MAÑANA"));
        controladorBD.CrearMensaje (new Mensaje (4,"PABLO",1,"AHHHH NO HAN HECHO NADA ","MAÑANA"));
        controladorBD.CrearMensaje (new Mensaje (5,"VANESSA",1,"ME MUERO ): NO PUEDO RESPIRAR","MAÑANA"));
        controladorBD.CrearMensaje (new Mensaje (6,"PABLO",1,"PONGANSE A TRABAJAR","MAÑANA"));
        //pps = controladorBD.ConsultaMensaje (1);
        controladorBD.cerrar ();
        //LV.setOnItemClickListener(this);
        // Helper.abrir();
        // pps = Helper.ConsultaPedidos();

        //Helper.cerrar();
        mlP = new AdaptadorMensaje (this,R.layout.chat_lista,pps);
        LV.setAdapter(mlP);
    }

    private void iniciarVista() {
        MessageInput inputView = findViewById(R.id.input);
        ListView messagesList = findViewById (R.id.msm);



        inputView.setInputListener(new MessageInput.InputListener(){
            @Override
            public boolean onSubmit(CharSequence input) {
                enviarMensaje(input.toString ());
                return true;
            }
        });
        // adapter = new MessagesListAdapter<>(senderId, imageLoader);
        // messagesList.setAdapter(adapter);
    }

    //aqui

    public void crearCHAT(String URL){
        final List<Chat> chat = new ArrayList<>();
        StringRequest stringRequest= new StringRequest (Request.Method.POST, URL, new Response.Listener<String> (){
            @Override
            public void onResponse(String response) {
                Toast.makeText (getApplicationContext (), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show ();
            }
        }, new Response.ErrorListener (){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (getApplicationContext (), error.toString (), Toast.LENGTH_SHORT).show ();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> parametros= new HashMap<String, String>();
                parametros.put("IDCHAT", String.valueOf (chat.get(0).getIdchat ()));
                parametros.put ("IDUSUARIO", chat.get (0).getIdUsuario ());
                parametros.put ("IDUSUARIO2", chat.get (0).getIdUsuario2());
                return parametros;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue (this);
        requestQueue.add (stringRequest);
    }

    ///hasta aqui









    private void enviarMensaje(String mensaje){

    }


}
