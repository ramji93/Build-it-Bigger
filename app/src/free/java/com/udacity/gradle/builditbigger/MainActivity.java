package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener; //freeversion
import com.google.android.gms.ads.AdRequest; //freeversion
import com.google.android.gms.ads.InterstitialAd; //freeversion

public class MainActivity extends ActionBarActivity {

    InterstitialAd mInterstitialAd;
    private ProgressBar spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                loadJokeActivity();
            }
        });

        requestNewInterstitial();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);


    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("BB68D66485883BB352A199A73535129E")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void loadJokeActivity()
    {
        spinner.setVisibility(View.VISIBLE);
        EndpointsAsyncTask mytask = new EndpointsAsyncTask();
        mytask.execute(new Pair<Context, ProgressBar>(this,spinner));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button,
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        //  Toast.makeText(this,new joker().getJokes(), Toast.LENGTH_SHORT).show();

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {


            loadJokeActivity();


        }
        //Intent intent = new Intent(this, JokeActivity.class);
        //intent.putExtra("jokemessage",new joker().getJokes());
        //startActivity(intent);
    }

}
