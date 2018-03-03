package com.innopolis.sergeypinkevich.popularmovies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.LocaleList;
import android.preference.PreferenceManager;

import com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase;

import java.util.Locale;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

public class AndroidWrapperImpl implements AndroidWrapper {

    public static final String FILTER_KEY = "filter_key";

    private Context context;

    @Inject
    public AndroidWrapperImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo().isConnected();
        }
        return false;
    }

    @Override
    public String getFilterTypeFromSharedPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(FILTER_KEY, FilterMoviesUseCase.DEFAULT_FILTER);
    }

    @Override
    public void putFilterTypeToSharedPreferences(String filterType) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(FILTER_KEY, filterType).apply();
    }

    @Override
    public String getLocalLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = Resources.getSystem().getConfiguration().locale;
        }
        return locale.getLanguage();
    }
}
