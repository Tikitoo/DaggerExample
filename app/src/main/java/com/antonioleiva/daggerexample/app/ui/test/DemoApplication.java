package com.antonioleiva.daggerexample.app.ui.test;

import android.app.Application;
import android.location.LocationManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

public class DemoApplication extends Application {

    @Singleton
    @Component(modules = AndroidModule.class)
    public interface ApplicationComponent {
        void inject(DemoApplication application);
        void inject(DemoActivity demoActivity);
        void inject(HomeActivity homeActivity);
    }

    @Inject
    LocationManager mLocationManager;

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerDemoApplication_ApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
        mComponent.inject(this);
    }

    public ApplicationComponent component() {
        return mComponent;
    }
}
