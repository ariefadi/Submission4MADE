package com.example.aplikasimoviecatalogue4;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasimoviecatalogue4.Database.DatabaseTvContract;
import com.example.aplikasimoviecatalogue4.Database.QueryHelper;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DetailTvShowActivity extends AppCompatActivity {

    private ImageView imgTvShow;
    private TextView tvName;
    private TextView tvOverview;
    private TextView tvFirstAirDate;
    private TextView tvVoteAverage;
    private TextView tvPopularity;
    private TextView tvVoteCount;
    private TextView tvOriginalLanguage;

    public static final String EXTRA_TV = "extra_tv";

    TextView tvNameTitle, tvFirstAirDateTitle, tvOverviewTitle, tvVoteAverageTitle, tvVoteCountTitle, tvPopularityTitle, tvLanguageTitle;

    private TvShowItems tvShowItems;
    private QueryHelper queryHelper;
    public boolean isFavoriteTv = true;

    public static final String EXTRA_POSITION_TV = "extra_position";
    public static final int REQUEST_ADD_TV = 100;
    public static final int RESULT_ADD_TV = 101;
    public static final int RESULT_DELETE_TV = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Detail TV Show");

        // Set Judul
        tvNameTitle = (TextView)findViewById(R.id.tv_name_title);
        tvNameTitle.setText(R.string.nama_tv_show);
        tvFirstAirDateTitle = (TextView)findViewById(R.id.tv_first_air_date_title);
        tvFirstAirDateTitle.setText(R.string.tanggal_tayang);
        tvOverviewTitle = (TextView)findViewById(R.id.tv_overview_title);
        tvOverviewTitle.setText(R.string.deskripsi_tv_show);
        tvVoteAverageTitle = (TextView)findViewById(R.id.tv_vote_average_title);
        tvVoteAverageTitle.setText(R.string.rating_tv);
        tvVoteCountTitle = (TextView)findViewById(R.id.tv_vote_count_title);
        tvVoteCountTitle.setText(R.string.jumlah_tayang_tv);
        tvPopularityTitle = (TextView)findViewById(R.id.tv_popularity_title);
        tvPopularityTitle.setText(R.string.kepopuleran_tv);
        tvLanguageTitle = (TextView)findViewById(R.id.tv_original_language_title);
        tvLanguageTitle.setText(R.string.bahasa_tv);


        imgTvShow = (ImageView)findViewById(R.id.img_tv_show);
        tvName = (TextView)findViewById(R.id.tv_name);
        tvOverview = (TextView)findViewById(R.id.tv_overview);
        tvFirstAirDate = (TextView)findViewById(R.id.tv_first_air_date);
        tvVoteAverage = (TextView)findViewById(R.id.tv_vote_average);
        tvPopularity = (TextView)findViewById(R.id.tv_popularity);
        tvVoteCount = (TextView)findViewById(R.id.tv_vote_count);
        tvOriginalLanguage = (TextView)findViewById(R.id.tv_original_language);

        tvShowItems = getIntent().getParcelableExtra(EXTRA_TV);
        Picasso.with(DetailTvShowActivity.this).load(tvShowItems.getPoster()).into(imgTvShow);
        tvName.setText(tvShowItems.getName());
        tvOverview.setText(tvShowItems.getOverview());
        tvFirstAirDate.setText(tvShowItems.getFirst_air_date());
        tvVoteAverage.setText(tvShowItems.getVote_average());
        tvVoteCount.setText(tvShowItems.getVote_count());
        tvPopularity.setText(tvShowItems.getVote_count());
        tvOriginalLanguage.setText(tvShowItems.getOriginal_language());

        queryHelper = QueryHelper.getInstance(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_save_favorite:

                Integer id = tvShowItems.getId();
                String originalName = tvShowItems.getOriginal_name();
                String name = tvName.getText().toString().trim();
                String popularity = tvPopularity.getText().toString().trim();
                String voteCount = tvVoteCount.getText().toString().trim();
                String firstAirDate = tvFirstAirDate.getText().toString().trim();
                String originalLanguage = tvOriginalLanguage.getText().toString().trim();
                String voteAverage = tvVoteAverage.getText().toString().trim();
                String overview = tvOverview.getText().toString().trim();
                String posterPath = tvShowItems.getPoster_path();
                String poster = tvShowItems.getPoster();

                queryHelper.open();

                Intent intent = new Intent();
                intent.putExtra(EXTRA_TV, tvShowItems);

                ContentValues values = new ContentValues();
                values.put(DatabaseTvContract.TvColumns.ID, id);
                values.put(DatabaseTvContract.TvColumns.ORIGINAL_NAME, originalName);
                values.put(DatabaseTvContract.TvColumns.NAME, name);
                values.put(DatabaseTvContract.TvColumns.POPULARITY, popularity);
                values.put(DatabaseTvContract.TvColumns.VOTE_COUNT, voteCount);
                values.put(DatabaseTvContract.TvColumns.FIRST_AIR_DATE, firstAirDate);
                values.put(DatabaseTvContract.TvColumns.ORIGINAL_LANGUAGE, originalLanguage);
                values.put(DatabaseTvContract.TvColumns.VOTE_AVERAGE, voteAverage);
                values.put(DatabaseTvContract.TvColumns.OVERVIEW, overview);
                values.put(DatabaseTvContract.TvColumns.POSTER_PATH, posterPath);
                values.put(DatabaseTvContract.TvColumns.POSTER, poster);

                if (isFavoriteTv){
                    long result = queryHelper.insertTv(values);
                    if (result > 0){
                        item.setIcon(R.drawable.ic_favorite_black_24dp);
                        Toast.makeText(DetailTvShowActivity.this, R.string.data_berhasil_disimpan, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    queryHelper.deleteTvById(tvShowItems.getId());
                    item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(DetailTvShowActivity.this, R.string.data_berhasil_dihapus, Toast.LENGTH_SHORT).show();
                }
                isFavoriteTv = !isFavoriteTv;
        }
        return super.onOptionsItemSelected(item);
    }
}
