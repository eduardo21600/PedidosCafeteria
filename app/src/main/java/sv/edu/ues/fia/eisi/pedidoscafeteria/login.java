package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText)findViewById(R.id.nombreUsuario);
        contrasena = (EditText)findViewById(R.id.contraseña);
    }

    public void entrar(View view){
        // Validaciones aqui:v
        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
