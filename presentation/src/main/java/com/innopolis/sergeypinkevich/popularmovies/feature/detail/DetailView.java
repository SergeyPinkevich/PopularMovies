package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import com.arellomobile.mvp.MvpView;

/**
 * @author Sergey Pinkevich
 */

public interface DetailView extends MvpView {

    void showMovieIsFavourite();

    void showMovieIsNotFavourite();

    void showErrorMessage();

    void finishView();
}
