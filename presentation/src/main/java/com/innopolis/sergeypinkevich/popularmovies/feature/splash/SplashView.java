package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public interface SplashView extends MvpView {

    void startMainScreen(List<Movie> movies);
}
