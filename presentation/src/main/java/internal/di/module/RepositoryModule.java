package internal.di.module;

import com.innopolis.sergeypinkevich.popularmovies.network.ApiService;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.repository.LocalRepositoryImpl;
import com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergey Pinkevich
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    RemoteRepository provideRemoteRepository(ApiService apiService) {
        return new RemoteRepositoryImpl(apiService);
    }

    @Singleton
    @Provides
    LocalRepository provideLocalRepository() {
        return new LocalRepositoryImpl();
    }
}
