package com.example.aplikasimoviecatalogue4.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.aplikasimoviecatalogue4.Adapter.CardViewTvShowAdapter;
import com.example.aplikasimoviecatalogue4.Adapter.SearchTvAdapter;
import com.example.aplikasimoviecatalogue4.Loader.TvShowLoader;
import com.example.aplikasimoviecatalogue4.Model.TvShowData;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
import com.example.aplikasimoviecatalogue4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
    ArrayList mData;
    SearchTvAdapter adapterSearchTvAdapter;
    String searchTvShow;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private SearchView.OnCloseListener closeListener;

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

        adapterSearchTvAdapter = new SearchTvAdapter(getActivity(), mData);
        rvCategory.setAdapter(adapterSearchTvAdapter);
        adapterSearchTvAdapter.setListTvShow(searchList);
        adapterSearchTvAdapter.notifyDataSetChanged();

        loading = (ProgressBar)view.findViewById(R.id.progressBar);

        String tvShow = searchTvShow;
        Bundle bundle = new Bundle();
        bundle.putString(STATE_LIST_TV, tvShow);

        searchList = new ArrayList<>();
        searchList.addAll(TvShowData.getListData());

        if (savedInstanceState == null){
            showRecyclerCardView();
        } else {
            ArrayList<TvShowItems> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST_TV);
            if (stateList != null){
                searchList.addAll(TvShowData.getListData());
            }
        }

        getLoaderManager().initLoader(0,bundle,this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setting_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {

                    searchTvShow = !TextUtils.isEmpty(query) ? query : null;
                    String movie = searchTvShow;
                    Bundle bundle = new Bundle();
                    bundle.putString(STATE_LIST_TV, movie);
                    getLoaderManager().restartLoader(0, bundle, TvShowFragment.this);
                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        } else {
            closeListener = new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    searchView.onActionViewCollapsed();
                    return true;
                }
            };
            searchView.setOnCloseListener(closeListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<ArrayList<TvShowItems>> onCreateLoader(int id, @Nullable Bundle args) {
        loading.setVisibility(View.VISIBLE);
        rvCategory.setVisibility(View.GONE);
        String kumpulanTvShow = "";
        if (args != null){
            kumpulanTvShow = args.getString(STATE_LIST_TV);
        }
        return new TvShowLoader(getActivity(), kumpulanTvShow);
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
