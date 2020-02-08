package com.example.aplikasimoviecatalogue4.helper;

import android.database.Cursor;

import com.example.aplikasimoviecatalogue4.Database.DatabaseMoviesContract;
import com.example.aplikasimoviecatalogue4.Database.DatabaseTvContract;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;

import java.util.ArrayList;

public class MappingHelperTv {

    public static ArrayList<TvShowItems> mapCursorToArrayList(Cursor tvCursor){
        ArrayList<TvShowItems> tvList = new ArrayList<>();

        while (tvCursor.moveToNext()) {
            int id = tvCursor.getInt(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.ID));
            String originalName = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.ORIGINAL_NAME));
            String name = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.NAME));
            String popularity = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.POPULARITY));
            String voteCount = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.VOTE_COUNT));
            String firstAirDate = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.FIRST_AIR_DATE));
            String originalLanguage = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.ORIGINAL_LANGUAGE));
            String voteAverage = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.VOTE_AVERAGE));
            String overview = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.OVERVIEW));
            String posterPath = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseTvContract.TvColumns.POSTER_PATH));
            String poster = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseMoviesContract.MoviesColumns.POSTER));
            tvList.add(new TvShowItems(id, originalName, name, popularity, voteCount, firstAirDate, originalLanguage, voteAverage, overview, posterPath, poster));
        }
        return tvList;
    }
}
