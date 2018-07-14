package com.kfugosic.jokesactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_extra";

    private TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        mJokeTextView = findViewById(R.id.tv_joke);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        String jokeText = intent.getStringExtra(JOKE_EXTRA);
        mJokeTextView.setText(jokeText);
    }
}
