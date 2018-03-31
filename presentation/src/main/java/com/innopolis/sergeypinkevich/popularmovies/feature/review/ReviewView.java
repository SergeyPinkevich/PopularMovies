package com.innopolis.sergeypinkevich.popularmovies.feature.review;

import com.arellomobile.mvp.MvpView;
import com.innopolis.sergeypinkevich.popularmovies.model.Review;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public interface ReviewView extends MvpView {

    void showProgress();

    void hideProgress();

    void showReviewsList(List<Review> reviews);

    void showErrorMessage();

    void finishView();
}
