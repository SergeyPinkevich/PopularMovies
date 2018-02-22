package com.innopolis.sergeypinkevich.popularmovies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

public class AndroidWrapperImpl implements AndroidWrapper {

    public static final String FILTER_KEY = "filter_key";
    public static final String DEFAULT_FILTER = "popular";

    private Context context;

    @Inject
    public AndroidWrapperImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo.isConnected();
    }

    @Override
    public String getFilterTypeFromSharedPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(FILTER_KEY, DEFAULT_FILTER);
    }
}
