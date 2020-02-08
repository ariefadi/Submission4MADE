package com.example.aplikasimoviecatalogue4.Database;

import android.provider.BaseColumns;

public class DatabaseMoviesContract {

    public static String TABLE_NAME_MOVIES = "movies";

    public static final class MoviesColumns implements BaseColumns {

        public static String ID = "id";
        public static String TITLE = "title";
        public static String OVERVIEW = "overview";
        public static String RELEASE_DATE = "release_date";
        public static String VOTE_AVERAGE = "vote_average";
        public static String POPULARITY = "popularity";
        public static String VOTE_COUNT = "vote_count";
        public static String POSTER_PATH = "poster_path";
        public static String ORIGINAL_LANGUAGE = "original_language";
        public static String POSTER = "poster";

    }

}
