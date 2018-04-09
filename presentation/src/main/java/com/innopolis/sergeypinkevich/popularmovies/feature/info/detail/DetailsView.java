package com.innopolis.sergeypinkevich.popularmovies.feature.info.detail;

import android.content.Intent;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.Trailer;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public interface DetailsView extends MvpView {

    void showProgress();

    void hideProgress();

    void showTitle(String title);

    void showPoster(String posterPath);

    void showPlot(String plot);

    void showRating(double rating);

    void showReleaseDate(String date);

    void showTrailers(List<Trailer> trailerList);

    void openTrailer(Intent intent);

    void showErrorMessage();

    void finishView();
}
