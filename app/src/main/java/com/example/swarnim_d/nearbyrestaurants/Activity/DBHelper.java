package com.example.swarnim_d.nearbyrestaurants.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "restaurantdatabasev3.db";
    public static final String TABLE_NAME = "restaurantsv4";
    public static final String HOTEL_ID = "hotelid";
    public static final String HOTEL_NAME = "name";
    public static final String CUISINE = "cuisine";
    public static final String COST_FOR_TWO = "costfortwo";
    public static final String RATING = "rating";
    public static final String RATING_TEXT = "ratingtext";
    public static final String RATING_COLOR = "ratingcolor";
    public static final String ADDRESS = "address";
    public static final String LOCALITY = "locality";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String REVIEW = "review";
    public static final String VOTES = "votes";
    public static final String IMAGE = "thumbnail";

    Context mcontext;
    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
            mcontext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE "
                +TABLE_NAME+"("
                +HOTEL_ID+" text PRIMARY KEY,"
                +HOTEL_NAME+" text,"
                +ADDRESS+" text,"
                +LOCALITY+" text,"
                +CUISINE+" text,"
                +COST_FOR_TWO+" text,"
                +RATING+" text,"
                +RATING_TEXT+" text,"
                +RATING_COLOR+" text,"
                +REVIEW+" text,"
                +LATITUDE+" text,"
                +LONGITUDE+" text,"
                +VOTES+" text,"
                +IMAGE+" text)");
        //db.execSQL("create table " +TABLE_NAME+"(hotelid text PRIMARY KEY,name text,address text,locality text,cuisine text,costfortwo text,rating text,ratingtext text,ratingcolor text,review text,latitude text,longitude text,votes text,thumbnail text");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertRestaurant (String mHOTEL_ID, String mHOTEL_NAME, String mADDRESS, String mLOCALITY,
                                     String mCUISINE, String mCOST_FOR_TWO,String mRATING,String mRATING_TEXT,String mRATING_COLOR
                                     ,String mLATITUDE,String mLONGITUDE,String mVOTES,String mIMAGE) {

        SQLiteDatabase db = this.getWritableDatabase();
       try {

        ContentValues contentValues = new ContentValues();
        contentValues.put("hotelid",mHOTEL_ID);
           contentValues.put("name",mHOTEL_NAME);
           contentValues.put("cuisine",mCUISINE);
           contentValues.put("costfortwo",mCOST_FOR_TWO);
           contentValues.put("rating",mRATING);
           contentValues.put("ratingtext",mRATING_TEXT);
           contentValues.put("ratingcolor",mRATING_COLOR);
           contentValues.put("address",mADDRESS);
           contentValues.put("locality",mLOCALITY);
           contentValues.put("latitude",mLATITUDE);
           contentValues.put("longitude",mLONGITUDE);
           contentValues.put("votes",mVOTES);
           contentValues.put("thumbnail",mIMAGE);

           db.insert(TABLE_NAME, null, contentValues);

       }
       catch (Exception e)
       {e.printStackTrace();Toast.makeText(mcontext, "exceptionn"+e, Toast.LENGTH_SHORT).show();}
        return true;
    }


    public void insertReview(String mReview,String mHotelName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("review",mReview);
        db.update(TABLE_NAME, contentValues, String.format("%s = ?", "name"),
                new String[]{mHotelName});
    }



    public Cursor getData(String mHotelName) {
        Cursor res = null;
        SQLiteDatabase db = this.getReadableDatabase();
        res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+HOTEL_NAME+"=?", new String[] {mHotelName + ""});
        //res =  db.rawQuery( "select * from movietbl where title = \""+mtitle+"\"", null );
        //Cursor res = db.query(TABLE_NAME, new String[] { OVERVIEW }, MOVIE_TITLE + "=?", new String[] { String.valueOf(MOVIE_TITLE) }, null, null, null, null);

        return res;
    }

}
