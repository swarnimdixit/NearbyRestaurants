package com.example.swarnim_d.nearbyrestaurants.Rest;

import com.example.swarnim_d.nearbyrestaurants.Model.RestAndUserReview;
import com.example.swarnim_d.nearbyrestaurants.Model.RestResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
/**
 * Created by swarnim_d on 06-12-2016.
 */

public interface ApiInterface {

    @Headers("user-key: 0abef42aab6571335374d24b2a1df6c1")
    @GET("/api/v2.1/geocode")
    Call<RestResponse> getRestaurants(@Query("lat") Double lat, @Query("lon") Double lon)  ;


    @Headers("user-key: 0abef42aab6571335374d24b2a1df6c1")
    @GET("/api/v2.1/restaurant")
    Call<RestAndUserReview> getAllTheUserReviews(@Query("res_id") Integer restID)  ;

}

