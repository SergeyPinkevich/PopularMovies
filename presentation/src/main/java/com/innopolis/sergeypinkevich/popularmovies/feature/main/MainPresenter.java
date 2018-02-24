package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    public MainPresenter(PopularMoviesUseCase useCase, RxScheduler rxScheduler) {

    }

    public void showDataOnMainScreen(ServerResponse response) {
        getViewState().showMoviesList(response.getResults());
    }
}
