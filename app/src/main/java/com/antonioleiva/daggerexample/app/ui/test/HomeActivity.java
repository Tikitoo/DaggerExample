package com.antonioleiva.daggerexample.app.ui.test;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.antonioleiva.daggerexample.app.R;

import javax.inject.Inject;

public class HomeActivity extends Activity {
    @Inject
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ((DemoApplication)getApplication()).component().inject(this);

        Log.d("HomeActivity", mLocationManager.toString());
    }
}
