package com.example.aplikasimoviecatalogue4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QueryHelper {
    private static final String DATABASE_MOVIES = DatabaseMoviesContract.TABLE_NAME_MOVIES;
    private static final String DATABASE_TV = DatabaseTvContract.TABLE_NAME_TV;
    private static DatabaseHelper dataBaseHelper;
    private static QueryHelper INSTANCE;

    private static SQLiteDatabase database;

    private QueryHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static QueryHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new QueryHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll() {
        return database.query(DATABASE_MOVIES,
                null,
                null,
                null,
                null,
                null,
                DatabaseMoviesContract.MoviesColumns.ID + " DESC");
    }

    public Cursor queryById(String id) {
        return database.query(DATABASE_MOVIES, null
                , DatabaseMoviesContract.MoviesColumns.ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_MOVIES, null, values);
    }

    public int deleteById(int id) {
        return database.delete(DATABASE_MOVIES, DatabaseMoviesContract.MoviesColumns.ID + " = '" + id + "'", null);
    }

    public Cursor queryTvAll(){
        return database.query(DATABASE_TV,
                null,
                null,
                null,
                null,
                null,
                DatabaseTvContract.TvColumns.ID + " DESC");
    }

    public Cursor queryTvById(String id){
        return database.query(DATABASE_TV,
                null,
                DatabaseTvContract.TvColumns.ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public long insertTv(ContentValues values){
        return database.insert(DATABASE_TV, null, values);
    }

    public int deleteTvById(int id){
        return database.delete(DATABASE_TV, DatabaseTvContract.TvColumns.ID + " = '" + id + "'", null);
    }
}
