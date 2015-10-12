package com.brandonjf.volleycupid;

import android.content.SharedPreferences;
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
        checkToken();
        //Get the data from the OkCupid servers
        loadAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        return true;
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
    public void onQuickmatchQueueListener(ArrayList<QuickmatchMatch> quickmatchMatches) {
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
    public void onAccessTokenReceivedListener(String accessToken) {
        loadQuickmatchData();
    }

    public void loadSharedPreferences(){
        settings = getSharedPreferences(PREFS_NAME, 0);
    }
    public void handlePageSetup() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rv_matchList);
        recyclerView.setLayoutManager(linearLayoutManager);
        alertBar = Snackbar.make(findViewById(R.id.cl_browseCoordinator),"VolleyCupid", Snackbar.LENGTH_SHORT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAllData();
            }
        });
    }

    public void checkToken(){
//        OkAuthLib.getInstance(this).init(getApplicationContext());
//        if (settings.getString("TOKEN_ACCESS", "MEOW") == null){
//
//        };
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
