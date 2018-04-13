package com.innopolis.sergeypinkevich.popularmovies.usecase;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class FavouriteMovieUseCase {

    private LocalRepository localRepository;

    @Inject
    public FavouriteMovieUseCase(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public Single<List<MovieDetails>> getFavouriteMoviesList() {
        return localRepository.getFavouriteMovies();
    }

    public void makeFavourite(MovieDetails movieDetails) {
        localRepository.addFavouriteMovie(movieDetails);
    }

    public void makeUnfavourite(long movieId) {
        localRepository.removeFavouriteMovie(movieId);
    }
}
