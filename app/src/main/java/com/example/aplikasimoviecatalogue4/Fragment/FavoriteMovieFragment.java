package com.example.aplikasimoviecatalogue4.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aplikasimoviecatalogue4.Adapter.FavoriteMovieAdapter;
import com.example.aplikasimoviecatalogue4.Database.QueryHelper;
import com.example.aplikasimoviecatalogue4.DetailMoviesActivity;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.example.aplikasimoviecatalogue4.R;
import com.example.aplikasimoviecatalogue4.helper.MappingHelperMovies;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements LoadMoviesCallback {

    private ProgressBar progressBar;
    private RecyclerView rvMovies;
    private FavoriteMovieAdapter adapter;
    private QueryHelper queryHelper;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_movie, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        progressBar = view.findViewById(R.id.progressBar);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.setHasFixedSize(true);
        adapter = new FavoriteMovieAdapter(getActivity());
        rvMovies.setAdapter(adapter);

        queryHelper = QueryHelper.getInstance(getActivity().getApplicationContext());
        queryHelper.open();

        if (savedInstanceState == null){
            new LoadMoviesAsync(queryHelper, this).execute();
        } else {
            ArrayList<MoviesItems> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null){
                adapter.setListMovies(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList(EXTRA_STATE, adapter.getListMovies());
    }

    @Override
    public void preExecute(){
        getActivity().runOnUiThread(new Runnable(){
           @Override
           public void run(){
               progressBar.setVisibility(View.VISIBLE);
           }
        });
    }

    @Override
    public void postExecute(ArrayList<MoviesItems> moviesItems){

        if (moviesItems.size() > 0){
            adapter.setListMovies(moviesItems);
            progressBar.setVisibility(View.GONE);
        } else {
            adapter.setListMovies(new ArrayList<MoviesItems>());
            Toast.makeText(getActivity().getApplicationContext(), "Data tidak ada saat ini !", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }

    private static class LoadMoviesAsync extends AsyncTask<Void, Void, ArrayList<MoviesItems>> {

        private final WeakReference<QueryHelper> weakMoviesHelper;
        private final WeakReference<LoadMoviesCallback> weakCallback;

        private LoadMoviesAsync(QueryHelper queryHelper, LoadMoviesCallback callback) {
            weakMoviesHelper = new WeakReference<>(queryHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<MoviesItems> doInBackground(Void... voids) {
            Cursor dataCursor = weakMoviesHelper.get().queryAll();
            return MappingHelperMovies.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<MoviesItems> moviesItems) {
            super.onPostExecute(moviesItems);
            weakCallback.get().postExecute(moviesItems);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == DetailMoviesActivity.REQUEST_ADD) {
                if (resultCode == DetailMoviesActivity.RESULT_ADD) {
                    MoviesItems moviesItem = data.getParcelableExtra(DetailMoviesActivity.EXTRA_MOVIES);

                    adapter.addItem(moviesItem);
                    rvMovies.smoothScrollToPosition(adapter.getItemCount() - 1);

                    Toast.makeText(getActivity().getApplicationContext(), "Satu item berhasil ditambahkan ke Favorite !", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == DetailMoviesActivity.RESULT_DELETE) {
                int position = data.getIntExtra(DetailMoviesActivity.EXTRA_POSITION, 0);
                adapter.removeItem(position);
                Toast.makeText(getActivity().getApplicationContext(), "Satu item berhasil dihapus dari Favorite !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        queryHelper.close();
    }
}

interface LoadMoviesCallback {
    void preExecute();
    void postExecute(ArrayList<MoviesItems> moviesItems);
}
