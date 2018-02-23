package internal.di.module;

import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;

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
    PopularMoviesUseCase provideUseCase(AndroidWrapper wrapper, RemoteRepository remoteRepository, LocalRepository localRepository) {
        return new PopularMoviesUseCase(wrapper, remoteRepository, localRepository);
    }
}
