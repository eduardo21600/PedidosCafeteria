package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class login extends AppCompatActivity implements CallbackWS {

    EditText usuario, contrasena;
    List<Usuario> u;
    ControladorServicios cServicios;
    ControladorBD controladorBD;
    List<Local> local;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText)findViewById(R.id.nombreUsuario);
        contrasena = (EditText)findViewById(R.id.contraseña);
        controladorBD = new ControladorBD(getApplicationContext());
        cServicios = new ControladorServicios(this);
        sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0); // 0 - for private mode
    }

    public void entrar(View view){
        // Validaciones aqui:v
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
        editor.putString("tipoUsuario",String.valueOf(usuario.getText()));
        editor.apply();

        cServicios.BuscarUsuario(usuario.getText().toString(), getApplicationContext());
        //GUARDAR USUARIO EN BASE LOCAL
        //u = cServicios.BuscarUsuario(usuario.getText().ToString());
        //controladorBD.insertar(u[0]);
        //(if u[0].getIdTipoUsuario==2){
        //  local = cServicio.BuscarLocalUsuario(this,u[0].getIdUsuario)
        //  controladorBD.crearLocal(local[0])}

    }

    public void registrarse(View v){
        Intent intent = new Intent (getApplicationContext(), crearCuenta.class);
        startActivity(intent);
    }

    @Override
    public void ResponseWS(Object lista)
    {
        u = (List<Usuario>) lista;

        if (u.isEmpty())
        {
            usuario.setError("Usuario no existente");
            FancyToast.makeText(getApplicationContext(), "El usuario no existe", FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
        }
        else
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
                Intent intent = new Intent (getApplicationContext(), drawerEncar.class);
                startActivity(intent);
            }
            else
            {
                FancyToast.makeText(getApplicationContext(), "Contraseña no válida", FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error, false).show();
            }
        }

    }
}
