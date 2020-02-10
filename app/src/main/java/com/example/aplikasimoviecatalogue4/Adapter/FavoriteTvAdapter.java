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
import com.example.aplikasimoviecatalogue4.DetailFavoriteTvActivity;
import com.example.aplikasimoviecatalogue4.DetailTvShowActivity;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
import com.example.aplikasimoviecatalogue4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.CardViewViewHolder> {

    private ArrayList<TvShowItems> listTvShow = new ArrayList<>();
    private Context context;

    public FavoriteTvAdapter(Context context){
        this.context = context;
    }

    public ArrayList<TvShowItems>getListTvShow(){
        return listTvShow;
    }

    public void setListTvShow(ArrayList<TvShowItems> listTvShow){

        if (listTvShow.size() > 0) {
            this.listTvShow.clear();
        }
        this.listTvShow.addAll(listTvShow);
        notifyDataSetChanged();
    }

    public void addItem(TvShowItems tvShowItems) {
        this.listTvShow.add(tvShowItems);
        notifyItemInserted(listTvShow.size() - 1);
    }
    public void removeItem(int position) {
        this.listTvShow.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listTvShow.size());
    }

    @NonNull
    @Override
    public FavoriteTvAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_tv_item, parent, false);
        FavoriteTvAdapter.CardViewViewHolder viewHolder = new FavoriteTvAdapter.CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteTvAdapter.CardViewViewHolder holder, int position) {

        final TvShowItems tvShowItems = getListTvShow().get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w300/"+tvShowItems.getPoster_path())
                .override(350, 350)
                .into(holder.imgTvShow);
        holder.tvName.setText(tvShowItems.getName());
        holder.tvOverview.setText(tvShowItems.getOverview());
        holder.tvFirstAirDate.setText(tvShowItems.getFirst_air_date());
        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {

                TvShowItems tv = new TvShowItems();
                tv.setId(getListTvShow().get(position).getId());
                tv.setName(getListTvShow().get(position).getName());
                tv.setOverview(getListTvShow().get(position).getOverview());
                tv.setFirst_air_date(getListTvShow().get(position).getFirst_air_date());
                tv.setPopularity(getListTvShow().get(position).getPopularity());
                tv.setVote_average(getListTvShow().get(position).getVote_average());
                tv.setVote_count(getListTvShow().get(position).getVote_count());
                tv.setOriginal_language(getListTvShow().get(position).getOriginal_language());
                tv.setOriginal_name(getListTvShow().get(position).getOriginal_name());
                tv.setPoster(getListTvShow().get(position).getPoster());
                tv.setPoster_path(getListTvShow().get(position).getPoster_path());

                Intent intent = new Intent(context, DetailFavoriteTvActivity.class);
                intent.putExtra(DetailFavoriteTvActivity.EXTRA_TV, tv);
                context.startActivity(intent);

            }
        }));
        holder.btnDelete.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                holder.queryHelper.deleteTvById(tvShowItems.getId());
                Toast.makeText(context, R.string.data_berhasil_dihapus, Toast.LENGTH_SHORT).show();
                removeItem(position);
            }
        }));
    }

    @Override
    public int getItemCount() {
        if (listTvShow == null) return 0;
        return getListTvShow().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTvShow;
        TextView tvName, tvOverview, tvFirstAirDate;
        Button btnDetail,btnDelete;
        QueryHelper queryHelper;

        CardViewViewHolder(View itemView){
            super(itemView);
            imgTvShow = (ImageView)itemView.findViewById(R.id.img_tv_show);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvOverview = (TextView)itemView.findViewById(R.id.tv_overview);
            tvFirstAirDate = (TextView)itemView.findViewById(R.id.tv_first_air_date);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);
            btnDelete = (Button)itemView.findViewById(R.id.btn_set_hapus);
            queryHelper = QueryHelper.getInstance(context);
            queryHelper.open();
        }
    }
}
