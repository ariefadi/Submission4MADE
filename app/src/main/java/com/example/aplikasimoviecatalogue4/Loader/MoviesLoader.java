package com.example.aplikasimoviecatalogue4.Loader;

import android.content.Context;

import com.example.aplikasimoviecatalogue4.BuildConfig;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import cz.msebera.android.httpclient.Header;

public class MoviesLoader extends AsyncTaskLoader<ArrayList<MoviesItems>> {

    private ArrayList<MoviesItems> mData;
    private boolean mHasResult = false;

    public MoviesLoader(final Context context){
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
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MoviesItems> moviesItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+ API_KEY +"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++){
                        JSONObject moviesNow = results.getJSONObject(i);
                        MoviesItems movieItems = new MoviesItems(moviesNow);
                        moviesItemses.add(movieItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Jika response gagal maka , do nothing
                

            }
        });
        return moviesItemses;
    }

    protected void onReleaseResources(ArrayList<MoviesItems> data) {
        //nothing to do.
    }
}
