package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cambiarCrede extends AppCompatActivity {

    Button btnGuardar;
    EditText nombreU, contra, repeContra, telefono;
    Usuario usuario,usuarioMod;
    SharedPreferences sharedPreferences;
    ControladorBD controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_crede);
        sharedPreferences = getSharedPreferences("validacion",0);
        String nombre =sharedPreferences.getString("nombreUsuario","usuario");
        controlador = new ControladorBD(this);
        nombreU=(EditText)findViewById(R.id.nombreUsuario);
        contra=(EditText)findViewById(R.id.contra);
        repeContra=(EditText)findViewById(R.id.repContra);
        telefono=(EditText)findViewById(R.id.telefono);


        //LLENADO PARA PRUEBAS RECUERDA QUITAR ESTO LUEGO!
        controlador.llenarUsuario();

        controlador.abrir();
        usuario=controlador.ConsultaUsuario(nombre);
        if(usuario!=null){
            nombreU.setText(usuario.getIdUsuario());
            contra.setText(usuario.getContrasena());
            repeContra.setText(usuario.getContrasena());
            telefono.setText(usuario.getTeleUsuario());
        }
        //Verificando que el nuevo nombre de usuario no este tomado
        nombreU.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!(nombreU.getText().toString().isEmpty())){
                    if(!(controlador.ConsultaUsuario(nombreU.getText().toString())==null)){
                        Toast.makeText(getApplicationContext(),"El nombre de usuario ya esta tomado",Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"No puede dejar este campo vacio",Toast.LENGTH_LONG).show();

                }
            }
        });


        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String nomU = nombreU.getText().toString();
               String verContra = contra.getText().toString();
               String verContraR = repeContra.getText().toString();
               String tel = telefono.getText().toString();
                if(verContra.equals(verContraR)){
                    if(nomU!=""){
                        if(controlador.ConsultaUsuario(nomU)==null){
                            usuario.setIdUsuario(nomU);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"El nombre de usuario ya esta tomado",Toast.LENGTH_LONG).show();
                            nombreU.setText(usuario.getIdUsuario());
                        }
                    if(tel!=""){
                        usuario.setTeleUsuario(tel);
                        }
                        controlador.actualizarUsuario(usuario);
                        controlador.cerrar();
                        Toast.makeText(getApplicationContext(),"Se guardaron los cambios",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                    contra.setText("");
                    repeContra.setText("");
                }

            }
        });
    }
}
