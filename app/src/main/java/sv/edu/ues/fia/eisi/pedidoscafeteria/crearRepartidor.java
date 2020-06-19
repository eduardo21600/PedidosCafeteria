package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

public class crearRepartidor extends AppCompatActivity {
    private EditText etNomRepa, apeRepa, usuarioRepa,contraRepa, repeConRepa, teleRepa;
    private Button btnCrearRepa;
    private ControladorBD controladorBD;
    Usuario nuevoRepa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_repartidor);
        etNomRepa = (EditText)findViewById(R.id.etNomRe);
        apeRepa = (EditText)findViewById(R.id.etApeRe);
        usuarioRepa = (EditText)findViewById(R.id.etNomUsuRe);
        contraRepa = (EditText)findViewById(R.id.etContraRe);
        repeConRepa = (EditText)findViewById(R.id.etRepContraRe);
        teleRepa = (EditText)findViewById(R.id.etTelefonoRe);
        btnCrearRepa = (Button) findViewById(R.id.btnCrearRe);
        nuevoRepa = new Usuario();
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
         btnCrearRepa.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!(vacios())){
                     if(!idEnUso()){
                         if(verificarContraseñas()){
                             nuevoRepa = new Usuario(usuarioRepa.getText().toString(),
                                     2,
                                     contraRepa.getText().toString(),
                                     etNomRepa.getText().toString(),
                                     teleRepa.getText().toString(),
                                     apeRepa.getText().toString(),
                                     "1");
                             controladorBD.insertar(nuevoRepa);
                             controladorBD.cerrar();
                             FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.repaCreado),
                                     FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                             finish();
                         }
                         else{
                             erroresEnFormulario();
                             repeConRepa.setError(getResources().getString(R.string.contraseñaNoCoincide));
                             repeConRepa.addTextChangedListener(new TextWatcher() {
                                 @Override
                                 public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                                 @Override
                                 public void onTextChanged(CharSequence s, int start, int before, int count) {}
                                 @Override
                                 public void afterTextChanged(Editable s) {
                                    if(verificarContraseñas()){
                                        repeConRepa.setError(null);
                                    }
                                 }
                             });
                         }
                     }
                     else{
                         erroresEnFormulario();
                         usuarioRepa.setError(getResources().getString(R.string.usuarioEnUso));
                         usuarioRepa.addTextChangedListener(new TextWatcher() {
                             @Override
                             public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                             @Override
                             public void onTextChanged(CharSequence s, int start, int before, int count) {}
                             @Override
                             public void afterTextChanged(Editable s) {
                                   if(!idEnUso()){
                                       repeConRepa.setError(null);
                                   }
                             }
                         });
                     }

                 }
                 else{
                     FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.camposVacios),
                             FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                 }
             }
         });


    }

    public boolean vacios(){
        if(etNomRepa.getText().toString().isEmpty()&&apeRepa.getText().toString().isEmpty()&&
            usuarioRepa.getText().toString().isEmpty()&&contraRepa.getText().toString().isEmpty()&&
            contraRepa.getText().toString().isEmpty()&&repeConRepa.getText().toString().isEmpty()&&
            teleRepa.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }

    public boolean verificarContraseñas(){
        if(repeConRepa.getText().toString().equals(contraRepa.getText().toString())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean idEnUso(){
        Usuario u = new Usuario();
        u = controladorBD.ConsultaUsuario(usuarioRepa.getText().toString());
        if(u==null){
            return false;//usuario tomado
        }
        else{
            return true;
        }
    }
    public void erroresEnFormulario(){
        FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.errorAlCrear),
                FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
    }
}
