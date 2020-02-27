package com.example.aplikasimoviecatalogue4.Loader;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.example.aplikasimoviecatalogue4.BuildConfig;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MoviesLoader extends AsyncTaskLoader<ArrayList<MoviesItems>> {

    private ArrayList<MoviesItems> mData;
    private boolean mHasResult = false;

    private String mKumpulanData;

    public MoviesLoader(final Context context, String kumpulanData){
        super(context);
        onContentChanged();
        this.mKumpulanData = kumpulanData;
    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MoviesItems> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset(){
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = BuildConfig.MD_API_KEY;

    @Nullable
    @Override
    public ArrayList<MoviesItems> loadInBackground() {

        final ArrayList<MoviesItems> moviesItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+ API_KEY +"&language=en-US&query=" + mKumpulanData;
        ANRequest a = AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build();

        ANResponse response = a.executeForString();

        if (response.isSuccess()){
            try {
                JSONObject responseObject = new JSONObject(response.getResult().toString());
                JSONArray results = responseObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++){
                    JSONObject moviesNow = results.getJSONObject(i);
                    MoviesItems moviesItems = new MoviesItems(moviesNow);
                    moviesItemses.add(moviesItems);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Log.e("Error", response.getError().getErrorDetail());
        }

        return moviesItemses;
    }

    protected void onReleaseResources(ArrayList<MoviesItems> data) {
        //nothing to do.
    }
}
