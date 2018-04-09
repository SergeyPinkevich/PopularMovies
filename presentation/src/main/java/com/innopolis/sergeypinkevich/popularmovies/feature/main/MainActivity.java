package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.info.InfoActivity;
import com.innopolis.sergeypinkevich.popularmovies.feature.splash.SplashActivity;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String MOVIE_DETAIL_EXTRA = "movieDetailExtra";

    @BindView(R.id.movies_list)
    RecyclerView recyclerViewMovies;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

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
        if (getIntent() != null && getIntent().getParcelableExtra(SplashActivity.MOVIES_LIST) != null) {
            presenter.showDataOnMainScreen(getIntent().getParcelableExtra(SplashActivity.MOVIES_LIST));
        }
    }

    @Override
    public void showMoviesList(List<Movie> movies) {
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        MovieAdapter adapter = new MovieAdapter((view, position) -> presenter.getInformationAboutMovie(movies.get(position).getId()));
        adapter.updateData(movies);
        recyclerViewMovies.setAdapter(adapter);
    }

    @Override
    public void showMovieDetailScreen(long movieId) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra(MOVIE_DETAIL_EXTRA, movieId);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage() {
        Toasty.error(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_popular:
                presenter.filterMoviesByPopularity();
                break;
            case R.id.action_filter_top_rated:
                presenter.filterMoviesByRating();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
