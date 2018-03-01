package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface RemoteRepository {

    Single<ServerResponse> getPopularMoviesFromNetwork(String language);

    Single<ServerResponse> getTopRatedMoviesFromNetwork(String language);

    Single<MovieDetails> getMovieDetailsFromNetwork(long id, String language);
}
