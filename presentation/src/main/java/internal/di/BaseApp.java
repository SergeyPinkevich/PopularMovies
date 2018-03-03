package internal.di;

import android.app.Application;

import internal.di.component.AppComponent;
import internal.di.component.DaggerAppComponent;
import internal.di.module.AppModule;
import internal.di.module.NetworkModule;
import internal.di.module.RepositoryModule;
import internal.di.module.SplashModule;

/**
 * @author Sergey Pinkevich
 */
public class BaseApp extends Application {

    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .repositoryModule(new RepositoryModule())
                .splashModule(new SplashModule())
                .build();
    }
}
