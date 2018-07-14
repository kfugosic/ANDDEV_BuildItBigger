package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kfugosic.jokesactivity.JokesActivity;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    private ProgressBar mSpinner;
    private Button mTellJokeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTellJokeButton = findViewById(R.id.button_tell_joke);
        mSpinner = (ProgressBar) findViewById(R.id.progress_bar_1);

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                MainActivity.this.startLoading();
            }
        });
    }

    private void startLoading() {
        new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                mTellJokeButton.setEnabled(true);
                mSpinner.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this, JokesActivity.class);
                intent.putExtra(JokesActivity.JOKE_EXTRA, result);
                startActivity(intent);
            }
        }.execute(this);
        mTellJokeButton.setEnabled(false);
        mSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        } else {
            startLoading();
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }

    }


}
