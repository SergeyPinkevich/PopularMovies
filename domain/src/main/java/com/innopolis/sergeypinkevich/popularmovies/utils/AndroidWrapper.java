package com.innopolis.sergeypinkevich.popularmovies.utils;

/**
 * @author Sergey Pinkevich
 */

public interface AndroidWrapper {

    boolean isNetworkAvailable();

    String getFilterTypeFromSharedPreference();

    /**
     * Language for the current locale
     * @return String value for API request - "en" for USA, "ru" for Russia and so on
     */
    String getLocalLanguage();
}
