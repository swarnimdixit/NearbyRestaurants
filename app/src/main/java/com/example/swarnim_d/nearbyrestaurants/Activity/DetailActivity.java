package com.example.swarnim_d.nearbyrestaurants.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.swarnim_d.nearbyrestaurants.R;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    String hotelName;
    ImageView imageViewDetailActivity;
    TextView nameTVDetailActivity,cuisineTVDetailActivity,costForTwoTVDetailActivity,localityTVDetailActivity;
    TextView ratingTextTVDetailActivity,ratingTVDetailActivity,votesTVDetailActivity,addressTVDetailActivity,reviewTVDetailActivity;
    ImageButton imageNavigationIBDetailActivity;
    EditText reviewETDetailFragment;
    Button reviewSubmitBtnDetailFragment;
    LinearLayout reviewLLDetailActivity;
    RelativeLayout activity_detailRL;

    String IMAGE_URL;
    String CUISINE ;
    String COST_FOR_TWO ;
    String RATING ;
    String RATING_TEXT;
    String RATING_COLOR;
    String ADDRESS;
    String LOCALITY;
    String LATITUDE;
    String LONGITUDE;
    String REVIEW ;
    String VOTES;

    //----------------- hide keyboard when editText not in Focus
    public void hideKeyboard(View view) {//hideKeyboard(view)
        InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageViewDetailActivity = (ImageView)findViewById(R.id.imageIVDetailFragment);
        nameTVDetailActivity = (TextView)findViewById(R.id.restaurantTVDetailFrag);
        cuisineTVDetailActivity =  (TextView)findViewById(R.id.cusineTVDetailFrag);
        costForTwoTVDetailActivity = (TextView)findViewById(R.id.costTVDetailFrag);
        localityTVDetailActivity = (TextView)findViewById(R.id.localityTVDetailFrag);
        ratingTVDetailActivity = (TextView)findViewById(R.id.ratingTVOnIVDetailFragment);
        ratingTextTVDetailActivity = (TextView)findViewById(R.id.ratingTextTVDetailFrag);
        votesTVDetailActivity = (TextView)findViewById(R.id.votesTVDetailFrag);
        addressTVDetailActivity =(TextView)findViewById(R.id.addressTVDetailFragment);
        imageNavigationIBDetailActivity = (ImageButton)findViewById(R.id.imageIBDetailFragment);
        reviewETDetailFragment = (EditText)findViewById(R.id.reviewETDetailFragment) ;
        reviewSubmitBtnDetailFragment = (Button)findViewById(R.id.reviewSubmitBtnDetailFragment);
        reviewLLDetailActivity = (LinearLayout)findViewById(R.id.reviewLLDetailFragment);
        reviewTVDetailActivity = (TextView)findViewById(R.id.reviewTVDetailFragment);
        activity_detailRL = (RelativeLayout)findViewById(R.id.activity_detail);
        reviewLLDetailActivity.setVisibility(View.VISIBLE);

            //----------------- receiving hotelid from map activity------------
       hotelName =getIntent().getStringExtra("hotelname");

        //---------------------------------fetching data from sqlite
        DBHelper db = new DBHelper(this);
        Cursor rs = db.getData(hotelName);
        rs.moveToFirst();
        if (rs!= null){
            String HOTEL_NAME = rs.getString(rs.getColumnIndex("name"));

            nameTVDetailActivity.setText(HOTEL_NAME);

            IMAGE_URL = rs.getString(rs.getColumnIndex("thumbnail"));

            CUISINE =rs.getString(rs.getColumnIndex("cuisine"));
            cuisineTVDetailActivity.setText(CUISINE);

            COST_FOR_TWO =rs.getString(rs.getColumnIndex("costfortwo"));
            costForTwoTVDetailActivity.setText(COST_FOR_TWO);

            RATING =rs.getString(rs.getColumnIndex("rating"));
            ratingTVDetailActivity.setText(RATING);

            RATING_TEXT =rs.getString(rs.getColumnIndex("ratingtext"));
            ratingTextTVDetailActivity.setText(RATING_TEXT);

            RATING_COLOR = rs.getString(rs.getColumnIndex("ratingcolor"));
            ratingTextTVDetailActivity.setBackgroundColor(Color.parseColor("#"+RATING_COLOR));

            ADDRESS = rs.getString(rs.getColumnIndex("address"));
            addressTVDetailActivity.setText("Address : "+ADDRESS);

            LOCALITY = rs.getString(rs.getColumnIndex("locality"));
            localityTVDetailActivity.setText(LOCALITY);

            LATITUDE = rs.getString(rs.getColumnIndex("latitude"));
            LONGITUDE = rs.getString(rs.getColumnIndex("longitude"));

            REVIEW = rs.getString(rs.getColumnIndex("review"));


            VOTES = rs.getString(rs.getColumnIndex("votes"));
            votesTVDetailActivity.setText(VOTES);

        }


        imageNavigationIBDetailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f(%s)",Double.parseDouble(LATITUDE), Double.parseDouble(LONGITUDE),"");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });


        //----------------setting image from internet (URL)--------------------
        Picasso.with(DetailActivity.this)
                .load(IMAGE_URL)
                .placeholder(R.drawable.default_image)
                .into(imageViewDetailActivity);

        if(REVIEW != null) {
            reviewTVDetailActivity.setText(REVIEW);
            reviewLLDetailActivity.setVisibility(View.INVISIBLE);
        }



        reviewSubmitBtnDetailFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String review =  reviewETDetailFragment.getText().toString();

                DBHelper db = new DBHelper(getApplicationContext());
                db.insertReview(review,hotelName);
                reviewLLDetailActivity.setVisibility(View.INVISIBLE);

            }
        });

        activity_detailRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

    }
}
