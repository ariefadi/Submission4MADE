package com.example.aplikasimoviecatalogue4.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbcatalog";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIES = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY ," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseMoviesContract.TABLE_NAME_MOVIES,
            DatabaseMoviesContract.MoviesColumns.ID,
            DatabaseMoviesContract.MoviesColumns.TITLE,
            DatabaseMoviesContract.MoviesColumns.OVERVIEW,
            DatabaseMoviesContract.MoviesColumns.RELEASE_DATE,
            DatabaseMoviesContract.MoviesColumns.VOTE_AVERAGE,
            DatabaseMoviesContract.MoviesColumns.POPULARITY,
            DatabaseMoviesContract.MoviesColumns.VOTE_COUNT,
            DatabaseMoviesContract.MoviesColumns.POSTER_PATH,
            DatabaseMoviesContract.MoviesColumns.ORIGINAL_LANGUAGE,
            DatabaseMoviesContract.MoviesColumns.POSTER
    );

    private static final String SQL_CREATE_TABLE_TV_SHOW = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY ," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseTvContract.TABLE_NAME_TV,
            DatabaseTvContract.TvColumns.ID,
            DatabaseTvContract.TvColumns.ORIGINAL_NAME,
            DatabaseTvContract.TvColumns.NAME,
            DatabaseTvContract.TvColumns.POPULARITY,
            DatabaseTvContract.TvColumns.VOTE_COUNT,
            DatabaseTvContract.TvColumns.FIRST_AIR_DATE,
            DatabaseTvContract.TvColumns.ORIGINAL_LANGUAGE,
            DatabaseTvContract.TvColumns.VOTE_AVERAGE,
            DatabaseTvContract.TvColumns.OVERVIEW,
            DatabaseTvContract.TvColumns.POSTER_PATH,
            DatabaseTvContract.TvColumns.POSTER
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_TV_SHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseMoviesContract.TABLE_NAME_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTvContract.TABLE_NAME_TV);
        onCreate(db);
    }
}
