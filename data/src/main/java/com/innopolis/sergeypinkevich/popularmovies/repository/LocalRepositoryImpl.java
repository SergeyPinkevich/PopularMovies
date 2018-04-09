package com.innopolis.sergeypinkevich.popularmovies.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    private FavouriteMovieDatabaseHelper databaseHelper;

    @Inject
    public LocalRepositoryImpl(FavouriteMovieDatabaseHelper databaseHelper) {
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
    public Single<MovieDetails> getMovieDetailsFromDatabaseById(long movieId) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME,
                null,
                FavouriteMovieContract.FavouriteMovieEntry._ID + " = ?",
                new String[]{String.valueOf(movieId)},
                null,
                null,
                null);
        cursor.moveToFirst();
        MovieDetails movieDetails = createMovieDetailsFromCursor(cursor);
        cursor.close();
        database.close();
        return Single.just(movieDetails);
    }

    private MovieDetails createMovieDetailsFromCursor(Cursor cursor) {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setTitle(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_TITLE)));
        movieDetails.setPosterPath(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_POSTER)));
        movieDetails.setReleaseDate(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RELEASE_DATE)));
        movieDetails.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RATING)));
        movieDetails.setOverview(cursor.getString(cursor.getColumnIndex(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_PLOT)));
        return movieDetails;
    }

    @Override
    public void addFavouriteMovie(MovieDetails movieDetails) {
        ContentValues contentValues = databaseHelper.getContentValues(movieDetails);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.insert(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, null, contentValues);
        database.close();
    }

    @Override
    public Single<List<MovieDetails>> getFavouriteMovies() {
        List<MovieDetails> movies = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                MovieDetails movieDetails = createMovieDetailsFromCursor(cursor);
                movies.add(movieDetails);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return Single.just(movies);
    }

    @Override
    public void removeFavouriteMovie(long movieId) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        database.delete(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, FavouriteMovieContract.FavouriteMovieEntry._ID + " = ?" + movieId, null);
    }
}
