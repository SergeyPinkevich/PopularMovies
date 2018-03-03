package internal.di.module;

import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainPresenter;
import com.innopolis.sergeypinkevich.popularmovies.usecase.FilterMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Sergey Pinkevich
 */
@Module
public class MainModule {

    @Singleton
    @Provides
    MainPresenter providePresenter(RxScheduler rxScheduler, AndroidWrapper wrapper) {
        return new MainPresenter(rxScheduler, wrapper);
    }
}
