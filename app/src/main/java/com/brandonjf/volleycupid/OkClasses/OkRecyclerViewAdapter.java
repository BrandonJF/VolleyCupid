package com.brandonjf.volleycupid.okclasses;

import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.brandonjf.volleycupid.CustomPagerAdapter;
import com.brandonjf.volleycupid.R;
import com.brandonjf.volleycupid.TypefaceUtils;
import com.brandonjf.volleycupid.ZoomOutPageTransformer;

import java.util.ArrayList;

/**
 * Created by brandon on 10/8/15.
 */
public class OkRecyclerViewAdapter extends RecyclerView.Adapter<OkRecyclerViewAdapter.MatchViewHolder> {
    ArrayList<QuickmatchMatch> mMatches;

    public OkRecyclerViewAdapter(ArrayList<QuickmatchMatch> quickmatchMatches) {
        this.mMatches = quickmatchMatches;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        MatchViewHolder matchViewHolder = new MatchViewHolder(view);
        return matchViewHolder;
    }

    @Override
    public void onBindViewHolder(MatchViewHolder matchViewHolder, int position) {
        QuickmatchMatch match = mMatches.get(position);
        matchViewHolder.username.setText(match.getUsername());
        matchViewHolder.age.setText("Age " +  match.getUserinfo().getAge().toString());
        matchViewHolder.username.setTypeface(matchViewHolder.typefaceRegular);
        matchViewHolder.age.setTypeface(matchViewHolder.typefaceRegular);
        matchViewHolder.viewPager.setAdapter(new CustomPagerAdapter(matchViewHolder.viewPager.getContext().getApplicationContext(), match));
        matchViewHolder.viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    public int addItems(ArrayList<QuickmatchMatch> quickmatchMatches){
        int currentLastItem = this.getItemCount();
        mMatches.addAll(quickmatchMatches);
        this.notifyDataSetChanged();
        return currentLastItem + 1;
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView username, age;
        NetworkImageView userPhoto;
        ViewPager viewPager;
        Typeface typefaceRegular;
        Typeface typefaceLight;

        MatchViewHolder(View matchView) {
            super(matchView);
            cardView = (CardView) matchView.findViewById(R.id.matchCardView);
            username = (TextView) matchView.findViewById(R.id.tv_username);
            age = (TextView) matchView.findViewById(R.id.tv_age);
//            userPhoto = (NetworkImageView) matchView.findViewById(R.id.user_image);
            viewPager = (ViewPager) matchView.findViewById(R.id.viewPager);
            typefaceRegular = TypefaceUtils.get(matchView.getContext(), "Lato-Regular.ttf");
            typefaceLight= TypefaceUtils.get(matchView.getContext(), "Lato-Light.ttf");
//            viewPager.setAdapter(new CustomPagerAdapter(viewPager.getContext().getApplicationContext()));



        }
    }


}
