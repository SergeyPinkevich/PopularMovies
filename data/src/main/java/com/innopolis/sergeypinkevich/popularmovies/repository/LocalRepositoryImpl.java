package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class LocalRepositoryImpl implements LocalRepository {
    @Override
    public Single<ServerResponse> getPopularMoviesFromDatabase() {
        return null;
    }

    @Override
    public Single<ServerResponse> getTopRatedMoviesFromDatabase() {
        return null;
    }

    @Override
    public Single<MovieDetails> getMovieDetailsFromDatabase(long id) {
        return null;
    }
}
