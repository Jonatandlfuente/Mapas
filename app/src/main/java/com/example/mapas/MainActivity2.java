package com.example.mapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    GeoPoint geoPointMyPosition;
    private MapView mMapView;
    private MapController mMapController;
    private ArrayList<OverlayItem> mOverlayItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Shared preferences para los favoritos
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_main2);

        Intent getDataIntent = getIntent();
        geoPointMyPosition = new GeoPoint(getDataIntent.getDoubleExtra(LATITUDE, 0).getData)
    }

    public void generateOpenStreetMaoViewAndMapController(){
        mMapView=(MapView) findViewById(R.id.openmapview);
        //desplazamiento
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapController=(MapController) mMapView.getController();
        mMapController.setZoom(18);
        mMapController.setCenter(geoPointMyPosition);

    }
}