package com.example.jarvis.proyectofean.Splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by JARVIS on 3/07/2017.
 */

public class PreferencesManager {

    private static final String PREF_NAME =  PreferencesManager.class.getName();

    private static PreferencesManager sInstance;

    private SharedPreferences sharedPreferences;
    private PreferencesManager(Context context){
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Preferences attributes
     */
    public static final String PREF_EMAIL = "email";

    public static final String PREF_ISLOGGED = "islogged";

    public static final String PREF_TOKEN = "token";

    public static final String PREF_ISNOT_FIRSTTIME = "isfirsttime";

    public static final String PREF_TOKEN_GCM = "token-gcm";

    public static final String PREF_SETTINGS_EVENTS_OFF = "settings-events-off";

    public static final String PREF_SETTINGS_PAYMENTS_OFF = "settings-payments-off";

    public static final String PREF_SETTINGS_NEWS_OFF = "settings-news-off";

    public static final String PREF_SETTINGS_ALERTS_OFF = "settings-alertss-off";

    /**
     * Crea una instancia de PreferencesManager a partir de un contexto
     * @param context
     * @return
     */
    public static synchronized PreferencesManager getInstance(Context context){
        if(sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }

    /**
     * Recupera una instancia de PreferencesManager anteriormente creada
     * @return
     */
    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void set(String param, String value){
        sharedPreferences.edit().putString(param, value).commit();
        Log.d(PreferencesManager.class.getSimpleName(), "set: "+param+"-"+value);
    }

    public String get(String param){
        String email = sharedPreferences.getString(param, null);
        Log.d(PreferencesManager.class.getSimpleName(), "get: "+param+"-"+email);
        return email;
    }

    public void remove(String param) {
        sharedPreferences.edit().putString(param, null).commit();
        Log.d(PreferencesManager.class.getSimpleName(), "remove: "+param);
    }

    public void clear() {
        sharedPreferences.edit().clear().commit();
        Log.d(PreferencesManager.class.getSimpleName(), "clear");
    }
}
