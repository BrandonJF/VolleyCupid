package com.brandonjf.volleycupid;

import com.brandonjf.volleycupid.okclasses.QuickmatchMatch;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by brandon on 10/1/15.
 */
public interface OkResponseInterface {

    void onQuickmatchQueueListener(ArrayList<QuickmatchMatch> quickmatchMatches);
    void onAccessTokenReceivedListener(Map tokenObject);
}
