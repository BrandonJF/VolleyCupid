package com.brandonjf.volleycupid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.brandonjf.volleycupid.okclasses.AuthResponse;
import com.brandonjf.volleycupid.okclasses.OkRecyclerViewAdapter;
import com.brandonjf.volleycupid.okclasses.QuickmatchMatch;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity implements OkResponseInterface{
    RecyclerView recyclerView;
    OkRecyclerViewAdapter rvAdapter;
    Snackbar alertBar;
    SharedPreferences settings;
    String token;
    public static final String PREFS_NAME = "VolleyCupidPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        //Find the elements on the page
        handlePageSetup();
        loadSharedPreferences();
        handleToken();
        //Get the data from the OkCupid servers
        //loadAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri uri = intent.getData();
        if (uri != null){
           Intent test = intent;
         }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuickmatchQueueListener(ArrayList <QuickmatchMatch> quickmatchMatches) {
        if (rvAdapter == null){
            rvAdapter = new OkRecyclerViewAdapter(quickmatchMatches);
            recyclerView.setAdapter(rvAdapter);
            dismissAlert();
        } else{
            int scrollToPos = rvAdapter.addItems(quickmatchMatches);
            recyclerView.smoothScrollToPosition(scrollToPos);
            dismissAlertPositively("Done");
        }
    }

    @Override
    public void onAccessTokenReceivedListener(AuthResponse authResponse) {
        settings.edit()
                .putString("accessToken", authResponse.access_token)
                .putString("refreshToken", authResponse.refresh_token)
                .putString("expiresAt", authResponse.getExpirationTime())
                .apply();
        loadQuickmatchData();
    }

    @Override
    public void onRefreshResponseReceivedListener(AuthResponse authResponse) {
        settings.edit()
                .putString("accessToken", authResponse.access_token)
                .putString("expiresAt", authResponse.getExpirationTime())
                .apply();
        loadQuickmatchData();
    }

    public void loadSharedPreferences(){
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    public void handlePageSetup() {
       setupRecyclerView();
        alertBar = Snackbar.make(findViewById(R.id.cl_browseCoordinator),"VolleyCupid", Snackbar.LENGTH_SHORT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllData();
            }
        });
    }

    public void setupRecyclerView(){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rv_matchList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //This is what handles the infinite scrolling. Easy peasey.
            int visibleItemPosition, visibleItemCount, totalItemCount;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                visibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (visibleItemPosition == totalItemCount - 1){
                    alertBar.setText("Loading more matches...").setDuration(Snackbar.LENGTH_SHORT).show();
                    loadQuickmatchData();
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener());
    }
    public void handleToken(){
        Uri tokenUri = getIntent().getData();
        //If this isn't coming back from a browser, make it start that auth.
        if (tokenUri == null){
            String savedToken = settings.getString("accessToken", null);
            if (savedToken == null){
                OkAuthLib.getInstance(this).init(getApplicationContext()).startAuthentication();
            } else{
                //if the token is past expiration - refresh it.
                if (AuthResponse.isPastExpiration(settings.getString("expiresAt", "1"))){
                    OkAuthLib.getInstance(this).init(getApplicationContext()).setAccessTokenfromRefreshToken(settings.getString("refreshToken", null));
                } else{
                    OkAuthLib.getInstance(this).setAccessToken(savedToken);
                    loadQuickmatchData();
                }


            }
        } else {
            //if coming back from the browser, read the code.
            String token = tokenUri.getQueryParameter("code");
            OkAuthLib.getInstance(this).init(getApplicationContext()).setAuthorizationCode(token);
        }

    }
    public void loadAllData(){
        loadQuickmatchData();
    }

    public void loadQuickmatchData(){
        alertUser("Loading matches...", Snackbar.LENGTH_INDEFINITE);
        QuickmatchLib.getInstance(this).getQueue(getApplicationContext());
    }

    public void dismissAlert(){
        if (alertBar.isShown()){
            alertBar.dismiss();
        }
    }

    public void dismissAlertPositively(String message){
        if (alertBar.isShown()){
            alertBar.setText(message).setDuration(Snackbar.LENGTH_SHORT).show();
        }
    }
    public void alertUser(String message){
        if (alertBar.isShown()){
            alertBar.dismiss();
        }
        if (!alertBar.isShown()){
            alertBar.setText(message).setDuration(Snackbar.LENGTH_SHORT).show();
        }
    }

    public void alertUser(String message, int duration){
        if (alertBar.isShown()){
            alertBar.dismiss();
        }
        if (!alertBar.isShown()){
            alertBar.setText(message).setDuration(duration).show();
        }
    }

}
