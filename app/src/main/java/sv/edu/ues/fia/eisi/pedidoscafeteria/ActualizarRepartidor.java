package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ActualizarRepartidor extends AppCompatActivity {

    private EditText etNomRepa, Nomusu,telefono,contraseña,apellido,repeC;
    private Button btnActualizar;
    private TextView tvTitulo;
    private String idRepa,telRepa,nomRepa,contraRepa,apellRepa,estado;
    private ControladorBD controladorBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_repartidor);
        Intent intent =getIntent();
        idRepa = intent.getStringExtra("idRepa");
        nomRepa = intent.getStringExtra("nombreRepa");
        apellRepa = intent.getStringExtra("apellidoRepa");
        telRepa = intent.getStringExtra("telefono");
        contraRepa = intent.getStringExtra("contraseña");
        estado = intent.getStringExtra("estado");
        etNomRepa = (EditText)findViewById(R.id.etNomRe);
        apellido = (EditText)findViewById(R.id.etApeRe);
        Nomusu = (EditText)findViewById(R.id.etNomUsuRe);
        contraseña = (EditText)findViewById(R.id.etContraRe);
        repeC = (EditText)findViewById(R.id.etRepContraRe);
        telefono = (EditText)findViewById(R.id.etTelefonoRe);
        tvTitulo = (TextView)findViewById(R.id.tvRepaVista);
        btnActualizar = (Button)findViewById(R.id.btnCrearRe);
        controladorBD = new ControladorBD(getApplicationContext());

        tvTitulo.setText(getResources().getString(R.string.actRepa));
        btnActualizar.setText(getResources().getString(R.string.actRepa));

        etNomRepa.setText(nomRepa);
        apellido.setText(apellRepa);
        telefono.setText(telRepa);
        Nomusu.setText(idRepa);
        contraseña.setText(contraRepa);
        repeC.setText(contraRepa);
        controladorBD.abrir();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRepartidor();
            }
        });


    }

    public void actualizarRepartidor(){
        if(!estanVacios()){
            if(idDisponible()){
                if(repeContraCoincide()){
                    Usuario repaAct = new Usuario(Nomusu.getText().toString(),
                            2,
                            contraseña.getText().toString(),
                            etNomRepa.getText().toString(),
                            telefono.getText().toString(),
                            apellido.getText().toString(),
                            estado);
                    controladorBD.actualizarUsuario2(repaAct,idRepa);
                    controladorBD.cerrar();
                    FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.repaCreado),
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                    finish();
                }
                else{
                    hayErrores();
                    repeC.setError(getResources().getString(R.string.contraseñaNoCoincide));
                    repeC.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(repeContraCoincide()){
                                repeC.setError(null);
                            }
                        }
                    });
                }
            }
            else {
                hayErrores();
                Nomusu.setError(getResources().getString(R.string.usuarioEnUso));
                Nomusu.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(idDisponible()){
                            Nomusu.setError(null);
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
    public boolean estanVacios(){
        if(etNomRepa.getText().toString().isEmpty()||apellido.getText().toString().isEmpty()||telefono.getText().toString().isEmpty()||
        Nomusu.getText().toString().isEmpty()||contraseña.getText().toString().isEmpty()||repeC.getText().toString().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean idDisponible(){
        Usuario u;
        u = controladorBD.ConsultaUsuario(Nomusu.getText().toString());
        if(u==null){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean repeContraCoincide(){
        if (repeC.getText().toString().equals(contraseña.getText().toString())){
            return true;
        }
        else {
            return false;
        }
    }
    public void hayErrores(){
        FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.errorAlCrear),
                FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
    }
}
