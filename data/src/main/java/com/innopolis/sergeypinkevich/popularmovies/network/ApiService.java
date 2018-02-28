package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl.API_KEY;
import static com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl.API_KEY_PARAMETER;
import static com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl.LANGUAGE;

/**
 * @author Sergey Pinkevich
 */

public interface ApiService {

    @GET("/3/movie/popular")
    Single<ServerResponse> getPopularMovies(@Query(API_KEY_PARAMETER) String apiKey,
                                            @Query(LANGUAGE) String language);
}
