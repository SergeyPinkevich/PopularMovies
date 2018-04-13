package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.converter.MovieDetailsToMovieConverter;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FavouriteMovieUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.TopRatedMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import internal.di.BaseApp;

import static com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase.FAVOURITES_FILTER;
import static com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase.POPULAR_FILTER;
import static com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase.TOP_RATED_FILTER;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private RxScheduler rxScheduler;
    private AndroidWrapper wrapper;

    @Inject
    Lazy<PopularMoviesUseCase> popularMoviesUseCase;
    @Inject
    Lazy<TopRatedMoviesUseCase> topRatedMoviesUseCase;
    @Inject
    Lazy<FavouriteMovieUseCase> favouriteMovieUseCase;

    @Inject
    public MainPresenter(RxScheduler rxScheduler, AndroidWrapper wrapper) {
        BaseApp.component.inject(this);

        this.wrapper = wrapper;
        this.rxScheduler = rxScheduler;
    }

    public void showDataOnMainScreen(MovieServerResponse response) {
        getViewState().showMoviesList(response.getResults());
    }

    public void showFavoriteMovies(List<MovieDetails> movieDetails) {
        getViewState().showMoviesList(MovieDetailsToMovieConverter.convertToMovie(movieDetails));
    }

    public void getInformationAboutMovie(long movieId) {
        getViewState().showMovieDetailScreen(movieId);
    }

    public void filterFavourites() {
        getViewState().showProgress();
        favouriteMovieUseCase.get().getFavouriteMoviesList()
                .subscribeOn(rxScheduler.getDatabase())
                .observeOn(rxScheduler.getMain())
                .doAfterTerminate(() -> getViewState().hideProgress())
                .subscribe(data -> {
                            showFavoriteMovies(data);
                            wrapper.putFilterTypeToSharedPreferences(FAVOURITES_FILTER);
                        },
                        exception -> {
                            Log.e("MainPresenter", exception.getMessage(), exception);
                            getViewState().showErrorMessage();
                        });
    }

    public void filterMoviesByPopularity() {
        getViewState().showProgress();
        popularMoviesUseCase.get().getPopularMovies()
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getMain())
                .doAfterTerminate(() -> getViewState().hideProgress())
                .subscribe(data -> {
                            showDataOnMainScreen(data);
                            wrapper.putFilterTypeToSharedPreferences(POPULAR_FILTER);
                        },
                        exception -> {
                            Log.e("MainPresenter", exception.getMessage(), exception);
                            getViewState().showErrorMessage();
                        });
    }

    public void filterMoviesByRating() {
        getViewState().showProgress();
        topRatedMoviesUseCase.get().getTopRatedMovies()
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getMain())
                .doAfterTerminate(() -> getViewState().hideProgress())
                .subscribe(data -> {
                            showDataOnMainScreen(data);
                            wrapper.putFilterTypeToSharedPreferences(TOP_RATED_FILTER);
                        },
                        exception -> {
                            Log.e("MainPresenter", exception.getMessage(), exception);
                            getViewState().showErrorMessage();
                        });
    }
}
