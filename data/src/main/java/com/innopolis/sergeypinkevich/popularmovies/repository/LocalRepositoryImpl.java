package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class LocalRepositoryImpl implements LocalRepository {
    @Override
    public Single<List<Movie>> getPopularMoviesFromDatabase() {
        return null;
    }
}
