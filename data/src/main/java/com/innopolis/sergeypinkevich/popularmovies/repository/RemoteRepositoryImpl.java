package com.innopolis.sergeypinkevich.popularmovies.repository;

import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.ApiService;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class RemoteRepositoryImpl implements RemoteRepository {

    public static final String API_KEY_PARAMETER = "api_key";
    public static final String API_KEY = "c42b989ebf5b0821f57833782ecfe1eb";
    public static final String LANGUAGE = "language";

    private ApiService apiService;

    public RemoteRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<ServerResponse> getPopularMoviesFromNetwork(String language) {
        return apiService.getPopularMovies(API_KEY, language);
    }
}
