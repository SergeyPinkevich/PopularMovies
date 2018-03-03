package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.converter.DateToStringConverter;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.usecase.MovieDetailsUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

import static com.innopolis.sergeypinkevich.popularmovies.feature.detail.DetailActivity.WRONG_ID;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private MovieDetailsUseCase useCase;
    private RxScheduler rxScheduler;

    @Inject
    public DetailPresenter(MovieDetailsUseCase useCase, RxScheduler rxScheduler) {
        this.useCase = useCase;
        this.rxScheduler = rxScheduler;
    }

    public void getMovieDetailsById(long id) {
        getViewState().showProgress();
        if (id == WRONG_ID) {
            getViewState().hideProgress();
            getViewState().showErrorMessage();
            getViewState().finishView();
        }
        useCase.getMovieDetails(id)
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getMain())
                .subscribe(data -> {
                    getViewState().hideProgress();
                    showMovieDetails(data);
                }, exception -> {
                    getViewState().hideProgress();
                    getViewState().showErrorMessage();
                });
    }

    public void showMovieDetails(MovieDetails movieDetails) {
        getViewState().showTitle(movieDetails.getTitle());
        getViewState().showPoster(movieDetails.getPosterPath());
        getViewState().showPlot(movieDetails.getOverview());
        getViewState().showRating(movieDetails.getVoteAverage());
        getViewState().showReleaseDate(DateToStringConverter.getStringFromDate(movieDetails.getReleaseDate()));
    }
}
