package com.innopolis.sergeypinkevich.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Pinkevich
 */

public class MovieServerResponse implements Parcelable {

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalResults);
        dest.writeInt(this.totalPages);
        dest.writeList(this.results);
    }

    public MovieServerResponse() {
    }

    protected MovieServerResponse(Parcel in) {
        this.page = in.readInt();
        this.totalResults = in.readInt();
        this.totalPages = in.readInt();
        this.results = new ArrayList<Movie>();
        in.readList(this.results, Movie.class.getClassLoader());
    }

    public static final Creator<MovieServerResponse> CREATOR = new Creator<MovieServerResponse>() {
        @Override
        public MovieServerResponse createFromParcel(Parcel source) {
            return new MovieServerResponse(source);
        }

        @Override
        public MovieServerResponse[] newArray(int size) {
            return new MovieServerResponse[size];
        }
    };
}
