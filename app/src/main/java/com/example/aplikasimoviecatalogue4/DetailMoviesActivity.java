package com.example.aplikasimoviecatalogue4;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasimoviecatalogue4.Database.DatabaseMoviesContract;
import com.example.aplikasimoviecatalogue4.Database.QueryHelper;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DetailMoviesActivity extends AppCompatActivity {

    private ImageView imgMovies;
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvReleaseDate;
    private TextView tvVoteAverage;
    private TextView tvPopularity;
    private TextView tvVoteCount;
    private TextView tvOriginalLanguage;

    public static final String EXTRA_MOVIES = "extra_movies";

    TextView tvNameTitle, tvReleaseDateTitle, tvOverviewTitle, tvVoteAverageTitle, tvVoteCountTitle, tvPopularityTitle, tvLanguageTitle;

    private MoviesItems moviesItems;
    private QueryHelper queryHelper;
    public boolean isFavorite = true;

    public static final String EXTRA_POSITION = "extra_position";
    public static final int REQUEST_ADD = 100;
    public static final int RESULT_ADD = 101;
    public static final int RESULT_DELETE = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Detail Movies");

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
        Picasso.with(DetailMoviesActivity.this).load(moviesItems.getPoster()).into(imgMovies);
        tvTitle.setText(moviesItems.getTitle());
        tvOverview.setText(moviesItems.getOverview());
        tvReleaseDate.setText(moviesItems.getRelease_date());
        tvVoteAverage.setText(moviesItems.getVote_average());
        tvVoteCount.setText(moviesItems.getVote_count());
        tvPopularity.setText(moviesItems.getVote_count());
        tvOriginalLanguage.setText(moviesItems.getOriginal_language());

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

            Integer id = moviesItems.getId();
            String title = tvTitle.getText().toString().trim();
            String overview = tvOverview.getText().toString().trim();
            String releaseDate = tvReleaseDate.getText().toString().trim();
            String voteAverage = tvVoteAverage.getText().toString().trim();
            String voteCount = tvVoteCount.getText().toString().trim();
            String popularity = tvPopularity.getText().toString().trim();
            String originalLanguage = tvOriginalLanguage.getText().toString().trim();
            String posterPath = moviesItems.getPoster_path();
            String poster = moviesItems.getPoster();

            queryHelper.open();

            Intent intent = new Intent();
            intent.putExtra(EXTRA_MOVIES, moviesItems);

            ContentValues values = new ContentValues();
            values.put(DatabaseMoviesContract.MoviesColumns.ID, id);
            values.put(DatabaseMoviesContract.MoviesColumns.TITLE, title);
            values.put(DatabaseMoviesContract.MoviesColumns.OVERVIEW, overview);
            values.put(DatabaseMoviesContract.MoviesColumns.RELEASE_DATE, releaseDate);
            values.put(DatabaseMoviesContract.MoviesColumns.VOTE_AVERAGE, voteAverage);
            values.put(DatabaseMoviesContract.MoviesColumns.VOTE_COUNT, voteCount);
            values.put(DatabaseMoviesContract.MoviesColumns.POPULARITY, popularity);
            values.put(DatabaseMoviesContract.MoviesColumns.ORIGINAL_LANGUAGE, originalLanguage);
            values.put(DatabaseMoviesContract.MoviesColumns.POSTER_PATH, posterPath);
            values.put(DatabaseMoviesContract.MoviesColumns.POSTER, poster);

            if (isFavorite){
                long result = queryHelper.insert(values);
                if (result > 0){
                    item.setIcon(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(DetailMoviesActivity.this, "Berhasil menambah data ke favorite !", Toast.LENGTH_SHORT).show();
                }
            } else {
                queryHelper.deleteById(moviesItems.getId());
                item.setIcon(R.drawable.ic_favorite_border_black_24dp);
                Toast.makeText(DetailMoviesActivity.this, "Berhasil menghapus data dari favorite !", Toast.LENGTH_SHORT).show();
            }
            isFavorite = !isFavorite;
        }
        return super.onOptionsItemSelected(item);
    }
}
