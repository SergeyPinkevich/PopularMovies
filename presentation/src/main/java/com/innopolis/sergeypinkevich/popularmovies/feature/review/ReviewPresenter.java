package com.innopolis.sergeypinkevich.popularmovies.feature.review;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.innopolis.sergeypinkevich.popularmovies.model.ReviewServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.usecase.ReviewUseCase;
import com.innopolis.sergeypinkevich.popularmovies.utils.RxScheduler;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

@InjectViewState
public class ReviewPresenter extends MvpPresenter<ReviewView> {

    private ReviewUseCase useCase;
    private RxScheduler rxScheduler;

    @Inject
    public ReviewPresenter(ReviewUseCase useCase, RxScheduler rxScheduler) {
        this.useCase = useCase;
        this.rxScheduler = rxScheduler;
    }

    public void getReview(long movieId) {
        getViewState().showProgress();
        useCase.getMovieReview(movieId)
                .subscribeOn(rxScheduler.getNetwork())
                .observeOn(rxScheduler.getMain())
                .doAfterTerminate(() -> getViewState().hideProgress())
                .subscribe(data -> showReviewsList(data), exception -> {
                    getViewState().showErrorMessage();
                    getViewState().finishView();
                });
    }

    public void showReviewsList(ReviewServerResponse serverResponse) {
        getViewState().showReviewsList(serverResponse.getReviewList());
    }
}
