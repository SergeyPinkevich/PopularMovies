package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.Movie;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface LocalRepository {

    Single<List<Movie>> getPopularMoviesFromDatabase();
}
