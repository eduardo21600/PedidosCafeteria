package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.LocalEncar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Local;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;
import sv.edu.ues.fia.eisi.pedidoscafeteria.cambiarCrede;


public class LocalEncargado extends Fragment {

    public LocalEncargado() {
        // Required empty public constructor
    }
    
    Button habilitar,actualizar;
    EditText etNomLocal;
    View root;
    ControladorBD controladorBD;
    List<Local> local;
    Local nLo;
    SharedPreferences sharedPreferences;
    GestureDetectorCompat gestureDetector;
    Bitmap imageBitmap;
    ImageView foto;
    ControladorServicios cServicios;
    boolean cambiosActivado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_local_encargado, container, false);
        controladorBD = new ControladorBD(getContext());
        sharedPreferences = this.getActivity().getSharedPreferences("validacion",0);
        String idUsuario = sharedPreferences.getString("nombreUsuario","");
        habilitar = (Button)root.findViewById(R.id.btnActivar);
        actualizar = (Button)root.findViewById(R.id.btnGuardarNom);
        etNomLocal = (EditText)root.findViewById(R.id.editText);
        foto = (ImageView)root.findViewById(R.id.imLocalEncargado);
        cambiosActivado=false;
        cServicios = new ControladorServicios();
        imageBitmap=null;
        gestureDetector = new GestureDetectorCompat(getContext(), new GestureListener());

        controladorBD.abrir();
        local = controladorBD.ConsultaLocales();
        for (int i = 0; i <local.size() ; i++) {
            if(local.get(i).getIdUsuario().equals(idUsuario)){
                etNomLocal.setText(local.get(i).getNombreLocal());
                nLo = local.get(i);
                break;
            }
        }

        String imageUri = "https://dv17003servicios.000webhostapp.com/uploads/"+nLo.getIdLocal()+"_local.jpg";
        Picasso.get().load(imageUri)
                .placeholder(R.drawable.shopper)
                .error(R.drawable.error)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(foto);

        habilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiosActivado=true;
                etNomLocal.setEnabled(true);
                actualizar.setVisibility(View.VISIBLE);
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controladorBD.abrir();
                if(!(etNomLocal.getText().toString().isEmpty())){
                    cServicios.subirImagen(getContext(),imageBitmap,"local",String.valueOf(nLo.getIdLocal()));
                    nLo.setNombreLocal(etNomLocal.getText().toString());
                    controladorBD.ActualizarLocal(nLo);
                    FancyToast.makeText(getContext(),getResources().getString(R.string.localActualizado),
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }
                else{
                    FancyToast.makeText(getContext(),getResources().getString(R.string.camposVacios),
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }
            }
        });

        foto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);

            }
        });
        return root;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
            }
            if(cambiosActivado){
                final MaterialDialog mDialog = new MaterialDialog.Builder(getActivity())
                        .setTitle("Cambiar foto del local")
                        .setAnimation(R.raw.camera)
                        .setMessage("¡Cambia o agrega una fotografía para tu local!")
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
            else{
                FancyToast.makeText(getContext(),"Active el modo edición",FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
            }
        }
    }

    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int SEARCH_IMAGE_REQUEST = 2;

    public void tomarFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
    }

    public void buscarEnGaleria(){
        Intent intent = new Intent(); //crea un nuevo intent
        intent.setType("image/*");    //pone que solo sea para buscar cualquier tipo de imagen
        intent.setAction(Intent.ACTION_GET_CONTENT); // le pone la acción que es, escoger un contenido
        startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SEARCH_IMAGE_REQUEST);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);

        }
        if(requestCode == SEARCH_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null)
        {
            Uri filePath = data.getData();//obtiene dirección del archivo
            try
            {
                imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath); //hace un mapa de bits de la imagen
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
}
