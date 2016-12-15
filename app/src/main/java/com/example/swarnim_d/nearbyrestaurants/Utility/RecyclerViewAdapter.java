package com.example.swarnim_d.nearbyrestaurants.Utility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swarnim_d.nearbyrestaurants.Model.User;
import com.example.swarnim_d.nearbyrestaurants.R;

import java.util.ArrayList;

/**
 * Created by swarnim_d on 01-12-2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<User> mDataset;

    public RecyclerViewAdapter(ArrayList<User> mDataset) {
        this.mDataset = mDataset;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCard;
        public TextView mUsersName,mReview,mFoodieLevel,mTimeStamp;

        public ViewHolder(CardView cardView) {
            super(cardView);
            mCard = cardView;

            mUsersName = (TextView) mCard.findViewById(R.id.nameOfUserItemCardView);
            mReview = (TextView) mCard.findViewById(R.id.reviewItemCardView);
            mFoodieLevel = (TextView) mCard.findViewById(R.id.foodieLevelItemCardView);
            mTimeStamp = (TextView) mCard.findViewById(R.id.timeStampItemCardView);
        }
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder((CardView) v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = mDataset.get(holder.getAdapterPosition());
        holder.mUsersName.setText(user.getName());
        holder.mReview.setText(user.getReview());
        holder.mFoodieLevel.setText(user.getFoodieLevel());
        holder.mTimeStamp.setText(user.getTimeAgo());
    }



    @Override
    public int getItemCount() {
            return mDataset.size();
    }
}
