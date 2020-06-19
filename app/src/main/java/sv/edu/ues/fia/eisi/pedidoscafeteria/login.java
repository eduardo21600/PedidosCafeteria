package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.Pedidos.PedidosR;

public class login extends AppCompatActivity implements CallbackWS {

    EditText usuario, contrasena;
    List<Usuario> u;
    ControladorServicios cServicios;
    ControladorBD controladorBD;
    List<Local> local;
    SharedPreferences sharedPreferences;
    Usuario userLocal;
    boolean vigilanteLocal;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText)findViewById(R.id.nombreUsuario);
        contrasena = (EditText)findViewById(R.id.contraseña);
        controladorBD = new ControladorBD(getApplicationContext());
        cServicios = new ControladorServicios(this);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0); // 0 - for private mode
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(255,51,156), PorterDuff.Mode.SRC_IN);
    }

    public void entrar(View view){
        cServicios.BuscarUsuario(usuario.getText().toString(), getApplicationContext());
        controladorBD.abrir();
        userLocal=controladorBD.ConsultaUsuario(usuario.getText().toString());

        progressBar.setVisibility(View.VISIBLE);
        if(!(userLocal==null)){
            vigilanteLocal=true; //se encontro el usuario en BD local
        }
        else{
            vigilanteLocal=false; //no se encontro en BD local
        }
        controladorBD.cerrar();
    }

    public void registrarse(View v){
        Intent intent = new Intent (getApplicationContext(), crearCuenta.class);
        startActivity(intent);
    }

    @Override
    public void ResponseWS(Object lista)
    {
        u = (List<Usuario>) lista;
        progressBar.setVisibility(View.INVISIBLE);
        if (u.isEmpty()&&!vigilanteLocal)
        {
            usuario.setError("Usuario no existente");
            FancyToast.makeText(getApplicationContext(), "El usuario no existe", FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
        }
        else if (!u.isEmpty())
        {
            Usuario usu = u.get(0);
            if(usu.getContrasena().equals(contrasena.getText().toString()))
            {

                if (usu.getIdTipoUsuario()==1)
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
                    editor.putString("tipoUsuario","1");//cliente
                    editor.apply();
                }
                else if (usu.getIdTipoUsuario()==2)
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
                    editor.putString("tipoUsuario","2");//repartidor
                    editor.apply();
                }
                else
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
                    editor.putString("tipoUsuario","3");//encargado
                    editor.apply();
                }
                progressBar.setVisibility(View.VISIBLE);
                controladorBD.abrir();
                controladorBD.insertar(u.get(0));
                controladorBD.cerrar();
                progressBar.setVisibility(View.INVISIBLE);
                Intent intent = new Intent (getApplicationContext(), drawerEncar.class);
                startActivity(intent);
            }
            else
            {
                FancyToast.makeText(getApplicationContext(), "Contraseña no válida", FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
            }
        }
        else{
            if (contrasena.getText().toString().equals(userLocal.getContrasena())){
                if(userLocal.getIdTipoUsuario()==1){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
                    editor.putString("tipoUsuario","1");//cliente
                    editor.apply();
                }
                else if (userLocal.getIdTipoUsuario()==2){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
                    editor.putString("tipoUsuario","2");//repartidor
                    editor.apply();
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
                    editor.putString("tipoUsuario","3");//encargado
                    editor.apply();
                }
                Intent intent = new Intent (getApplicationContext(), drawerEncar.class);
                startActivity(intent);
            }
            else{
                FancyToast.makeText(getApplicationContext(), "Contraseña no válida",
                        FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
            }
        }

    }
}
