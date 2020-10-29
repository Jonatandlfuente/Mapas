package com.example.mapas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class GpsService extends Service implements LocationListener {
    private LocationManager mLocManager = null;
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    //lo que hace el servicio cuando llamamos
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startLocation();
        return START_STICKY;
    }

    //Se encarga de proveer, obtiene el contexto de la localizacion de los servicios y si no está habilitado como
    //proveedor GPS
    @SuppressLint("MissingPermission")
    private void startLocation() {
        mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //obtiene la información
            Intent callGPSIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            callGPSIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(callGPSIntent);

        } else {

            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 100, this);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
