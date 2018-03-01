package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public interface MainView extends MvpView {

    void showMoviesList(List<Movie> movies);

    void showDetailScreen();

    void showError();
}
