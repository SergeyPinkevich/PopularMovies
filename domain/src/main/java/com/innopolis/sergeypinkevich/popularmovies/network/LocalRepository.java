package com.innopolis.sergeypinkevich.popularmovies.network;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public interface LocalRepository {

    void addFavouriteMovie(MovieDetails movieDetails);

    List<MovieDetails> getFavouriteMovies();

    boolean isFavourite(long movieId);

    void removeFavouriteMovie(long movieId);
}
