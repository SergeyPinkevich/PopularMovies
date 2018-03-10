package com.innopolis.sergeypinkevich.popularmovies.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

public class SplashActivity extends AppCompatActivity implements SplashView {

    public static final String MOVIES_LIST = "movies_list";

    @Inject
    @InjectPresenter
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter.attachView(this);
        presenter.getMovies();
    }

    @Override
    public void startMainScreen(MovieServerResponse response) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MOVIES_LIST, response);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String errorText) {
        Toasty.error(this, errorText, Toast.LENGTH_SHORT).show();
    }
}
