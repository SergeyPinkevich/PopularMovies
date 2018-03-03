package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import com.arellomobile.mvp.MvpView;

/**
 * @author Sergey Pinkevich
 */

public interface DetailView extends MvpView {

    void showProgress();

    void hideProgress();

    void showTitle(String title);

    void showPoster(String posterPath);

    void showPlot(String plot);

    void showRating(double rating);

    void showReleaseDate(String date);

    void showErrorMessage();

    void finishView();
}
