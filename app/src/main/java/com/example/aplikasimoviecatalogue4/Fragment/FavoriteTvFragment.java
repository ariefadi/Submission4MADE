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

import com.example.aplikasimoviecatalogue4.Adapter.FavoriteTvAdapter;
import com.example.aplikasimoviecatalogue4.Database.QueryHelper;
import com.example.aplikasimoviecatalogue4.DetailTvShowActivity;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
import com.example.aplikasimoviecatalogue4.R;
import com.example.aplikasimoviecatalogue4.helper.MappingHelperTv;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment implements LoadTvCallback {

    private ProgressBar progressBar;
    private RecyclerView rvTvShow;
    private FavoriteTvAdapter adapter;
    private QueryHelper queryHelper;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.progressBar);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvShow.setHasFixedSize(true);
        adapter = new FavoriteTvAdapter(getActivity());
        rvTvShow.setAdapter(adapter);

        queryHelper = QueryHelper.getInstance(getActivity().getApplicationContext());
        queryHelper.open();

        if (savedInstanceState == null){
            new LoadTvAsync(queryHelper, this).execute();
        } else {
            ArrayList<TvShowItems> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null){
                adapter.setListTvShow(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList(EXTRA_STATE, adapter.getListTvShow());
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
    public void postExecute(ArrayList<TvShowItems> tvShowItems){

        if (tvShowItems.size() > 0){
            adapter.setListTvShow(tvShowItems);
            progressBar.setVisibility(View.GONE);
        } else {
            adapter.setListTvShow(new ArrayList<TvShowItems>());
            Toast.makeText(getActivity().getApplicationContext(), R.string.data_kosong, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }

    private static class LoadTvAsync extends AsyncTask<Void, Void, ArrayList<TvShowItems>> {

        private final WeakReference<QueryHelper> weakQueryHelper;
        private final WeakReference<LoadTvCallback> weakCallback;

        private LoadTvAsync(QueryHelper queryHelper, LoadTvCallback callback) {
            weakQueryHelper = new WeakReference<>(queryHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<TvShowItems> doInBackground(Void... voids) {
            Cursor dataCursor = weakQueryHelper.get().queryTvAll();
            return MappingHelperTv.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<TvShowItems> tvShowItems) {
            super.onPostExecute(tvShowItems);
            weakCallback.get().postExecute(tvShowItems);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == DetailTvShowActivity.REQUEST_ADD_TV) {
                if (resultCode == DetailTvShowActivity.RESULT_ADD_TV) {
                    TvShowItems tvShowItems = data.getParcelableExtra(DetailTvShowActivity.EXTRA_TV);

                    adapter.addItem(tvShowItems);
                    rvTvShow.smoothScrollToPosition(adapter.getItemCount() - 1);

                    Toast.makeText(getActivity().getApplicationContext(), R.string.data_berhasil_disimpan, Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == DetailTvShowActivity.RESULT_DELETE_TV) {
                int position = data.getIntExtra(DetailTvShowActivity.EXTRA_POSITION_TV, 0);
                adapter.removeItem(position);
                Toast.makeText(getActivity().getApplicationContext(), R.string.data_berhasil_dihapus, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        queryHelper.close();
    }
}

interface LoadTvCallback {
    void preExecute();
    void postExecute(ArrayList<TvShowItems> tvShowItems);
}
