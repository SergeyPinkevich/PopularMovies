package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.model.UserChoice;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.TopRatedMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

import dagger.Lazy;
import internal.di.BaseApp;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    private AndroidWrapper wrapper;
    private FilterMoviesUseCase filterMoviesUseCase;
    private RxScheduler rxScheduler;

    @Inject
    Lazy<PopularMoviesUseCase> popularMoviesUseCase;
    @Inject
    Lazy<TopRatedMoviesUseCase> topRatedMoviesUseCase;

    @Inject
    public SplashPresenter(FilterMoviesUseCase filterMoviesUseCase, RxScheduler rxScheduler, AndroidWrapper wrapper) {
        BaseApp.component.inject(this);

        this.filterMoviesUseCase = filterMoviesUseCase;
        this.rxScheduler = rxScheduler;
        this.wrapper = wrapper;
    }

    public void getMovies() {
        UserChoice userChoice = filterMoviesUseCase.getUserLastChoice();
        if (userChoice == UserChoice.POPULAR) {
            popularMoviesUseCase.get().getPopularMovies()
                    .subscribeOn(rxScheduler.getNetwork())
                    .observeOn(rxScheduler.getMain())
                    .subscribe(data -> getViewState().startMainScreen(data),
                            exception -> Log.e("SplashPresenter", exception.getMessage(), exception));
        } else {

        }
    }
}
