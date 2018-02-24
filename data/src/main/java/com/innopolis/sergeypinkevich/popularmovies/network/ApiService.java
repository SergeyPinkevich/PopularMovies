package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Sergey Pinkevich
 */

public interface ApiService {

    String API_KEY = "api_key";

    @GET("/movie/popular")
    Single<ServerResponse> getPopularMovies(@Query(API_KEY) String apiKey);
}
