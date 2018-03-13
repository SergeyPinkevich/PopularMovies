package com.innopolis.sergeypinkevich.popularmovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public class ReviewServerResponse {

    @SerializedName("results")
    @Expose
    List<Review> reviewList;

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> trailerList) {
        this.reviewList = trailerList;
    }
}
