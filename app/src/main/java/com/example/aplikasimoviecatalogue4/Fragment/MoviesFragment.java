package com.example.aplikasimoviecatalogue4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.aplikasimoviecatalogue4.Adapter.CardViewMoviesAdapter;
import com.example.aplikasimoviecatalogue4.Loader.MoviesLoader;
import com.example.aplikasimoviecatalogue4.Model.MoviesData;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.example.aplikasimoviecatalogue4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MoviesItems>> {

    RecyclerView rvCategory;
    ArrayList<MoviesItems> searchList = new ArrayList<>();
    CardViewMoviesAdapter adapter;
    ProgressBar loading;
    private final String STATE_LIST = "state_list";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        rvCategory = (RecyclerView)view.findViewById(R.id.lv_item);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CardViewMoviesAdapter(getActivity());
        rvCategory.setAdapter(adapter);
        adapter.setListMovies(searchList);
        adapter.notifyDataSetChanged();

        loading = (ProgressBar)view.findViewById(R.id.progressBar);

        searchList = new ArrayList<>();
        searchList.addAll(MoviesData.getListData());

        if (savedInstanceState == null){
            showRecyclerCardView();
        } else {
            ArrayList<MoviesItems> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            if (stateList != null){
                searchList.addAll(MoviesData.getListData());
            }
        }

        getLoaderManager().initLoader(0,null,this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<MoviesItems>> onCreateLoader(int id, @Nullable Bundle args) {
        loading.setVisibility(View.VISIBLE);
        rvCategory.setVisibility(View.GONE);
        return new MoviesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MoviesItems>> loader, ArrayList<MoviesItems> data) {
        loading.setVisibility(View.GONE);
        rvCategory.setVisibility(View.VISIBLE);
        adapter.setListMovies(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MoviesItems>> loader) {
        adapter.setListMovies(null);
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewMoviesAdapter cardViewMovieAdapter = new CardViewMoviesAdapter(getActivity());
        cardViewMovieAdapter.setListMovies(searchList);
        rvCategory.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST, searchList);
    }
}
