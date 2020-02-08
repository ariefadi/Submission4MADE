package com.example.aplikasimoviecatalogue4.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.aplikasimoviecatalogue4.Adapter.CardViewTvShowAdapter;
import com.example.aplikasimoviecatalogue4.Loader.TvShowLoader;
import com.example.aplikasimoviecatalogue4.Model.TvShowData;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
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
public class TvShowFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<TvShowItems>> {

    RecyclerView rvCategory;
    ArrayList<TvShowItems> searchList = new ArrayList<>();
    CardViewTvShowAdapter adapter;
    ProgressBar loading;
    private final String STATE_LIST_TV = "state_list_tv";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rvCategory = (RecyclerView)view.findViewById(R.id.lv_tv_show);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CardViewTvShowAdapter(getActivity());
        rvCategory.setAdapter(adapter);
        adapter.setListTvShow(searchList);
        adapter.notifyDataSetChanged();

        loading = (ProgressBar)view.findViewById(R.id.progressBar);

        searchList = new ArrayList<>();
        searchList.addAll(TvShowData.getListData());
        getLoaderManager().initLoader(0,null,this);
        if (savedInstanceState == null){
            showRecyclerCardView();
        } else {
            ArrayList<TvShowItems> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST_TV);
            if (stateList != null){
                searchList.addAll(TvShowData.getListData());
            }
        }
        showRecyclerCardView();
    }

    @NonNull
    @Override
    public Loader<ArrayList<TvShowItems>> onCreateLoader(int id, @Nullable Bundle args) {
        loading.setVisibility(View.VISIBLE);
        rvCategory.setVisibility(View.GONE);
        return new TvShowLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<TvShowItems>> loader, ArrayList<TvShowItems> data) {
        loading.setVisibility(View.GONE);
        rvCategory.setVisibility(View.VISIBLE);
        adapter.setListTvShow(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<TvShowItems>> loader) {
        adapter.setListTvShow(null);
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardViewTvShowAdapter cardViewTvShowAdapter = new CardViewTvShowAdapter(getActivity());
        cardViewTvShowAdapter.setListTvShow(searchList);
        rvCategory.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST_TV, searchList);
    }
}
