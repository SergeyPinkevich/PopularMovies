package com.innopolis.sergeypinkevich.popularmovies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

public class AndroidWrapperImpl implements AndroidWrapper {

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
}
