package com.example.swarnim_d.nearbyrestaurants.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swarnim_d on 14-12-2016.
 */

public class RestAndUserReview {

    @SerializedName("phone_numbers")
    String phoneNo;
    @SerializedName("all_reviews")
    List<AllReviews> allReviews;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<AllReviews> getAllReviews() {
        return allReviews;
    }

    public void setAllReviews(List<AllReviews> allReviews) {
        this.allReviews = allReviews;
    }


    public class AllReviews{

        @SerializedName("rating")
        String uRating;

        @SerializedName("review_text")
        String uReviewText;

        @SerializedName("review_time_friendly")
        String uReviewTimeAgo;

        @SerializedName("user")
        UserDetails userDetails;

        public String getuRating() {
            return uRating;
        }

        public void setuRating(String uRating) {
            this.uRating = uRating;
        }

        public String getuReviewText() {
            return uReviewText;
        }

        public void setuReviewText(String uReviewText) {
            this.uReviewText = uReviewText;
        }

        public String getuReviewTimeAgo() {
            return uReviewTimeAgo;
        }

        public void setuReviewTimeAgo(String uReviewTimeAgo) {
            this.uReviewTimeAgo = uReviewTimeAgo;
        }

        public UserDetails getUserDetails() {
            return userDetails;
        }

        public void setUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
        }


        public class UserDetails{
            @SerializedName("name")
            String name;

            @SerializedName("foodie_level")
            String foddieLevel;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFoddieLevel() {
                return foddieLevel;
            }

            public void setFoddieLevel(String foddieLevel) {
                this.foddieLevel = foddieLevel;
            }
        }
    }
}
