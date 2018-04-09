package com.innopolis.sergeypinkevich.popularmovies.feature.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FavouriteMovieUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {

    private FavouriteMovieUseCase favouriteMovieUseCase;
    private RxScheduler rxScheduler;

    @Inject
    public InfoPresenter(FavouriteMovieUseCase favouriteMovieUseCase, RxScheduler rxScheduler) {
        this.favouriteMovieUseCase = favouriteMovieUseCase;
        this.rxScheduler = rxScheduler;
    }

    public void changeMovieIsFavourite(boolean isSelectedAsFavouriteNow, MovieDetails movieDetails) {
        if (isSelectedAsFavouriteNow) {
            favouriteMovieUseCase.makeFavourite(movieDetails);
            getViewState().showMovieIsFavourite();
        } else {
            favouriteMovieUseCase.makeUnfavourite(movieDetails.getId());
            getViewState().showMovieIsNotFavourite();
        }
    }
}
