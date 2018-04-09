package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public interface LocalRepository {

    Single<MovieServerResponse> getPopularMoviesFromDatabase();

    Single<MovieServerResponse> getTopRatedMoviesFromDatabase();

    Single<MovieDetails> getMovieDetailsFromDatabaseById(long id);

    void addFavouriteMovie(MovieDetails movieDetails);

    Single<List<MovieDetails>> getFavouriteMovies();

    void removeFavouriteMovie(long movieId);
}
