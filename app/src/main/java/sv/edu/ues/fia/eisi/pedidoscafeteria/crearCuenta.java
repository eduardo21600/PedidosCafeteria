package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackRespuestaString;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;


public class crearCuenta extends AppCompatActivity implements CallbackWS, CallbackRespuestaString {

    private Chip chip;
    private EditText nombre, apellido,usuario, contra, repeContra, telefono, nomLocal;
    private TextView tvNomLocal;
    private ControladorServicios cServicio;
    private ControladorBD controladorBD;
    private Usuario usu, userLocal;
    private int tipo=1;
    private int vigilante=0;
    private int encargadoActivado;
    private Button btnCrear;
    private List<Usuario> prueba;
    private boolean noCrearlocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        nombre = (EditText)findViewById(R.id.etNomC);
        apellido = (EditText)findViewById(R.id.etApeC);
        usuario = (EditText)findViewById(R.id.etNomUsuC);
        contra = (EditText)findViewById(R.id.etContraC);
        repeContra = (EditText)findViewById(R.id.etRepeConC);
        telefono = (EditText)findViewById(R.id.etTelC);
        nomLocal = (EditText)findViewById(R.id.etNomLocC);
        chip = (Chip)findViewById(R.id.chip2);
        tvNomLocal = (TextView)findViewById(R.id.tvNomLocal);
        btnCrear = (Button)findViewById(R.id.btnCrearCuenta);
        encargadoActivado=0;
        cServicio = new ControladorServicios(this, this);
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        noCrearlocal=true;


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocal = controladorBD.ConsultaUsuario(usuario.getText().toString());
                if(userLocal==null){
                    verificarId();
                }
                else{
                    FancyToast.makeText(getApplicationContext(),
                            getResources().getString(R.string.usuarioEnUso),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }
            }
        });

        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chip.isChecked()){
                    tvNomLocal.setVisibility(View.VISIBLE);
                    nomLocal.setVisibility(View.VISIBLE);
                    nomLocal.setEnabled(true);
                    tipo = 3;//Encargado
                    encargadoActivado=1;
                }
                else{
                    tvNomLocal.setVisibility(View.INVISIBLE);
                    nomLocal.setVisibility(View.INVISIBLE);
                    tipo = 1;//Cliente
                }

            }
        });

        usuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(usuario.getText().toString().isEmpty()){
                        FancyToast.makeText(getApplicationContext(),
                                getResources().getString(R.string.camposVacios),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                        vigilante=1;
                    }
                    else{
                        //cServicio.BuscarUsuario(usuario.getText().toString(),getApplicationContext());
                    }
                }
            }
        });

        //Verificar que nombre no este vacio
        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(nombre.getText().toString().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),
                            getResources().getString(R.string.camposVacios),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                    nombre.setError(getResources().getString(R.string.ingreseUnNombre));
                    vigilante=1;
                }
                else{
                    vigilante=0;
                }

            }
        });

        //Verificar que apellido no este vacio
        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(apellido.getText().toString().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),
                            getResources().getString(R.string.camposVacios),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    apellido.setError(getResources().getString(R.string.ingreseUnApellido));
                    vigilante=1;
                }
                else{
                    vigilante=0;
                }
            }
        });

        //Verificar contraseñas
        contra.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!contra.getText().toString().isEmpty()){
                        if(!(contrasenas())){
                            FancyToast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.contraseñaNoCoincide),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                            contra.setError(getResources().getString(R.string.contraseñaNoCoincide));
                            vigilante=1;
                        }
                        else{
                            contra.setError(null);
                            vigilante=0;
                        }
                    }
                    else{
                        FancyToast.makeText(getApplicationContext(),
                                getResources().getString(R.string.camposVacios),
                                FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                        contra.setError(getResources().getString(R.string.camposVacios));
                        vigilante=1;
                    }
                }
            }
        });

        //Verificar repe contra
        repeContra.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!repeContra.getText().toString().isEmpty()){
                        if(!(contrasenas())){
                            FancyToast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.contraseñaNoCoincide),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                            repeContra.setError(getResources().getString(R.string.contraseñaNoCoincide));
                            vigilante=1;
                        }
                        else{
                            repeContra.setError(null);
                            vigilante=0;
                        }
                    }
                    else{
                        FancyToast.makeText(getApplicationContext(),
                                getResources().getString(R.string.camposVacios),
                                FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                        repeContra.setError(getResources().getString(R.string.camposVacios));
                        vigilante=1;
                    }
                }
            }
        });

        //Verificar telefono
        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(telefono.getText().toString().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),
                            getResources().getString(R.string.camposVacios),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    telefono.setError(getResources().getString(R.string.ingreseUnNumero));
                    vigilante=1;
                }
                else{
                    vigilante=0;
                }
            }
        });

        //Verificar nombre local
        nomLocal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(nomLocal.getText().toString().isEmpty()){
                    FancyToast.makeText(getApplicationContext(),
                            getResources().getString(R.string.camposVacios),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    nomLocal.setError(getResources().getString(R.string.ingreseUnNombre));
                    vigilante=1;
                }
                else{
                    vigilante=0;
                }
            }
        });
    }

    public void CrearCuenta(){
        if(vigilante==0){
                if(!(contra.getText().toString().isEmpty())&&!(repeContra.getText().toString().isEmpty())&&contrasenas()&&vacios()){
                    if(encargadoActivado==0){
                        usu = obtenerDatos();
                        cServicio.CrearAct(usu,getApplicationContext(),true);
                        controladorBD.insertar(usu);
                        finish();
                    }
                    else{
                        if(nomLocal.getText().toString().isEmpty()){
                            FancyToast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.errorAlCrear),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                        }
                        else
                        {
                            noCrearlocal=false;
                            usu = obtenerDatos();
                            cServicio.CrearAct(usu,getApplicationContext(),true);
                            controladorBD.insertar(usu);
                        }
                    }

                }
                else{
                    FancyToast.makeText(getApplicationContext(),
                            getResources().getString(R.string.errorAlCrear),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                }


        }
        else{
                FancyToast.makeText(getApplicationContext(),
                        getResources().getString(R.string.errorAlCrear),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
            }
    }

    public void verificarId(){
        if(vigilante==0) {
            cServicio.BuscarUsuario(usuario.getText().toString(), getApplicationContext());
        }
        else{
            FancyToast.makeText(getApplicationContext(),
                    getResources().getString(R.string.errorAlCrear),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
        }
    }

    public Usuario obtenerDatos(){
        Usuario usuario1 = new Usuario(
                usuario.getText().toString(),
                tipo,
                contra.getText().toString(),
                nombre.getText().toString(),
                telefono.getText().toString(),
                apellido.getText().toString()
        );
        return usuario1;
    }

    public boolean contrasenas(){
        boolean resul;
        if(contra.getText().toString().equals(repeContra.getText().toString())){
            resul=true;
        }
        else{
            resul=false;
        }
        return resul;
    }

    public boolean vacios(){
        boolean resul;
        if(!(nombre.getText().toString().isEmpty())&&!(apellido.getText().toString().isEmpty())
                &&!(telefono.getText().toString().isEmpty())){
            resul=true;
        }
        else{
            resul=false;
        }
        return resul;
    }

    @Override
    public void ResponseWS(Object lista)
    {
        prueba = (List<Usuario>) lista;
        if(prueba.isEmpty())
        {
            CrearCuenta();
        }
        else
        {
            FancyToast.makeText(getApplicationContext(),
                    getResources().getString(R.string.usuarioEnUso),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
        }
    }

    @Override
    public void respuesta(String respuesta)
    {
        if(respuesta.equals("CONEXIÓN EXITOSA"))
        {
            if(noCrearlocal){
                FancyToast.makeText(getApplicationContext(),
                        getResources().getString(R.string.usuarioCreado),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,R.drawable.exito,true).show();
                finish();
            }
            else {
                ControladorServicios controladorServicios = new ControladorServicios();
                String nom = nomLocal.getText().toString();
                Local local = new Local();
                local.setNombreLocal(nom);
                local.setIdUsuario(usu.getIdUsuario());
                controladorBD.CrearLocal(local);

                controladorServicios.CrearAct(local, getApplicationContext(), true);

                FancyToast.makeText(getApplicationContext(),
                        getResources().getString(R.string.usuarioCreado),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,R.drawable.exito,true).show();
                finish();
            }

        }
        else
        {
            FancyToast.makeText(getApplicationContext(),
                    getResources().getString(R.string.usuarioNoCreado),FancyToast.LENGTH_LONG,FancyToast.ERROR,R.drawable.error,true).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controladorBD.cerrar();
    }
}
