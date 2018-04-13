package com.innopolis.sergeypinkevich.popularmovies.repository;

import android.content.Context;
import android.database.Cursor;

import com.innopolis.sergeypinkevich.popularmovies.database.FavouriteMovieContract;
import com.innopolis.sergeypinkevich.popularmovies.database.FavouriteMovieDatabaseHelper;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;
import com.innopolis.sergeypinkevich.popularmovies.model.MovieServerResponse;
import com.innopolis.sergeypinkevich.popularmovies.network.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Sergey Pinkevich
 */

public class LocalRepositoryImpl implements LocalRepository {

    private Context context;
    private FavouriteMovieDatabaseHelper databaseHelper;

    @Inject
    public LocalRepositoryImpl(Context context, FavouriteMovieDatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Single<MovieServerResponse> getPopularMoviesFromDatabase() {
        return null;
    }

    @Override
    public Single<MovieServerResponse> getTopRatedMoviesFromDatabase() {
        return null;
    }

    @Override
    public Single<MovieDetails> getMovieDetailsFromDatabaseById(long id) {
        return null;
    }

    @Override
    public void addFavouriteMovie(MovieDetails movieDetails) {
        context.getContentResolver().insert(FavouriteMovieContract.FavouriteMovieEntry.CONTENT_URI, databaseHelper.getContentValues(movieDetails));
    }

    @Override
    public Single<List<MovieDetails>> getFavouriteMovies() {
        List<MovieDetails> movieDetails = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(FavouriteMovieContract.FavouriteMovieEntry.CONTENT_URI, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                movieDetails.add(createMovieDetailsFromCursor(cursor));
                cursor.moveToNext();
            }
        }
        return Single.just(movieDetails);
    }

    @Override
    public void removeFavouriteMovie(long movieId) {
        context.getContentResolver().delete(FavouriteMovieContract.FavouriteMovieEntry.CONTENT_URI, FavouriteMovieContract.FavouriteMovieEntry._ID + " = ?" + movieId, null);
    }

    private MovieDetails createMovieDetailsFromCursor(Cursor cursor) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setTitle(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_TITLE)));
        movieDetails.setPosterPath(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_POSTER)));
        movieDetails.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RATING)));
        movieDetails.setOverview(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_PLOT)));
        movieDetails.setReleaseDate(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RELEASE_DATE)));
        return movieDetails;
    }
}
