package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.util.Log;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.kfugosic.javajokes.Jokes;
import com.kfugosic.jokesactivity.JokesActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Kristijan on 14-Jul-18.
 */

@RunWith(AndroidJUnit4.class)
public class GCEAndroidTestT {

//    @Rule
//    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
//            MainActivity.class);

    @Test
    public void testGCEAsyncTask() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);

        new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String s) {
                assertEquals(new Jokes().getJoke(), s);
                signal.countDown();
            }
        }.execute((Context)null);
        signal.await();
    }

}
