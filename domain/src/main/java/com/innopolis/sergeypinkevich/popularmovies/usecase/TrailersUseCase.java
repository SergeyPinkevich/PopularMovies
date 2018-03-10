package com.innopolis.sergeypinkevich.popularmovies.usecase;

import android.accounts.NetworkErrorException;

import com.innopolis.sergeypinkevich.popularmovies.model.TrailerServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.innopolis.sergeypinkevich.popularmovies.usecase.TopRatedMoviesUseCase.INTERNET_IS_NOT_AVAILABLE;

/**
 * @author Sergey Pinkevich
 */

public class TrailersUseCase {

    private AndroidWrapper wrapper;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public TrailersUseCase(AndroidWrapper wrapper,
                               RemoteRepository remoteRepository,
                               LocalRepository localRepository) {
        this.wrapper = wrapper;
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    public Single<TrailerServerResponse> getMovieTrailers(long id) {
        if (wrapper.isNetworkAvailable()) {
            return remoteRepository.getMovieTrailers(id);
        } else {
            return Single.error(new NetworkErrorException(INTERNET_IS_NOT_AVAILABLE));
        }
    }
}
