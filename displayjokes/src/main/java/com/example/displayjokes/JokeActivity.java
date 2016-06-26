package com.example.displayjokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        Intent intent = getIntent();

        TextView jokeview = (TextView) findViewById(R.id.joketext);
        jokeview.setText(intent.getStringExtra("jokemessage"));



    }
}
