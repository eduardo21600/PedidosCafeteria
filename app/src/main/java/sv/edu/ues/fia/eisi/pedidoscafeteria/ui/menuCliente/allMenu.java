package sv.edu.ues.fia.eisi.pedidoscafeteria.ui.menuCliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import sv.edu.ues.fia.eisi.pedidoscafeteria.AdaptadorMenuC;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorBD;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ControladorServicios;
import sv.edu.ues.fia.eisi.pedidoscafeteria.Menu;
import sv.edu.ues.fia.eisi.pedidoscafeteria.ProductoAsignar;
import sv.edu.ues.fia.eisi.pedidoscafeteria.R;

public class allMenu extends AppCompatActivity implements RecognitionListener {

    private List<Menu> listaMenu;
    private List<ProductoAsignar> PS;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    ControladorServicios controladorServicios;
    ControladorBD controladorBD;
    EditText busqueda;
    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;
    ImageButton hablar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_menu);
        controladorBD = new ControladorBD(getApplicationContext());
        controladorBD.abrir();
        listaMenu = controladorBD.ConsultaMenus();
        controladorBD.cerrar();
        recyclerView = (RecyclerView) findViewById(R.id.menu_recycler_todos);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorMenuC(this, listaMenu);
        recyclerView.setAdapter(adapter);
        busqueda = (EditText) findViewById(R.id.buscar_menu_et);
        hablar = (ImageButton) findViewById(R.id.buscar_menu_mic);

        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizer.setRecognitionListener(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);

        busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                controladorBD.abrir();
                String b = busqueda.getText().toString();
                listaMenu = controladorBD.ConsultaMenusNombre(b);
                controladorBD.cerrar();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new AdaptadorMenuC(getApplicationContext(), listaMenu);
                recyclerView.setAdapter(adapter);

            }
        });

        hablar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            }
        });

    }

    @Override
    public void onReadyForSpeech(Bundle params) {
    }

    @Override
    public void onBeginningOfSpeech() {
        FancyToast.makeText(getApplicationContext(), "Diga su búsqueda", FancyToast.LENGTH_SHORT, FancyToast.INFO, R.drawable.microfono, false).show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
    }

    @Override
    public void onError(int error) {
        FancyToast.makeText(getApplicationContext(), "Ha ocurrido un error", FancyToast.LENGTH_SHORT, FancyToast.ERROR, R.drawable.error    , false).show();
    }

    @Override
    public void onResults(Bundle results) {

        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String b = matches.get(0);
        busqueda.setText(b);
        controladorBD.abrir();
        listaMenu = controladorBD.ConsultaMenusNombre(b);
        controladorBD.cerrar();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorMenuC(getApplicationContext(), listaMenu);
        recyclerView.setAdapter(adapter);
        FancyToast.makeText(getApplicationContext(), "Mostrando resultados para: " + b, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, R.drawable.exito, false).show();
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}