package com.example.swarnim_d.nearbyrestaurants.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.swarnim_d.nearbyrestaurants.Model.RestResponse;
import com.example.swarnim_d.nearbyrestaurants.R;
import com.example.swarnim_d.nearbyrestaurants.Rest.ApiClient;
import com.example.swarnim_d.nearbyrestaurants.Rest.ApiInterface;
import com.example.swarnim_d.nearbyrestaurants.Utility.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener,GoogleMap.OnInfoWindowClickListener {
    public GoogleMap mMap;
    Double lat;
    Double lon;
    GPSTracker gpsTracker;
    Response<RestResponse> restResponse;
    LatLng latLng;
    ArrayList<RestResponse.Restaurants.RestDetails> restDetailsArrayList;
    String mHotelID;
    RelativeLayout mapActivityRL;
    ProgressDialog pd;

    public boolean checkStoragePerm() {
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
            else {return false;}
        }
        return true;
    }


    public void Setmarker(String hotelname, LatLng hotelLocation, String ACFT) {
        mMap.addMarker(new MarkerOptions().position(hotelLocation).title(hotelname).snippet("Cost For Two : "+ACFT).icon(BitmapDescriptorFactory.fromResource(R.drawable.food)).alpha(0.8f));
    }

    //-----------------------------------------------------API CALL------------------------
    public void callingApiForNearByRestaurants(){
        ApiInterface apiServices = ApiClient.getClient().create(ApiInterface.class);
        Call<RestResponse> call = apiServices.getRestaurants(lat, lon);

        call.enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(Response<RestResponse> response, Retrofit retrofit) {
                //Log.d("Response restaurant at oth position",resName);
                restResponse = response;
                for (int i = 0; i < response.body().getRestaurants().size(); i++) {

                    String mHotelName = response.body().getRestaurants().get(i).getRestDetails().getName();
                    Double mLat = response.body().getRestaurants().get(i).getRestDetails().getLocation().getLatitude();
                    String mLatString = String.valueOf(response.body().getRestaurants().get(i).getRestDetails().getLocation().getLatitude());
                    String mLonString = String.valueOf(response.body().getRestaurants().get(i).getRestDetails().getLocation().getLongitude());
                    Double mLon = response.body().getRestaurants().get(i).getRestDetails().getLocation().getLongitude();
                    String mCostForTwo = String.valueOf(response.body().getRestaurants().get(i).getRestDetails().getAverageCostForTwo());
                    mHotelID = response.body().getRestaurants().get(i).getRestDetails().getHotelID();
                    String mAddress = response.body().getRestaurants().get(i).getRestDetails().getLocation().getAddress();
                    String mLocality = response.body().getRestaurants().get(i).getRestDetails().getLocation().getLocality();
                    String mCuisine = response.body().getRestaurants().get(i).getRestDetails().getCuisines();
                    String mRating = response.body().getRestaurants().get(i).getRestDetails().getUserRating().getRating();
                    String mRatingText = response.body().getRestaurants().get(i).getRestDetails().getUserRating().getRatingText();
                    String mRatingColor = response.body().getRestaurants().get(i).getRestDetails().getUserRating().getRatingColor();
                    String mVotes = String.valueOf(response.body().getRestaurants().get(i).getRestDetails().getUserRating().getVotes());
                    String mImage = response.body().getRestaurants().get(i).getRestDetails().getThumb();
                    Log.d("Names of hotels",mHotelName);
                    //------------------------setting gps location
                    latLng = new LatLng(mLat,mLon);
                    Setmarker(mHotelName,latLng,mCostForTwo);

                    DBHelper db = new DBHelper(getApplicationContext());
                    db.insertRestaurant(mHotelID,mHotelName,mAddress,mLocality,mCuisine,mCostForTwo,mRating,mRatingText,mRatingColor,mLatString,mLonString,mVotes,mImage);
                }
            }
            @Override
            public void onFailure(Throwable t) {Log.e("onFailure",t.toString());}
        });
    }

    //------------------------------onCreate-----------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapActivityRL = (RelativeLayout)findViewById(R.id.activity_map);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading Please Wait");
        pd.show();

        Snackbar snackbar = Snackbar.make(mapActivityRL, "                  Best Hotels Around You               ", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();

        checkStoragePerm();



        //------code for gps location fetching--------------
        gpsTracker = new GPSTracker(this);
        lat = gpsTracker.getLatitude();
        lon = gpsTracker.getLongitude();

        //------Calling onMapReady
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        supportMapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {return;}
        mMap.setMyLocationEnabled(true);
        LatLng latLng = new LatLng(lat,lon);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon) , 15.0f));

        //-------------calling Zomato api geocode----------------------

            callingApiForNearByRestaurants();

        pd.dismiss();

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        Snackbar snackbar = Snackbar
//                .make(mapActivityRL, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
//
//        snackbar.show();

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String mHotelname  = marker.getTitle();
        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
        intent.putExtra("hotelname",mHotelname);
        startActivity(intent);
    }



}
