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
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String MOVIE_DETAIL_EXTRA = "movieDetailExtra";
    public static final String SCROLL_POSITION = "scrollPosition";
    public static final String MOVIE_DATA = "movieData";

    @BindView(R.id.movies_list)
    RecyclerView recyclerViewMovies;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    @InjectPresenter
    MainPresenter presenter;

    private int scrollPosition = 0;
    private MovieServerResponse serverResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getIntent() != null && getIntent().getParcelableExtra(SplashActivity.MOVIES_LIST) != null) {
            if (presenter.isFavourite()) {
                presenter.filterFavourites();
            } else if (serverResponse != null) {
                presenter.showDataOnMainScreen(serverResponse);
            } else {
                presenter.showDataOnMainScreen(getIntent().getParcelableExtra(SplashActivity.MOVIES_LIST));
            }
        }
        recyclerViewMovies.getLayoutManager().scrollToPosition(scrollPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        scrollPosition = ((GridLayoutManager)recyclerViewMovies.getLayoutManager()).findFirstVisibleItemPosition();
        outState.putParcelable(MOVIE_DATA, serverResponse);
        outState.putInt(SCROLL_POSITION, scrollPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serverResponse = savedInstanceState.getParcelable(MOVIE_DATA);
        scrollPosition = savedInstanceState.getInt(SCROLL_POSITION);
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
    public void saveToCache(MovieServerResponse response) {
        serverResponse = response;
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
            case R.id.action_filter_favourites:
                presenter.filterFavourites();
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
