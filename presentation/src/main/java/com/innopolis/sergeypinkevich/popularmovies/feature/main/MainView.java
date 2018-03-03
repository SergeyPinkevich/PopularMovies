package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void showProgress();

    void hideProgress();

    void showMoviesList(List<Movie> movies);

    void showMovieDetailScreen(long movieId);

    void showErrorMessage();
}
