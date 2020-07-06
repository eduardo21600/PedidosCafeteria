package sv.edu.ues.fia.eisi.pedidoscafeteria;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitud,longitud;
    Intent intent;
    private FloatingActionButton fab;
    android.location.Address address;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent =getIntent();
        latitud = intent.getDoubleExtra("latitud",13.716223);
        longitud = intent.getDoubleExtra("longitud",-89.203501);
        geocoder = new Geocoder(getApplicationContext());
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra("nuevaLatitud",address.getLatitude());
                result.putExtra("nuevaLongitud",address.getLongitude());
                setResult(Activity.RESULT_OK,result);
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            address = geocoder.getFromLocation(latitud, longitud, 1).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng sydney = new LatLng(latitud, longitud);
        float zoomLevel = 15.0f;
        mMap.addMarker(new MarkerOptions().position(sydney).draggable(true).title("Marcador en posición actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoomLevel));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng latLng = marker.getPosition();
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
