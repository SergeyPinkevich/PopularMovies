package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.network.ApiService;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class RemoteRepositoryImpl implements RemoteRepository {

    public static final String API_KEY = "c42b989ebf5b0821f57833782ecfe1eb";

    private ApiService apiService;

    public RemoteRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<Movie>> getPopularMoviesFromNetwork() {
        return apiService.getPopularMovies(API_KEY);
    }
}
