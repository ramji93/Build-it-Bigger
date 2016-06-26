package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.displayjokes.JokeActivity;
import com.example.user.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by user on 20-06-2016.
 */

class EndpointsAsyncTask extends AsyncTask<Pair<Context,ProgressBar>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    public String jokeresult;
    private ProgressBar spinner;

    @Override
    protected String doInBackground(Pair<Context, ProgressBar>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://possible-dream-134823.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        spinner = params[0].second;


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();


        spinner.setVisibility(View.GONE);

        jokeresult = result;

        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra("jokemessage",result);
        context.startActivity(intent);

    }
}