package com.brandonjf.volleycupid;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.brandonjf.volleycupid.okclasses.QuickmatchMatch;

/**
 * Created by brandon on 10/20/15.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private QuickmatchMatch mMatch;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    public CustomPagerAdapter(Context context, QuickmatchMatch match) {
        mContext = context;
        mMatch = match;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.photo_page, container, false);
        NetworkImageView pageUserPhoto = (NetworkImageView) layout.findViewById(R.id.page_user_image);
        TextView username = (TextView) layout.findViewById(R.id.tv_username);
        TextView information = (TextView) layout.findViewById(R.id.tv_age);
        username.setText(mMatch.getUsername());
        information.setText("Age " +  mMatch.getUserinfo().getAge().toString());
        pageUserPhoto.setImageUrl(mMatch.getThumbs().get(position).get400x400(), ApplicationController.getInstance().getmImageLoader());
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mMatch.getThumbs().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
