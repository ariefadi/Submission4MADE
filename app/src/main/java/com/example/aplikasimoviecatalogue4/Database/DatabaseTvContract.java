package com.example.aplikasimoviecatalogue4.Database;

import android.provider.BaseColumns;

public class DatabaseTvContract {

    public static String TABLE_NAME_TV = "tv_show";

    public static final class TvColumns implements BaseColumns {

        public static String ID = "id";
        public static String ORIGINAL_NAME = "original_name";
        public static String NAME = "name";
        public static String POPULARITY = "popularity";
        public static String VOTE_COUNT = "vote_count";
        public static String FIRST_AIR_DATE = "first_air_date";
        public static String ORIGINAL_LANGUAGE = "original_language";
        public static String VOTE_AVERAGE = "vote_average";
        public static String OVERVIEW = "overview";
        public static String POSTER_PATH = "poster_path";
        public static String POSTER = "poster";

    }
}
