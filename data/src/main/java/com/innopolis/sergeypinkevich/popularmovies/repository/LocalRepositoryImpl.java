package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class LocalRepositoryImpl implements LocalRepository {
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
}
