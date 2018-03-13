package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.model.ReviewServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.model.TrailerServerResponse;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface RemoteRepository {

    Single<MovieServerResponse> getPopularMoviesFromNetwork(String language);

    Single<MovieServerResponse> getTopRatedMoviesFromNetwork(String language);

    Single<MovieDetails> getMovieDetailsFromNetwork(long id, String language);

    Single<TrailerServerResponse> getMovieTrailers(long id);

    Single<ReviewServerResponse> getMovieReview(long id);
}
