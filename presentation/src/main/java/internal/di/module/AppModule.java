package internal.di.module;

import android.app.Application;
import android.content.Context;

import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapper;
import com.innopolis.sergeypinkevich.popularmovies.utils.AndroidWrapperImpl;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxSchedulersImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import internal.di.scope.ApplicationContext;

/**
 * @author Sergey Pinkevich
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @ApplicationContext
    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    AndroidWrapper provideAndroidWrapper() {
        return new AndroidWrapperImpl(application);
    }

    @Singleton
    @Provides
    RxScheduler provideSchedulers() {
        return new RxSchedulersImpl();
    }
}
