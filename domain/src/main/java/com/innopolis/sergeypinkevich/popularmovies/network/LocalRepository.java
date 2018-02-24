package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface LocalRepository {

    Single<ServerResponse> getPopularMoviesFromDatabase();
}
