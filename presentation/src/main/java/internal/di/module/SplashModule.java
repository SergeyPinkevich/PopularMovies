package internal.di.module;

import com.innopolis.sergeypinkevich.popularmovies.feature.splash.SplashPresenter;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.TopRatedMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergey Pinkevich
 */
@Module
public class SplashModule {

    @Singleton
    @Provides
    SplashPresenter providePresenter(FilterMoviesUseCase useCase, RxScheduler rxScheduler, AndroidWrapper wrapper) {
        return new SplashPresenter(useCase, rxScheduler, wrapper);
    }

    @Singleton
    @Provides
    FilterMoviesUseCase provideFilterUseCase(AndroidWrapper wrapper) {
        return new FilterMoviesUseCase(wrapper);
    }

    @Singleton
    @Provides
    TopRatedMoviesUseCase provideTopRatedUseCase() {
        return new TopRatedMoviesUseCase();
    }

    @Singleton
    @Provides
    PopularMoviesUseCase providePopularUseCase(AndroidWrapper wrapper, RemoteRepository remoteRepository, LocalRepository localRepository) {
        return new PopularMoviesUseCase(wrapper, remoteRepository, localRepository);
    }
}
