package com.innopolis.sergeypinkevich.popularmovies.converter;

import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public class MovieDetailsToMovieConverter {

    public static List<Movie> convertToMovie(List<MovieDetails> movieDetails) {
        List<Movie> movies = new ArrayList<>();
        for (MovieDetails details : movieDetails) {
            Movie movie = new Movie();
            movie.setTitle(details.getTitle());
            movie.setPosterPath(details.getPosterPath());
            movie.setVoteAverage(details.getVoteAverage());
            movie.setReleaseDate(details.getReleaseDate());
            movie.setOverview(details.getOverview());

            movies.add(movie);
        }
        return movies;
    }
}
