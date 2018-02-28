package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface RemoteRepository {

    Single<ServerResponse> getPopularMoviesFromNetwork(String language);
}
