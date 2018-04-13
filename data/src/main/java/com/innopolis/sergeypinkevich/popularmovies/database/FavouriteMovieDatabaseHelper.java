package com.innopolis.sergeypinkevich.popularmovies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.innopolis.sergeypinkevich.popularmovies.model.MovieDetails;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Sergey Pinkevich
 */
@Singleton
public class FavouriteMovieDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "favourites.db";
    public static final int DB_VERSION = 1;

    @Inject
    public FavouriteMovieDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FavouriteMovieContract.FavouriteMovieEntry.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w("DatabaseHelper", "Upgrading database. Existing contents will be lost. ["
                + oldVersion + "]->[" + newVersion + "]");
        sqLiteDatabase.execSQL(FavouriteMovieContract.FavouriteMovieEntry.DELETE_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public ContentValues getContentValues(MovieDetails details) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavouriteMovieContract.FavouriteMovieEntry.MOVIE_ID, details.getId());
        contentValues.put(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_TITLE, details.getTitle());
        contentValues.put(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RELEASE_DATE, details.getReleaseDate());
        contentValues.put(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_POSTER, details.getPosterPath());
        contentValues.put(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RATING, details.getVoteAverage());
        contentValues.put(FavouriteMovieContract.FavouriteMovieEntry.COLUMN_PLOT, details.getOverview());
        return contentValues;
    }
}
