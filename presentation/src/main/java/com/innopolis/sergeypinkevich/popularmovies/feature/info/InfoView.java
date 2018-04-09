package com.innopolis.sergeypinkevich.popularmovies.feature.info;

import com.arellomobile.mvp.MvpView;

/**
 * @author Sergey Pinkevich
 */

public interface InfoView extends MvpView {

    void showMovieIsFavourite();

    void showMovieIsNotFavourite();

    void showErrorMessage();

    void finishView();
}
