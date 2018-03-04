package com.innopolis.sergeypinkevich.popularmovies.usecase;

import android.accounts.NetworkErrorException;

import com.innopolis.sergeypinkevich.popularmovies.model.ServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class TopRatedMoviesUseCase {

    public static final String INTERNET_IS_NOT_AVAILABLE = "Internet connection is not available";

    private AndroidWrapper wrapper;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public TopRatedMoviesUseCase(AndroidWrapper wrapper,
                                RemoteRepository remoteRepository,
                                LocalRepository localRepository) {
        this.wrapper = wrapper;
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    public Single<ServerResponse> getTopRatedMovies() {
        if (wrapper.isNetworkAvailable()) {
            return remoteRepository.getTopRatedMoviesFromNetwork(wrapper.getLocalLanguage());
        } else {
            return Single.error(new NetworkErrorException(INTERNET_IS_NOT_AVAILABLE));
        }
    }
}
