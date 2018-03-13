package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.data.BuildConfig;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.model.ReviewServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.model.TrailerServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.ApiService;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class RemoteRepositoryImpl implements RemoteRepository {

    public static final String API_KEY_PARAMETER = "api_key";
    private static final String API_KEY = BuildConfig.API_KEY;
    public static final String LANGUAGE = "language";
    public static final String IMAGE_PATH = "https://image.tmdb.org/t/p/w500";

    private ApiService apiService;

    public RemoteRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<MovieServerResponse> getPopularMoviesFromNetwork(String language) {
        return apiService.getPopularMovies(API_KEY, language);
    }

    @Override
    public Single<MovieServerResponse> getTopRatedMoviesFromNetwork(String language) {
        return apiService.getTopRatedMovies(API_KEY, language);
    }

    @Override
    public Single<MovieDetails> getMovieDetailsFromNetwork(long id, String language) {
        return apiService.getMovieDetails(id, API_KEY, language);
    }

    @Override
    public Single<TrailerServerResponse> getMovieTrailers(long id) {
        return apiService.getMovieTrailers(id, API_KEY);
    }

    @Override
    public Single<ReviewServerResponse> getMovieReview(long id) {
        return apiService.getMovieReviews(id, API_KEY);
    }
}
