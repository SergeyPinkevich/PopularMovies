package com.innopolis.sergeypinkevich.popularmovies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public class TrailerServerResponse {

    @SerializedName("results")
    @Expose
    List<Trailer> trailerList;

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }
}
