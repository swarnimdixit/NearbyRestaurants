package com.example.swarnim_d.nearbyrestaurants.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by swarnim_d on 07-12-2016.
 */

public class UserRating {

                @SerializedName("aggregate_rating")
                private String rating;
                @SerializedName("rating_text")
                private  String ratingText;
                @SerializedName("rating_color")
                private String ratingColor;

                @SerializedName("votes")
                private  int votes;

                public String getRating() {
                    return rating;
                }

                public void setRating(String rating) {
                    this.rating = rating;
                }

                public String getRatingText() {
                    return ratingText;
                }

                public void setRatingText(String ratingText) {
                    this.ratingText = ratingText;
                }

                public String getRatingColor() {
                    return ratingColor;
                }

                public void setRatingColor(String ratingColor) {
                    this.ratingColor = ratingColor;
                }

                public int getVotes() {return votes;}

                public void setVotes(int votes) {this.votes = votes;}
}
