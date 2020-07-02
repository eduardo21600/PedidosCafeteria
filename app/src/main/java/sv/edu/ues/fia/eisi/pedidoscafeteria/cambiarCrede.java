package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class cambiarCrede extends AppCompatActivity {

    private static final int SEARCH_IMAGE_REQUEST = 2;
    Button btnGuardar,btnFoto;
    CircleImageView foto;
    EditText nombreU, contra, repeContra, telefono;
    Usuario usuario;
    SharedPreferences sharedPreferences;
    ControladorBD controlador;
    int vigilante=0,cambioU=0;
    String original, nombre;
    ControladorServicios cServicios;
    GestureDetectorCompat gestureDetector;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_crede);
        sharedPreferences = getSharedPreferences("validacion",0);
        nombre =sharedPreferences.getString("nombreUsuario","usuario");
        controlador = new ControladorBD(this);
        nombreU=(EditText)findViewById(R.id.nombreUsuario);
        contra=(EditText)findViewById(R.id.contra);
        repeContra=(EditText)findViewById(R.id.repContra);
        telefono=(EditText)findViewById(R.id.telefono);
        foto = (CircleImageView) findViewById(R.id.fotoPerfil);
        cServicios = new ControladorServicios();
        imageBitmap=null;
        gestureDetector = new GestureDetectorCompat(this, new GestureListener());

        controlador.abrir();
        usuario = controlador.ConsultaUsuario(nombre);

        if(usuario!=null){
            nombreU.setText(usuario.getIdUsuario());
            original = usuario.getIdUsuario();
            contra.setText(usuario.getContrasena());
            repeContra.setText(usuario.getContrasena());
            telefono.setText(usuario.getTeleUsuario());
            String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+nombre+"_usuario.jpg";
            Picasso.get().load(imageUri)
                    .placeholder(R.drawable.user)
                    .error(R.drawable.error)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(foto);
        }

        foto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);

            }
        });

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
                //controlador.abrir();
                cambioU=1;
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
                               //controlador.abrir();
                               List<PedidoRealizado> pedido= new ArrayList<>();
                               boolean tienePedidos=false;
                               pedido = controlador.ConsultaPedidosRealizados();
                               for (int i = 0; i < pedido.size() ; i++) {
                                   if(pedido.get(i).getIdUsuario().equals(original)){
                                       tienePedidos=true;
                                       break;
                                   }
                               }
                               sharedPreferences = getSharedPreferences("validacion",0);
                               String tipo =sharedPreferences.getString("tipoUsuario","");
                               if(tipo.equals("3")){
                                   FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.noPuedeCambiarCredeEnc),
                                           2,FancyToast.ERROR,true).show();
                               }
                               if(tienePedidos&&cambioU==1){
                                   FancyToast.makeText(getApplicationContext(),getResources().getString(R.string.NoPuedeCambiarCrede),
                                           2,FancyToast.ERROR,true).show();
                               }
                               else{
                                   usuario.setIdUsuario(nomU);
                                   usuario.setContrasena(verContra);
                                   usuario.setTeleUsuario(tel);
                                   controlador.actualizarUsuario2(usuario,original);
                                   original = nomU;
                                   SharedPreferences.Editor editor = sharedPreferences.edit();
                                   editor.putString("nombreUsuario",nomU);
                                   editor.apply();
                                   //controlador.cerrar();
                                   cServicios.subirImagen(getApplicationContext(),imageBitmap,"usuario",nombre);
                                   FancyToast.makeText(getApplicationContext(),"Se han guardado los cambios",
                                           2,FancyToast.SUCCESS,true).show();
                               }

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

    static final int REQUEST_TAKE_PHOTO = 1;

    public void tomarFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }

    public void buscarEnGaleria(){
        Intent intent = new Intent(); //crea un nuevo intent
        intent.setType("image/*");    //pone que solo sea para buscar cualquier tipo de imagen
        intent.setAction(Intent.ACTION_GET_CONTENT); // le pone la acción que es, escoger un contenido
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SEARCH_IMAGE_REQUEST);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);

        }
        if(requestCode == SEARCH_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri filePath = data.getData();//obtiene dirección del archivo
            try
            {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath); //hace un mapa de bits de la imagen
                foto.destroyDrawingCache();
                foto.setImageBitmap(imageBitmap);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controlador.cerrar();
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            if (ContextCompat.checkSelfPermission(cambiarCrede.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(cambiarCrede.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(cambiarCrede.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
            }
            final MaterialDialog mDialog = new MaterialDialog.Builder(cambiarCrede.this)
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

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }*/
}

