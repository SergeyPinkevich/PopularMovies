package internal.di.component;

import com.innopolis.sergeypinkevich.popularmovies.feature.info.InfoActivity;
import com.innopolis.sergeypinkevich.popularmovies.feature.info.detail.DetailsFragment;
import com.innopolis.sergeypinkevich.popularmovies.feature.info.review.ReviewFragment;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainPresenter;
import com.innopolis.sergeypinkevich.popularmovies.feature.splash.SplashActivity;
import com.innopolis.sergeypinkevich.popularmovies.feature.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;
import internal.di.module.AppModule;
import internal.di.module.NetworkModule;
import internal.di.module.RepositoryModule;
import internal.di.module.SplashModule;

/**
 * @author Sergey Pinkevich
 */
@Singleton
@Component(modules = {AppModule.class, SplashModule.class, NetworkModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(SplashPresenter presenter);

    void inject(MainPresenter presenter);

    void inject(InfoActivity activity);

    void inject(DetailsFragment fragment);

    void inject(ReviewFragment fragment);
}
