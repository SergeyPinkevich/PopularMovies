package internal.di.module;

import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainPresenter;
import com.innopolis.sergeypinkevich.popularmovies.usecase.PopularMoviesUseCase;
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
    MainPresenter providePresenter(PopularMoviesUseCase useCase, RxScheduler rxScheduler) {
        return new MainPresenter(useCase, rxScheduler);
    }
}
