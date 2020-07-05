package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActualizarRepartidor extends AppCompatActivity {

    private EditText etNomRepa, Nomusu,telefono,contraseña,apellido,repeC;
    private Button btnActualizar;
    private TextView tvTitulo;
    private String idRepa,telRepa,nomRepa,contraRepa,apellRepa,estado;
    private ControladorBD controladorBD;
    private CircleImageView foto_repa;
    boolean disponible;
    int vigilante=0;
    GestureDetectorCompat gestureDetector;
    Bitmap imageBitmap;
    ControladorServicios cServicios;

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
        foto_repa = (CircleImageView)findViewById(R.id.iv_foto_repa);
        controladorBD = new ControladorBD(getApplicationContext());
        cServicios = new ControladorServicios();
        imageBitmap=null;
        gestureDetector = new GestureDetectorCompat(this, new GestureListener());

        tvTitulo.setText(getResources().getString(R.string.actRepa));
        btnActualizar.setText(getResources().getString(R.string.actRepa));

        etNomRepa.setText(nomRepa);
        apellido.setText(apellRepa);
        telefono.setText(telRepa);
        Nomusu.setText(idRepa);
        contraseña.setText(contraRepa);
        repeC.setText(contraRepa);
        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+idRepa+"_usuario.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.job)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(foto_repa);
        controladorBD.abrir();
        foto_repa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarRepartidor();
            }
        });


    }

    public void actualizarRepartidor(){
        if(!estanVacios()){
            if(idDisponible()&&vigilante==1){
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
                    cServicios.subirImagen(getApplicationContext(),imageBitmap,"usuario",Nomusu.getText().toString());
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
            else if(vigilante==0){
                Usuario repaAct = new Usuario(Nomusu.getText().toString(),
                        2,
                        contraseña.getText().toString(),
                        etNomRepa.getText().toString(),
                        telefono.getText().toString(),
                        apellido.getText().toString(),
                        estado);
                controladorBD.actualizarUsuario3(repaAct,idRepa);
                controladorBD.cerrar();
                cServicios.subirImagen(getApplicationContext(),imageBitmap,"usuario",Nomusu.getText().toString());
                FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.repaCreado),
                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                finish();
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

        Nomusu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vigilante=1;//cambio usuario
                Usuario u = controladorBD.ConsultaUsuario(Nomusu.getText().toString());
                if(u==null){
                    disponible = true;
                }
                else{
                    disponible = false;
                }
            }
        });

        return disponible;
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

    static final int REQUEST_TAKE_PHOTO = 1;

    public void tomarFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    private static final int SEARCH_IMAGE_REQUEST = 2;
    public void buscarEnGaleria(){
        Intent intent = new Intent(); //crea un nuevo intent
        intent.setType("image/*");    //pone que solo sea para buscar cualquier tipo de imagen
        intent.setAction(Intent.ACTION_GET_CONTENT); // le pone la acción que es, escoger un contenido
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SEARCH_IMAGE_REQUEST);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            if (ContextCompat.checkSelfPermission(ActualizarRepartidor.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActualizarRepartidor.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ActualizarRepartidor.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
            }
            final MaterialDialog mDialog = new MaterialDialog.Builder(ActualizarRepartidor.this)
                    .setTitle("Cambiar foto de perfil")
                    .setAnimation(R.raw.camera)
                    .setMessage("¡Cambia o agrega una fotografía!")
                    .setCancelable(true)
                    .setPositiveButton("Galería", new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            // Delete Operation
                            buscarEnGaleria();
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("Tomar Foto", new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            tomarFoto();
                            dialogInterface.dismiss();
                        }
                    })
                    .build();
            mDialog.show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            foto_repa.setImageBitmap(imageBitmap);

        }
        if(requestCode == SEARCH_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri filePath = data.getData();//obtiene dirección del archivo
            try
            {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath); //hace un mapa de bits de la imagen
                foto_repa.destroyDrawingCache();
                foto_repa.setImageBitmap(imageBitmap);

            }
            catch (IOException e)//captura excepción de conversión a bitmap
            {
                e.printStackTrace();
            }
        }
        else
        {
            //no se seleccionó una foto, pueden poner aquí lo que les de la gana
        }
    }
}
