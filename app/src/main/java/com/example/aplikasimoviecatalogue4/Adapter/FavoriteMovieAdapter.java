package com.example.aplikasimoviecatalogue4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasimoviecatalogue4.CustomOnItemClickListener;
import com.example.aplikasimoviecatalogue4.Database.QueryHelper;
import com.example.aplikasimoviecatalogue4.DetailFavoriteMovieActivity;
import com.example.aplikasimoviecatalogue4.DetailMoviesActivity;
import com.example.aplikasimoviecatalogue4.Model.MoviesItems;
import com.example.aplikasimoviecatalogue4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.CardViewViewHolder> {

    private ArrayList<MoviesItems> listMovies = new ArrayList<>();
    private Context context;

    public FavoriteMovieAdapter(Context context){
        this.context = context;
    }

    public ArrayList<MoviesItems>getListMovies(){
        return listMovies;
    }

    public void setListMovies(ArrayList<MoviesItems> listMovies){

        if (listMovies.size() > 0) {
            this.listMovies.clear();
        }
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    public void addItem(MoviesItems moviesItems) {
        this.listMovies.add(moviesItems);
        notifyItemInserted(listMovies.size() - 1);
    }
    public void removeItem(int position) {
        this.listMovies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listMovies.size());
    }

    @NonNull
    @Override
    public FavoriteMovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_movie_item, parent, false);
        FavoriteMovieAdapter.CardViewViewHolder viewHolder = new FavoriteMovieAdapter.CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteMovieAdapter.CardViewViewHolder holder, int position) {

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

                Intent intent = new Intent(context, DetailFavoriteMovieActivity.class);
                intent.putExtra(DetailFavoriteMovieActivity.EXTRA_MOVIES, movies);
                context.startActivity(intent);

            }
        }));
        holder.btnDelete.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                holder.queryHelper.deleteById(moviesItems.getId());
                Toast.makeText(context, "Berhasil menghapus data dari favorite !", Toast.LENGTH_SHORT).show();
                removeItem(position);
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
        Button btnDetail,btnDelete;
        QueryHelper queryHelper;

        CardViewViewHolder(View itemView){
            super(itemView);
            imgMovies = (ImageView)itemView.findViewById(R.id.img_movies);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvOverview = (TextView)itemView.findViewById(R.id.tv_overview);
            tvReleaseDate = (TextView)itemView.findViewById(R.id.tv_release_date);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);
            btnDelete = (Button)itemView.findViewById(R.id.btn_set_hapus);
            queryHelper = QueryHelper.getInstance(context);
            queryHelper.open();
        }
    }
}
