package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */
@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    private PopularMoviesUseCase popularMoviesUseCase;
    private RxScheduler rxScheduler;

    @Inject
    public SplashPresenter(PopularMoviesUseCase popularMoviesUseCase, RxScheduler rxScheduler) {
        this.popularMoviesUseCase = popularMoviesUseCase;
        this.rxScheduler = rxScheduler;
    }

    public void getMovies() {
        popularMoviesUseCase.getPopularMovies()
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getNetwork())
                .subscribe((data, exception) -> {
                    if (exception != null) {
                        Log.e("SplashPresenter", exception.getMessage(), exception);
                    } else {
                        getViewState().startMainScreen(data);
                    }
                });
    }
}
