package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;

/**
 * @author Sergey Pinkevich
 */

public interface SplashView extends MvpView {

    void startMainScreen(MovieServerResponse response);

    void showError(String errorText);
}
