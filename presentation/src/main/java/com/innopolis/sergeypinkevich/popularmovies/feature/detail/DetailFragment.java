package com.innopolis.sergeypinkevich.popularmovies.feature.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.model.Trailer;
import com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

/**
 * @author Sergey Pinkevich
 */

public class DetailFragment extends Fragment implements DetailView {

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
    @BindView(R.id.trailers_list)
    RecyclerView recyclerViewTrailers;

    @Inject
    @InjectPresenter
    DetailPresenter presenter;

    public DetailFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        BaseApp.component.inject(this);
        super.onActivityCreated(savedInstanceState);

        presenter.attachView(this);
        if (getActivity().getIntent() != null) {
            presenter.getMovieDetailsById(getActivity().getIntent().getLongExtra(MainActivity.MOVIE_DETAIL_EXTRA, WRONG_ID));
            presenter.getMovieTrailersById(getActivity().getIntent().getLongExtra(MainActivity.MOVIE_DETAIL_EXTRA, WRONG_ID));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showTitle(String title) {
        movieTitle.setText(title);
    }

    @Override
    public void showPoster(String posterPath) {
        Picasso.with(getContext()).load(RemoteRepositoryImpl.IMAGE_PATH + posterPath).into(moviePoster);
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
    public void showTrailers(List<Trailer> trailerList) {
        recyclerViewTrailers.setLayoutManager(new LinearLayoutManager(getContext()));
        TrailerAdapter adapter = new TrailerAdapter((listener, position) -> presenter.openTrailer(trailerList.get(position).getKey()));
        adapter.updateData(trailerList);
        recyclerViewTrailers.setAdapter(adapter);
    }

    @Override
    public void openTrailer(Intent intent) {
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void showErrorMessage() {
        Toasty.error(getContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
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
    public void finishView() {
        getActivity().onBackPressed();
    }
}
