package com.example.aplikasimoviecatalogue4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
import com.squareup.picasso.Picasso;

public class DetailFavoriteTvActivity extends AppCompatActivity {

    private ImageView imgTvShow;
    private TextView tvName;
    private TextView tvOverview;
    private TextView tvFirstAirDate;
    private TextView tvVoteAverage;
    private TextView tvPopularity;
    private TextView tvVoteCount;
    private TextView tvOriginalLanguage;

    public static final String EXTRA_TV = "extra_favorite_tv";

    TextView tvNameTitle, tvFirstAirDateTitle, tvOverviewTitle, tvVoteAverageTitle, tvVoteCountTitle, tvPopularityTitle, tvLanguageTitle;
    private TvShowItems tvShowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite_tv);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Detail Favorite TV Show");

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
        Picasso.with(DetailFavoriteTvActivity.this).load(tvShowItems.getPoster()).into(imgTvShow);
        tvName.setText(tvShowItems.getName());
        tvOverview.setText(tvShowItems.getOverview());
        tvFirstAirDate.setText(tvShowItems.getFirst_air_date());
        tvVoteAverage.setText(tvShowItems.getVote_average());
        tvVoteCount.setText(tvShowItems.getVote_count());
        tvPopularity.setText(tvShowItems.getVote_count());
        tvOriginalLanguage.setText(tvShowItems.getOriginal_language());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
