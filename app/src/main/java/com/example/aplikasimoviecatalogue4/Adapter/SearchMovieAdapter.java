package com.example.aplikasimoviecatalogue4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasimoviecatalogue4.CustomOnItemClickListener;
import com.example.aplikasimoviecatalogue4.DetailMoviesActivity;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.example.aplikasimoviecatalogue4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.CardViewViewHolder> {


    private ArrayList<MoviesItems> listMovies;
    LayoutInflater mInflater;
    private Context context;

    public SearchMovieAdapter(Context context, ArrayList<MoviesItems> mData){
        this.listMovies = mData;
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<MoviesItems>getListMovies(){
        return listMovies;
    }

    public void setListMovies(ArrayList<MoviesItems> listMovies){
        this.listMovies = listMovies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchMovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_movies_adapter, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final MoviesItems moviesItems = getListMovies().get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w300/"+moviesItems.getPoster_path())
                .override(350, 350)
                .into(holder.imgMovies);
        holder.tvTitle.setText(moviesItems.getTitle());
        holder.tvOverview.setText(moviesItems.getOverview());
        holder.tvReleaseDate.setText(moviesItems.getRelease_date());

        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {

                MoviesItems movies = new MoviesItems();
                movies.setId(getListMovies().get(position).getId());
                movies.setTitle(getListMovies().get(position).getTitle());
                movies.setOverview(getListMovies().get(position).getOverview());
                movies.setRelease_date(getListMovies().get(position).getRelease_date());
                movies.setPopularity(getListMovies().get(position).getPopularity());
                movies.setVote_average(getListMovies().get(position).getVote_average());
                movies.setVote_count(getListMovies().get(position).getVote_count());
                movies.setPoster(getListMovies().get(position).getPoster());
                movies.setOriginal_language(getListMovies().get(position).getOriginal_language());
                movies.setOriginal_title(getListMovies().get(position).getOriginal_title());
                movies.setPoster_path(getListMovies().get(position).getPoster_path());

                Intent intent = new Intent(context, DetailMoviesActivity.class);
                intent.putExtra(DetailMoviesActivity.EXTRA_MOVIES, movies);
                context.startActivity(intent);

            }
        }));
    }

    @Override
    public int getItemCount() {
        if (listMovies == null) return 0;
        return getListMovies().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgMovies;
        TextView tvTitle, tvOverview, tvReleaseDate;
        Button btnDetail;

        CardViewViewHolder(View itemView){
            super(itemView);
            imgMovies = (ImageView)itemView.findViewById(R.id.img_movies);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvOverview = (TextView)itemView.findViewById(R.id.tv_overview);
            tvReleaseDate = (TextView)itemView.findViewById(R.id.tv_release_date);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);

        }
    }
}
