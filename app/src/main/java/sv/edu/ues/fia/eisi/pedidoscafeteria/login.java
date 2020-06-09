package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.home.HomeFragment;

public class login extends AppCompatActivity {

    EditText usuario, contrasena;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText)findViewById(R.id.nombreUsuario);
        contrasena = (EditText)findViewById(R.id.contraseña);
    }

    public void entrar(View view){
        // Validaciones aqui:v
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("validacion", 0); // 0 - for private mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombreUsuario",String.valueOf(usuario.getText()));
        editor.putString("tipoUsuario",String.valueOf(usuario.getText()));
        editor.apply();
        Intent intent = new Intent (getApplicationContext(), drawerEncar.class);
        startActivity(intent);
    }
}
