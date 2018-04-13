package com.innopolis.sergeypinkevich.popularmovies.feature.info.detail;

import android.content.Intent;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.converter.DateToStringConverter;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.usecase.TrailersUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    public static final String YOUTUBE_URL = "https://youtube.com/watch?v=";

    private TrailersUseCase trailersUseCase;
    private RxScheduler rxScheduler;

    @Inject
    public DetailsPresenter(TrailersUseCase trailersUseCase, RxScheduler rxScheduler) {
        this.trailersUseCase = trailersUseCase;
        this.rxScheduler = rxScheduler;
    }

    public void getMovieTrailersById(long id) {
        trailersUseCase.getMovieTrailers(id)
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getMain())
                .subscribe(data -> getViewState().showTrailers(data.getTrailerList()), exception -> {
                    getViewState().showErrorMessage();
                });
    }

    public void showMovieDetails(MovieDetails movieDetails) {
        getViewState().showTitle(movieDetails.getOriginalTitle());
        getViewState().showPoster(movieDetails.getPosterPath());
        getViewState().showPlot(movieDetails.getOverview());
        getViewState().showRating(movieDetails.getVoteAverage());
        getViewState().showReleaseDate(DateToStringConverter.getStringFromDate(movieDetails.getReleaseDate()));
    }

    public void openTrailer(String trailerPath) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(YOUTUBE_URL + trailerPath));
        getViewState().openTrailer(intent);
    }
}
