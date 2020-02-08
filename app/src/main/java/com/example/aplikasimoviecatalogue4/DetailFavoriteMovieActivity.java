package com.example.aplikasimoviecatalogue4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.squareup.picasso.Picasso;

public class DetailFavoriteMovieActivity extends AppCompatActivity {

    private ImageView imgMovies;
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvReleaseDate;
    private TextView tvVoteAverage;
    private TextView tvPopularity;
    private TextView tvVoteCount;
    private TextView tvOriginalLanguage;

    private MoviesItems moviesItems;
    public static final String EXTRA_MOVIES = "favorite_extra_movie";

    TextView tvNameTitle, tvReleaseDateTitle, tvOverviewTitle, tvVoteAverageTitle, tvVoteCountTitle, tvPopularityTitle, tvLanguageTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite_movie);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Detail Favorite Movies");

        // Set Judul
        tvNameTitle = (TextView)findViewById(R.id.tv_name_title);
        tvNameTitle.setText(R.string.judul_film);
        tvReleaseDateTitle = (TextView)findViewById(R.id.tv_release_date_title);
        tvReleaseDateTitle.setText(R.string.tanggal_release);
        tvOverviewTitle = (TextView)findViewById(R.id.tv_overview_title);
        tvOverviewTitle.setText(R.string.deskripsi_film);
        tvVoteAverageTitle = (TextView)findViewById(R.id.tv_vote_average_title);
        tvVoteAverageTitle.setText(R.string.rating);
        tvVoteCountTitle = (TextView)findViewById(R.id.tv_vote_count_title);
        tvVoteCountTitle.setText(R.string.jumlah_tayang);
        tvPopularityTitle = (TextView)findViewById(R.id.tv_popularity_title);
        tvPopularityTitle.setText(R.string.kepopuleran);
        tvLanguageTitle = (TextView)findViewById(R.id.tv_original_language_title);
        tvLanguageTitle.setText(R.string.bahasa);

        imgMovies = (ImageView)findViewById(R.id.img_movies);
        tvTitle = (TextView)findViewById(R.id.tv_name);
        tvOverview = (TextView)findViewById(R.id.tv_overview);
        tvReleaseDate = (TextView)findViewById(R.id.tv_release_date);
        tvVoteAverage = (TextView)findViewById(R.id.tv_vote_average);
        tvPopularity = (TextView)findViewById(R.id.tv_popularity);
        tvVoteCount = (TextView)findViewById(R.id.tv_vote_count);
        tvOriginalLanguage = (TextView)findViewById(R.id.tv_original_language);

        moviesItems = getIntent().getParcelableExtra(EXTRA_MOVIES);
        Picasso.with(DetailFavoriteMovieActivity.this).load(moviesItems.getPoster()).into(imgMovies);
        tvTitle.setText(moviesItems.getTitle());
        tvOverview.setText(moviesItems.getOverview());
        tvReleaseDate.setText(moviesItems.getRelease_date());
        tvVoteAverage.setText(moviesItems.getVote_average());
        tvVoteCount.setText(moviesItems.getVote_count());
        tvPopularity.setText(moviesItems.getVote_count());
        tvOriginalLanguage.setText(moviesItems.getOriginal_language());
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
