package com.innopolis.sergeypinkevich.popularmovies.usecase;

import com.innopolis.sergeypinkevich.popularmovies.model.UserChoice;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

public class FilterMoviesUseCase {

    public static final String DEFAULT_FILTER = "popular";
    public static final String POPULAR_FILTER = DEFAULT_FILTER;
    public static final String TOP_RATED_FILTER = "top_rated";

    AndroidWrapper wrapper;

    @Inject
    public FilterMoviesUseCase(AndroidWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public UserChoice getUserLastChoice() {
        if (wrapper.getFilterTypeFromSharedPreference() == POPULAR_FILTER) {
            return UserChoice.POPULAR;
        } else {
            return UserChoice.TOP_RATED;
        }
    }
}
