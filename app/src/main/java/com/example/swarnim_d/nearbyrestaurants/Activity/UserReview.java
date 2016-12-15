package com.example.swarnim_d.nearbyrestaurants.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.example.swarnim_d.nearbyrestaurants.Model.RestAndUserReview;
import com.example.swarnim_d.nearbyrestaurants.Model.User;
import com.example.swarnim_d.nearbyrestaurants.R;
import com.example.swarnim_d.nearbyrestaurants.Rest.ApiClient;
import com.example.swarnim_d.nearbyrestaurants.Rest.ApiInterface;
import com.example.swarnim_d.nearbyrestaurants.Utility.RecyclerViewAdapter;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UserReview extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> arrayListUserModel = new ArrayList<>();
    int restID = 16774318;//get this from intentextras
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        restID =getIntent().getIntExtra("hotelid",16774318);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ApiInterface apiServices = ApiClient.getClient().create(ApiInterface.class);
        Call<RestAndUserReview> call = apiServices.getAllTheUserReviews(restID);

        call.enqueue(new Callback<RestAndUserReview>() {
            @Override
            public void onResponse(Response<RestAndUserReview> response, Retrofit retrofit) {
                if(response.body().getAllReviews() != null) {
                    for (int i = 0; i < response.body().getAllReviews().size(); i++) {
                        User user = new User();
                        user.setName(response.body().getAllReviews().get(i).getUserDetails().getName());
                        user.setFoodieLevel(response.body().getAllReviews().get(i).getUserDetails().getFoddieLevel());
                        user.setReview(response.body().getAllReviews().get(i).getuReviewText());
                        user.setTimeAgo(response.body().getAllReviews().get(i).getuReviewTimeAgo());
                        arrayListUserModel.add(user);
                    }
                }else{
                    Toast.makeText(UserReview.this, "No Reviews Found", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });





        mAdapter = new RecyclerViewAdapter(arrayListUserModel);
        mRecyclerView.setAdapter(mAdapter);

    }
}
