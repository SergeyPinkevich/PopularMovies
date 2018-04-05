package com.innopolis.sergeypinkevich.popularmovies.usecase;

import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

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

    public Single<Boolean> changeMovieIsFavourite(long movieId) {
        return localRepository.changeMovieIsFavourite(movieId);
    }
}