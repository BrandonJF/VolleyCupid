package com.brandonjf.volleycupid;

import com.brandonjf.volleycupid.okclasses.AuthResponse;
import com.brandonjf.volleycupid.okclasses.QuickmatchMatch;

import java.util.ArrayList;

/**
 * Created by brandon on 10/1/15.
 */
public interface OkResponseInterface {

    void onQuickmatchQueueListener(ArrayList<QuickmatchMatch> quickmatchMatches);
    void onAccessTokenReceivedListener(AuthResponse authResponse);
}
