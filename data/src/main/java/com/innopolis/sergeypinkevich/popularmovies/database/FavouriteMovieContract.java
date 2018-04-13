package com.innopolis.sergeypinkevich.popularmovies.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Sergey Pinkevich
 */

public class FavouriteMovieContract {

    public static final String CONTENT_AUTHORITY = "com.innopolis.sergeypinkevich.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String FAVOURITE_BASE_PATH = "favourites";

    private FavouriteMovieContract() {

    }

    public static class FavouriteMovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, FAVOURITE_BASE_PATH);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + FAVOURITE_BASE_PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + FAVOURITE_BASE_PATH;

        public static final String TABLE_NAME = "favourites";

        public static final String MOVIE_ID = "movieId";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_PLOT = "plot";

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + FavouriteMovieEntry.TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MOVIE_ID + " INTEGER NOT NULL," +
                COLUMN_TITLE + " TEXT NOT NULL," +
                COLUMN_POSTER + " TEXT NOT NULL," +
                COLUMN_RELEASE_DATE + " TEXT NOT NULL," +
                COLUMN_RATING + " REAL NOT NULL," +
                COLUMN_PLOT + " TEXT)";

        public static final String DELETE_TABLE_QUERY = "DELETE TABLE IF EXISTS " + TABLE_NAME;

    }
}
