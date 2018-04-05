package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.sergeypinkevich.popularmovies.database.FavouriteMovieDatabaseHelper;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class LocalRepositoryImpl implements LocalRepository {

    private FavouriteMovieDatabaseHelper databaseHelper;

    @Inject
    public LocalRepositoryImpl(FavouriteMovieDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Single<MovieServerResponse> getPopularMoviesFromDatabase() {
        return null;
    }

    @Override
    public Single<MovieServerResponse> getTopRatedMoviesFromDatabase() {
        return null;
    }

    @Override
    public Single<MovieDetails> getMovieDetailsFromDatabase(long id) {
        return null;
    }

    @Override
    public Single<Boolean> changeMovieIsFavourite(long movieId) {
        return Single.just(new Boolean(true));
    }
}
