package com.innopolis.sergeypinkevich.popularmovies.usecase;

import com.innopolis.sergeypinkevich.popularmovies.model.UserChoice;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

public class FilterMoviesUseCase {

    public static final String DEFAULT_FILTER = "popular";
    public static final String POPULAR_FILTER = "popular";
    public static final String TOP_RATED_FILTER = "top_rated";
    public static final String FAVOURITES_FILTER = "favourites";

    AndroidWrapper wrapper;

    @Inject
    public FilterMoviesUseCase(AndroidWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public UserChoice getUserLastChoice() {
        if (wrapper.getFilterTypeFromSharedPreference().equals(POPULAR_FILTER)) {
            return UserChoice.POPULAR;
        } else if (wrapper.getFilterTypeFromSharedPreference().equals(TOP_RATED_FILTER)) {
            return UserChoice.TOP_RATED;
        } else {
            return UserChoice.FAVOURITE;
        }
    }
}
