package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface LocalRepository {

    Single<ServerResponse> getPopularMoviesFromDatabase();

    Single<ServerResponse> getTopRatedMoviesFromDatabase();

    Single<MovieDetails> getMovieDetailsFromDatabase(long id);
}
