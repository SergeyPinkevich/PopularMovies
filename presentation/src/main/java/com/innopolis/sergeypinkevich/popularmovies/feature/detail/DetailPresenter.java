package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FavouriteMovieUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private FavouriteMovieUseCase favouriteMovieUseCase;
    private RxScheduler rxScheduler;

    @Inject
    public DetailPresenter(FavouriteMovieUseCase favouriteMovieUseCase, RxScheduler rxScheduler) {
        this.favouriteMovieUseCase = favouriteMovieUseCase;
        this.rxScheduler = rxScheduler;
    }

    public void changeMovieIsFavourite(long movieId) {
        favouriteMovieUseCase.changeMovieIsFavourite(movieId)
                .subscribeOn(rxScheduler.getDatabase())
                .observeOn(rxScheduler.getMain())
                .subscribe(data -> showMovieDetails(data), exception -> {
                    getViewState().showErrorMessage();
                    getViewState().finishView();
                });
    }

    private void showMovieDetails(boolean isFavourite) {
        if (isFavourite) {
            getViewState().showMovieIsFavourite();
        } else {
            getViewState().showMovieIsNotFavourite();
        }
    }
}
