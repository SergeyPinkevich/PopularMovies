package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

public class DetailActivity extends AppCompatActivity implements DetailView {

    public static final long WRONG_ID = -1;

    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.movie_plot)
    TextView moviePlot;
    @BindView(R.id.movie_rating)
    TextView movieRating;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    @InjectPresenter
    DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BaseApp.component.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter.attachView(this);
        if (getIntent() != null) {
            presenter.getMovieDetailsById(getIntent().getLongExtra(MainActivity.MOVIE_DETAIL_EXTRA, WRONG_ID));
        }
    }

    @Override
    public void showTitle(String title) {
        movieTitle.setText(title);
    }

    @Override
    public void showPoster(String posterPath) {
        Picasso.with(this).load(RemoteRepositoryImpl.IMAGE_PATH + posterPath).into(moviePoster);
    }

    @Override
    public void showPlot(String plot) {
        moviePlot.setText(plot);
    }

    @Override
    public void showRating(double rating) {
        movieRating.setText(String.valueOf(rating));
    }

    @Override
    public void showReleaseDate(String date) {
        movieReleaseDate.setText(date);
    }

    @Override
    public void showErrorMessage() {
        Toasty.error(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finishView() {
        onBackPressed();
    }
}
