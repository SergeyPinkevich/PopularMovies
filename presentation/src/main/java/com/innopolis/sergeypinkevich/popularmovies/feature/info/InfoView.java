package com.innopolis.sergeypinkevich.popularmovies.feature.info;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;

/**
 * @author Sergey Pinkevich
 */

public interface InfoView extends MvpView {

    void loadMovieDetails(MovieDetails movieDetails);

    void showMovieIsFavourite();

    void showMovieIsNotFavourite();

    void showErrorMessage();

    void finishView();

    void showProgress();

    void hideProgress();
}
