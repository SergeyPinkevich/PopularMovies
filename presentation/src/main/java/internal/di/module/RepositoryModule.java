package internal.di.module;

import android.content.Context;

import com.innopolis.sergeypinkevich.popularmovies.database.FavouriteMovieDatabaseHelper;
import com.innopolis.sergeypinkevich.popularmovies.network.ApiService;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;
import com.innopolis.sergeypinkevich.popularmovies.network.RemoteRepository;
import com.innopolis.sergeypinkevich.popularmovies.repository.LocalRepositoryImpl;
import com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import internal.di.scope.ApplicationContext;

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
    FavouriteMovieDatabaseHelper provideDatabaseHelper(@ApplicationContext Context context) {
        return new FavouriteMovieDatabaseHelper(context);
    }

    @Singleton
    @Provides
    LocalRepository provideLocalRepository(@ApplicationContext Context context, FavouriteMovieDatabaseHelper databaseHelper) {
        return new LocalRepositoryImpl(context, databaseHelper);
    }
}
