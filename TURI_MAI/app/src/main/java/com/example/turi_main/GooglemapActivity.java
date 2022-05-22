package com.example.turi_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GooglemapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int REQUEST_CODE_PREMISSUINS = 1000;
    Button GPSBTN;
    private static final String TAG ="googlemap" ;
    private GoogleMap googleMap;
    private EditText edGsearch;
    private  static final float DEFAULT_ZOOM = 15;
    public static Context moveContext;
    public static Context geoContext;
    private FusedLocationProviderClient GPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        edGsearch = (EditText) findViewById(R.id.geosearch);
        Intent SendIntent = getIntent();
        String sMySearch = SendIntent.getStringExtra("sSearch");
        edGsearch.setText(sMySearch);
        GPS = LocationServices.getFusedLocationProviderClient(this);
        GPSBTN = findViewById(R.id.GPS);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;



        LatLng latLng = new LatLng(37.403680, 126.930373);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("대림대학교");
        googleMap.addMarker(markerOptions);
        init();

        UiSettings mapUiSettings = googleMap.getUiSettings();
        mapUiSettings.setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }




    }
private void init (){
        Log.d(TAG,"init : initializing ");

    edGsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER

                        ){

                    geoLocate();

                    }
                return false;
            }
        });
}


        /*public void movecamera(LatLng latLng, float zoom,String addressLine,String title) {
        Log.d(TAG,"moveCameara: moving the camera to : lat:" + latLng.latitude + ", lng :" + latLng.longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        MarkerOptions options = new MarkerOptions()
        .position(latLng)
        .title(title);
        googleMap.addMarker(options);*/

    public void    geoLocate(){
        Log.d(TAG,"geoLocate: geolocating");
        String seachString = edGsearch.getText().toString();
        Geocoder geocoder = new Geocoder(GooglemapActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(seachString,1);
        } catch (IOException e) {
            Log.e(TAG,"geolocate: IOException:" + e.getMessage());
        }
        if(list.size() > 0) {
            Address address = list.get(0);
            Log.d(TAG, "geoLocate: found a location:" + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),DEFAULT_ZOOM,address.getAddressLine(0));
geoContext=this;
}

    }

    public void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG,"moveCameara: moving the camera to : lat:" + latLng.latitude + ", lng :" + latLng.longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title(title);
        googleMap.addMarker(options);
        moveContext=this;
    }



    public void onLastLocationButtonClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PREMISSUINS);
            return;
        }
        GPS.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions()
                                    .position(myLocation)
                                    .title("내 위치"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                        }
                    }

                }

        );
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PREMISSUINS:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 거부", Toast.LENGTH_SHORT).show();
                }
        }
    }




}
