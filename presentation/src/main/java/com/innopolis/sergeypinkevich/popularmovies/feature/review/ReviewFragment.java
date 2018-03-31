package com.innopolis.sergeypinkevich.popularmovies.feature.review;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.feature.main.MainActivity;
import com.innopolis.sergeypinkevich.popularmovies.model.Review;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import internal.di.BaseApp;

/**
 * @author Sergey Pinkevich
 */

public class ReviewFragment extends Fragment implements ReviewView {

    public static final int WRONG_ID = -1;

    @BindView(R.id.review_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.review_recycler)
    RecyclerView recyclerView;

    @Inject
    @InjectPresenter
    ReviewPresenter presenter;

    public ReviewFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        BaseApp.component.inject(this);
        super.onActivityCreated(savedInstanceState);

        presenter.attachView(this);
        if (getActivity().getIntent() != null) {
            presenter.getReview(getActivity().getIntent().getLongExtra(MainActivity.MOVIE_DETAIL_EXTRA, WRONG_ID));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
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
    public void showReviewsList(List<Review> reviews) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReviewAdapter adapter = new ReviewAdapter(reviews);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage() {
        Toasty.error(getContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishView() {
        getActivity().onBackPressed();
    }
}
