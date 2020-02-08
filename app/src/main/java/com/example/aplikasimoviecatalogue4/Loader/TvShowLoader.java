package com.example.aplikasimoviecatalogue4.Loader;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.example.aplikasimoviecatalogue4.BuildConfig;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class TvShowLoader extends AsyncTaskLoader<ArrayList<TvShowItems>> {

    private ArrayList<TvShowItems> mData;
    private boolean mHasResult = false;

    public TvShowLoader(final Context context){
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<TvShowItems> data){
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
    public ArrayList<TvShowItems> loadInBackground() {

        final ArrayList<TvShowItems> tvShowItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key="+ API_KEY +"&language=en-US";
        ANRequest a = AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build();

        ANResponse response = a.executeForString();

        if (response.isSuccess()){
            try {
                JSONObject responseObject = new JSONObject(response.getResult().toString());
                JSONArray results = responseObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++ ){
                    JSONObject tvNow = results.getJSONObject(i);
                    TvShowItems tvShowItems = new TvShowItems(tvNow);
                    tvShowItemses.add(tvShowItems);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.e("Error", response.getError().getErrorDetail());
        }

        return tvShowItemses;
    }

    protected void onReleaseResources(ArrayList<TvShowItems> data) {
        //nothing to do.
    }
}
