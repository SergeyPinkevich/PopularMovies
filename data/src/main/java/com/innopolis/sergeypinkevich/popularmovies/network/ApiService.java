package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.model.ReviewServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.model.TrailerServerResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl.API_KEY_PARAMETER;
import static com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl.LANGUAGE;

/**
 * @author Sergey Pinkevich
 */

public interface ApiService {

    @GET("/3/movie/popular")
    Single<MovieServerResponse> getPopularMovies(@Query(API_KEY_PARAMETER) String apiKey,
                                                 @Query(LANGUAGE) String language);

    @GET("/3/movie/top_rated")
    Single<MovieServerResponse> getTopRatedMovies(@Query(API_KEY_PARAMETER) String apiKey,
                                                  @Query(LANGUAGE) String language);

    @GET("/3/movie/{id}")
    Single<MovieDetails> getMovieDetails(@Path("id") long id,
                                         @Query(API_KEY_PARAMETER) String apiKey,
                                         @Query(LANGUAGE) String language);

    @GET("/3/movie/{id}/videos")
    Single<TrailerServerResponse> getMovieTrailers(@Path("id") long id,
                                                   @Query(API_KEY_PARAMETER) String apiKey);

    @GET("/3/movie/{id}/reviews")
    Single<ReviewServerResponse> getMovieReviews(@Path("id") long id,
                                                 @Query(API_KEY_PARAMETER) String apiKey);
}
