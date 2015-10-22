package com.brandonjf.volleycupid;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by brandon on 10/21/15.
 */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    GestureDetector mGestureDetector;

    public interface OnItemDoubleClickListener {
        public void onItemDoubleClick(View view, int position);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
