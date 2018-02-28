package com.innopolis.sergeypinkevich.popularmovies.usecase;

import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class PopularMoviesUseCase {

    private AndroidWrapper wrapper;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public PopularMoviesUseCase(AndroidWrapper wrapper,
                                RemoteRepository remoteRepository,
                                LocalRepository localRepository) {
        this.wrapper = wrapper;
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    public Single<ServerResponse> getPopularMovies() {
        if (wrapper.isNetworkAvailable()) {
            return remoteRepository.getPopularMoviesFromNetwork(wrapper.getLocalLanguage());
        } else {
            return localRepository.getPopularMoviesFromDatabase();
        }
    }
}
