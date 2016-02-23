package com.antonioleiva.daggerexample.app.ui.test;

import android.app.Activity;
import android.os.Bundle;

import com.antonioleiva.daggerexample.app.R;

public class DemoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ((DemoApplication)getApplication()).component().inject(this);
    }

}
