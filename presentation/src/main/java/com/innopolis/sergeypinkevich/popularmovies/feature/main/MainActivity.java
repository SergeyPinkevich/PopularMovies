package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.splash.SplashActivity;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import internal.di.BaseApp;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.movies_list)
    RecyclerView recyclerViewMovies;

    @Inject
    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter.attachView(this);
        Intent intent = getIntent();
        if (intent != null && intent.getParcelableExtra(SplashActivity.MOVIES_LIST) != null) {
            presenter.showDataOnMainScreen(intent.getParcelableExtra(SplashActivity.MOVIES_LIST));
        }
    }

    @Override
    public void showMoviesList(List<Movie> movies) {
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMovies.setAdapter(new MovieAdapter(movies));
    }

    @Override
    public void showError() {

    }
}
