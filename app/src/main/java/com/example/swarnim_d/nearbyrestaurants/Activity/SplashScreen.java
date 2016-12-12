package com.example.swarnim_d.nearbyrestaurants.Activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.swarnim_d.nearbyrestaurants.R;

public class SplashScreen extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final long SPLASH_TIME_OUT = 3000;
    boolean enable;



    //-------------------------------------------check Location permmision --------for marshmallow
    private boolean checkLocationPerm() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION) ;
                //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

            }else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},SplashScreen.PERMISSION_REQUEST_CODE);
                return false;
            }
        }
        return true;
    }




    //-----------------------------------check if gps is ON or OFF---------------
    private void checkGPSEnabled() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        enable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enable) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("GPS Settings");
            builder.setMessage("GPS is not enabled. Please enable GPS");
            builder.setInverseBackgroundForced(true);
            builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            AlertDialog alert = builder.create();
            alert.setCanceledOnTouchOutside(false);
            alert.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkLocationPerm();
                checkGPSEnabled();
                // This method will be executed once the timer is over
                // Start your app main activity
                        Intent intent = new Intent(SplashScreen.this,MapActivity.class);
                        startActivity(intent);
                        finish();
            }
        }, SPLASH_TIME_OUT);




    }
}
