package com.example.jarvis.proyectofean.Splash;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by JARVIS on 3/07/2017.
 */

public final class CurrentActivityListener implements Application.ActivityLifecycleCallbacks{

    private static Activity currentActivity;

    public CurrentActivityListener(){}

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
