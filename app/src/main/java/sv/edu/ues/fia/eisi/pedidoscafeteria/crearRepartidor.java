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
import android.media.AudioManager;
import android.media.SoundPool;
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

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class crearRepartidor extends AppCompatActivity {
    private EditText etNomRepa, apeRepa, usuarioRepa,contraRepa, repeConRepa, teleRepa;
    private Button btnCrearRepa;
    private ControladorBD controladorBD;
    Usuario nuevoRepa;
    CircleImageView foto_repa;
    GestureDetectorCompat gestureDetector;
    Bitmap imageBitmap;
    SoundPool sp;
    int sonido;
    ControladorServicios cServicios;

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
        foto_repa = (CircleImageView)findViewById(R.id.iv_foto_repa);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido = sp.load(getApplicationContext(),R.raw.audio_success,1);
        cServicios = new ControladorServicios();
        nuevoRepa = new Usuario();
        controladorBD = new ControladorBD(getApplicationContext());
        imageBitmap=null;
        gestureDetector = new GestureDetectorCompat(this, new GestureListener());
        controladorBD.abrir();
        foto_repa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);

            }
        });
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
                             cServicios.subirImagen(getApplicationContext(),imageBitmap,"usuario",nuevoRepa.getIdUsuario());
                             sp.play(sonido,1,1,1,0,0);
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
        if(etNomRepa.getText().toString().isEmpty()||apeRepa.getText().toString().isEmpty()||
            usuarioRepa.getText().toString().isEmpty()||contraRepa.getText().toString().isEmpty()||
            contraRepa.getText().toString().isEmpty()||repeConRepa.getText().toString().isEmpty()||
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
            if (ContextCompat.checkSelfPermission(crearRepartidor.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(crearRepartidor.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(crearRepartidor.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
            }
            final MaterialDialog mDialog = new MaterialDialog.Builder(crearRepartidor.this)
                    .setTitle("Agregar foto de perfil")
                    .setAnimation(R.raw.camera)
                    .setMessage("¡Agrega una fotografía!")
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
