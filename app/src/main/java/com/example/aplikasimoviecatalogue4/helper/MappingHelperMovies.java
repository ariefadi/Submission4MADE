package com.example.aplikasimoviecatalogue4.helper;

import android.database.Cursor;

import com.example.aplikasimoviecatalogue4.Database.DatabaseMoviesContract;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;

import java.util.ArrayList;

public class MappingHelperMovies {

    public static ArrayList<MoviesItems> mapCursorToArrayList(Cursor moviesCursor){
        ArrayList<MoviesItems> moviesList = new ArrayList<>();

        while (moviesCursor.moveToNext()) {
            int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.ID));
            String title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.TITLE));
            String overview = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.OVERVIEW));
            String releaseDate = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.RELEASE_DATE));
            String voteAverage = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.VOTE_AVERAGE));
            String popularity = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.POPULARITY));
            String voteCount = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.VOTE_COUNT));
            String posterPath = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.POSTER_PATH));
            String originalLanguage = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.ORIGINAL_LANGUAGE));
            String poster = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.POSTER));
            moviesList.add(new MoviesItems(id, title, overview, releaseDate, voteAverage, popularity, voteCount, posterPath, originalLanguage, poster));
        }
        return moviesList;
    }
}
