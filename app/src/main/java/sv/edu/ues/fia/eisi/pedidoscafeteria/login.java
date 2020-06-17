package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.home.HomeFragment;

public class login extends AppCompatActivity{

    EditText usuario, contrasena;
    SharedPreferences sharedPreferences;
    List<Usuario> u;
    ControladorServicios cServicios;
    ControladorBD controladorBD;
    List<Local> local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText)findViewById(R.id.nombreUsuario);
        contrasena = (EditText)findViewById(R.id.contraseña);
        controladorBD = new ControladorBD(getApplicationContext());
        cServicios = new ControladorServicios();

    }

    public void entrar(View view){
        // Validaciones aqui:v
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
        editor.putString("tipoUsuario",String.valueOf(usuario.getText()));
        editor.apply();
        //GUARDAR USUARIO EN BASE LOCAL
        //u = cServicios.BuscarUsuario(usuario.getText().ToString());
        //controladorBD.insertar(u[0]);
        //(if u[0].getIdTipoUsuario==2){
        //  local = cServicio.BuscarLocalUsuario(this,u[0].getIdUsuario)
        //  controladorBD.crearLocal(local[0])}
        Intent intent = new Intent (getApplicationContext(), drawerEncar.class);
        startActivity(intent);
    }

    public void registrarse(View v){
        Intent intent = new Intent (getApplicationContext(), crearCuenta.class);
        startActivity(intent);
    }

}
