package com.innopolis.sergeypinkevich.popularmovies.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * @author Sergey Pinkevich
 */

public class FavouriteMoviesContentProvider extends ContentProvider {

    public static final int FAVOURITE_MOVIES = 100;
    public static final int FAVOURITE_ID = 101;
    private static final UriMatcher matcher;

    private FavouriteMovieDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    @Inject
    public FavouriteMoviesContentProvider() {}

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(FavouriteMovieContract.CONTENT_AUTHORITY, FavouriteMovieContract.FAVOURITE_BASE_PATH, FAVOURITE_MOVIES);
        matcher.addURI(FavouriteMovieContract.CONTENT_AUTHORITY, FavouriteMovieContract.FAVOURITE_BASE_PATH + "/#",  FAVOURITE_ID);
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new FavouriteMovieDatabaseHelper(getContext());
        database = databaseHelper.getWritableDatabase();
        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        database = databaseHelper.getReadableDatabase();

        if (sortOrder == null || sortOrder == "") {
            sortOrder = FavouriteMovieContract.FavouriteMovieEntry.COLUMN_RATING;
        }

        Cursor cursor;
        switch (matcher.match(uri)) {
            case FAVOURITE_MOVIES:
                cursor = database.query(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case FAVOURITE_ID:
                selection = FavouriteMovieContract.FavouriteMovieEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown Uri " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            case FAVOURITE_MOVIES:
                return FavouriteMovieContract.FavouriteMovieEntry.CONTENT_LIST_TYPE;
            case FAVOURITE_ID:
                return FavouriteMovieContract.FavouriteMovieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + matcher.match(uri));
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowId = database.insert(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, "", contentValues);
        if (rowId > 0) {
            Uri uriOfNewRecord = ContentUris.withAppendedId(Uri.parse(FavouriteMovieContract.FavouriteMovieEntry.CONTENT_ITEM_TYPE), rowId);
            getContext().getContentResolver().notifyChange(uriOfNewRecord, null);
            return uriOfNewRecord;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int countOfDeletedRows;
        switch (matcher.match(uri)) {
            case FAVOURITE_MOVIES:
                countOfDeletedRows = database.delete(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case FAVOURITE_ID:
                String id = uri.getPathSegments().get(1);
                countOfDeletedRows = database.delete(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, FavouriteMovieContract.FavouriteMovieEntry._ID + "=" + id, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + matcher.match(uri));
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return countOfDeletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int countOfUpdatedRows;
        switch (matcher.match(uri)) {
            case FAVOURITE_MOVIES:
                countOfUpdatedRows = database.update(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            case FAVOURITE_ID:
                String id = uri.getPathSegments().get(1);
                countOfUpdatedRows = database.delete(FavouriteMovieContract.FavouriteMovieEntry.TABLE_NAME, FavouriteMovieContract.FavouriteMovieEntry._ID + "=" + id, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + matcher.match(uri));
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return countOfUpdatedRows;
    }
}
