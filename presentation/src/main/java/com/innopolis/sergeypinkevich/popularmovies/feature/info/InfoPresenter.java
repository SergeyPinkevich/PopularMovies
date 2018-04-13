package com.innopolis.sergeypinkevich.popularmovies.feature.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FavouriteMovieUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.MovieDetailsUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {

    private MovieDetailsUseCase movieDetailsUseCase;
    private FavouriteMovieUseCase favouriteMovieUseCase;
    private RxScheduler rxScheduler;

    @Inject
    public InfoPresenter(FavouriteMovieUseCase favouriteMovieUseCase, MovieDetailsUseCase movieDetailsUseCase, RxScheduler rxScheduler) {
        this.movieDetailsUseCase = movieDetailsUseCase;
        this.favouriteMovieUseCase = favouriteMovieUseCase;
        this.rxScheduler = rxScheduler;
    }

    public void getMovieDetailsById(long id) {
        getViewState().showProgress();
        movieDetailsUseCase.getMovieDetails(id)
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getMain())
                .doAfterTerminate(() -> getViewState().hideProgress())
                .subscribe(data -> getViewState().loadMovieDetails(data), exception -> {
                    getViewState().showErrorMessage();
                    getViewState().finishView();
                });
    }

    public void checkIsCurrentMovieInFavouriteCategory(long id) {
        if (favouriteMovieUseCase.isFavourite(id)) {
            getViewState().showMovieIsFavourite();
        } else {
            getViewState().showMovieIsNotFavourite();
        }
    }

    public void changeMovieIsFavourite(boolean isSelectedAsFavouriteNow, MovieDetails movieDetails) {
        if (isSelectedAsFavouriteNow) {
            favouriteMovieUseCase.makeUnfavourite(movieDetails.getId());
            getViewState().showMovieIsNotFavourite();
        } else {
            favouriteMovieUseCase.makeFavourite(movieDetails);
            getViewState().showMovieIsFavourite();
        }
    }
}
