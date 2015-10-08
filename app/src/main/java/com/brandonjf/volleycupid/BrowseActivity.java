package com.brandonjf.volleycupid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadAllData();
            }
        });


        //Find the elements on the page
       handlePageSetup();
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
        OkRecyclerViewAdapter rvAdapter = new OkRecyclerViewAdapter(quickmatchMatches);
        recyclerView.setAdapter(rvAdapter);
    }

    public void handlePageSetup(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rv_matchList);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    public void loadAllData(){
        loadQuickmatchData();
    }

    public void loadQuickmatchData(){
        QuickmatchLib.getInstance(this).getQueue(getApplicationContext());
    }
}
