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

import com.shashank.sony.fancytoastlib.FancyToast;

public class cambiarCrede extends AppCompatActivity {

    Button btnGuardar;
    EditText nombreU, contra, repeContra, telefono;
    Usuario usuario;
    SharedPreferences sharedPreferences;
    ControladorBD controlador;
    int vigilante=0;
    String original;

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

        controlador.abrir();
        usuario = controlador.ConsultaUsuario(nombre);
        controlador.cerrar();
        if(usuario!=null){
            nombreU.setText(usuario.getIdUsuario());
            original = usuario.getIdUsuario();
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
                        //Toast.makeText(getApplicationContext(),"El nombre de usuario ya esta tomado",Toast.LENGTH_LONG).show();
                        FancyToast.makeText(getApplicationContext(),"El nombre de usuario ya esta tomado",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        vigilante = 1; //Nombre invalido
                    }
                    else{
                        vigilante = 0;
                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(),"No puede dejar este campo vacio",Toast.LENGTH_LONG).show();
                    FancyToast.makeText(getApplicationContext(),"No puede dejar este campo vacio",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    vigilante = 2; //Nombre vacio
                }
            }
        });

        //Verificando que no se deje contraseña vacia
        contra.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(contra.getText().toString().isEmpty()){
                        //Toast.makeText(getApplicationContext(),"No puede dejar este campo vacio",Toast.LENGTH_LONG).show();
                        FancyToast.makeText(getApplicationContext(),"No puede dejar este campo vacio",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        vigilante = 3; //contraseña vacia
                    }
                    else{
                        if(!contra.getText().toString().equals(repeContra.getText().toString())){
                            FancyToast.makeText(getApplicationContext(),"Las contraseñas no coinciden",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            vigilante = 4; //Contraseñas no coinciden
                        }
                        else{
                            vigilante = 0;
                        }
                    }
                }
            }
        });

        //Verificando que la contraseña coincida
        repeContra.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!(repeContra.getText().toString().isEmpty())){
                        if(!contra.getText().toString().equals(repeContra.getText().toString())){
                            //Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                            FancyToast.makeText(getApplicationContext(),"Las contraseñas no coinciden",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            vigilante = 4; //Contraseñas no coinciden
                        }
                        else{
                            vigilante = 0;
                        }
                    }
                    else{
                        //Toast.makeText(getApplicationContext(),"No puede dejar este campo vacio",Toast.LENGTH_LONG).show();
                        FancyToast.makeText(getApplicationContext(),"No puede dejar este campo vacio",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        vigilante = 5; //Repetir contraseña vacio
                    }
                }
            }
        });

        //Verificando que no se deje el numero de telefono vacio
        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(telefono.getText().toString().isEmpty()){
                    //Toast.makeText(getApplicationContext(),"No puede dejar este campo vacio",Toast.LENGTH_LONG).show();
                    FancyToast.makeText(getApplicationContext(),"No puede dejar este campo vacio",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    vigilante = 6; //telefono vacio
                }
                else{
                    vigilante = 0;
                }
            }
        });


        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String nomU = nombreU.getText().toString();
               String verContra = contra.getText().toString();
               String tel = telefono.getText().toString();

               switch (vigilante){
                   case 0:
                       if(!(repeContra.getText().toString().isEmpty())&&!(contra.getText().toString().isEmpty())){
                           if(contra.getText().toString().equals(repeContra.getText().toString())){
                               controlador.abrir();
                               usuario.setIdUsuario(nomU);
                               usuario.setContrasena(verContra);
                               usuario.setTeleUsuario(tel);
                               controlador.actualizarUsuario(usuario,original);
                               SharedPreferences.Editor editor = sharedPreferences.edit();
                               editor.putString("nombreUsuario",nomU);
                               editor.apply();
                               controlador.cerrar();
                               FancyToast.makeText(getApplicationContext(),"Se han guardado los cambios",2,FancyToast.SUCCESS,true).show();
                               break;
                           }
                           else {
                               FancyToast.makeText(getApplicationContext(),"No puede guardar, las contraseñas no coinciden",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                               break;
                           }
                       }
                       else{
                           FancyToast.makeText(getApplicationContext(),"No puede guardar, especifique contraseña",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                           break;
                       }

                   case 1:
                       FancyToast.makeText(getApplicationContext(),"No puede guardar, cambie su nombre de usuario",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                       break;
                   case 2:
                       FancyToast.makeText(getApplicationContext(),"No puede guardar, nombre de usuario vacio",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                       break;
                   case 3:
                       FancyToast.makeText(getApplicationContext(),"No puede guardar, especifique contraseña",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                       break;
                   case 4:
                       FancyToast.makeText(getApplicationContext(),"No puede guardar, las contraseñas no coinciden",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                       break;
                   case 5:
                       FancyToast.makeText(getApplicationContext(),"No puede guardar, debe verificar contraseña",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                       break;
                   case 6:
                       FancyToast.makeText(getApplicationContext(),"No puede guardar, especifique nuevo teléfono",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                       break;
                   default:
                       FancyToast.makeText(getApplicationContext(),"Algo anda mal",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

               }
            }
        });
    }

    public void llenar (View v){
        //LLENADO PARA PRUEBAS RECUERDA QUITAR ESTO LUEGO!
        controlador.llenarUsuario();
    }
}

