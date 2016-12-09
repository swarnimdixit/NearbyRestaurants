package com.example.swarnim_d.nearbyrestaurants.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by swarnim_d on 06-12-2016.
 */

public class RestResponse {
    @SerializedName("nearby_restaurants")
    private List<Restaurants> restaurants;

    public List<Restaurants> getRestaurants() {return restaurants;}
    public void setRestaurants(List<Restaurants> restaurants) {this.restaurants = restaurants;}
//---------------------------------------------------------------------------------------------------
    public class Restaurants implements Serializable {

        @SerializedName("restaurant")
        private RestDetails restDetails;

        public RestDetails getRestDetails() {return restDetails;}

        public void setRestDetails(RestDetails restDetails) {this.restDetails = restDetails;}

//------------------------------------------------------------------------------------------------------
        public class RestDetails implements Serializable {

            @SerializedName("id")
            private String hotelID;

            @SerializedName("name")
            private String name;

            @SerializedName("cuisines")
            private String cuisines;

            @SerializedName("thumb")
            private String thumb;

            @SerializedName("average_cost_for_two")
            private  int averageCostForTwo;

            @SerializedName("location")
            private Locations location;

            @SerializedName("user_rating")
            private  UserRating userRating;

            public String getHotelID() {return hotelID;}

            public void setHotelID(String hotelID) {this.hotelID = hotelID;}

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCuisines() {
                return cuisines;
            }

            public void setCuisines(String cuisines) {
                this.cuisines = cuisines;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public int getAverageCostForTwo() {
                return averageCostForTwo;
            }

            public UserRating getUserRating() {
                return userRating;
            }

            public void setUserRating(UserRating userRating) {
                this.userRating = userRating;
            }

            public void setAverageCostForTwo(int averageCostForTwo) {
                this.averageCostForTwo = averageCostForTwo;

            }
//-----------------------------------------------------------------------------------------------
            public Locations getLocation() {
                return location;
            }

            public void setLocation(Locations location) {
                this.location = location;
            }

            public class Locations implements Serializable{

                @SerializedName("address")
                private String address;

                @SerializedName("locality")
                private String locality;

                @SerializedName("latitude")
                private Double latitude;

                @SerializedName("longitude")
                private Double longitude;


                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getLocality() {
                    return locality;
                }

                public void setLocality(String locality) {
                    this.locality = locality;
                }

                public Double getLatitude() {
                    return latitude;
                }

                public void setLatitude(Double latitude) {
                    this.latitude = latitude;
                }

                public Double getLongitude() {
                    return longitude;
                }

                public void setLongitude(Double longitude) {
                    this.longitude = longitude;
                }
            }

        }

    }

}
