package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;

import java.util.List;

import javax.inject.Inject;

import internal.di.BaseApp;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @Inject
    @InjectPresenter
    public SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter.getMovies();
    }

    @Override
    public void startMainScreen(List<Movie> movies) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
