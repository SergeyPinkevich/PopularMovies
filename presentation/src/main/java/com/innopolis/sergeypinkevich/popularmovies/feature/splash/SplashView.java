package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public interface SplashView extends MvpView {

    void startMainScreen(ServerResponse response);
}
