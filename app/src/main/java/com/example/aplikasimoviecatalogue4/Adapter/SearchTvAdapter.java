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
import com.example.aplikasimoviecatalogue4.DetailTvShowActivity;
import com.example.aplikasimoviecatalogue4.Model.TvShowItems;
import com.example.aplikasimoviecatalogue4.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchTvAdapter extends RecyclerView.Adapter<SearchTvAdapter.CardViewViewHolder> {



    private ArrayList<TvShowItems> listTvShow;
    LayoutInflater mInflater;
    private Context context;

    public SearchTvAdapter(Context context,  ArrayList<TvShowItems> mData){
        this.listTvShow = mData;
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<TvShowItems>getListTvShow(){
        return listTvShow;
    }

    public void setListTvShow(ArrayList<TvShowItems> listTvShow){
        this.listTvShow = listTvShow;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchTvAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_tv_show_adapter, parent, false);
        SearchTvAdapter.CardViewViewHolder viewHolder = new SearchTvAdapter.CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
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

                Intent intent = new Intent(context, DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRA_TV, tv);
                context.startActivity(intent);

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
        Button btnDetail;

        CardViewViewHolder(View itemView){
            super(itemView);
            imgTvShow = (ImageView)itemView.findViewById(R.id.img_tv_show);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvOverview = (TextView)itemView.findViewById(R.id.tv_overview);
            tvFirstAirDate = (TextView)itemView.findViewById(R.id.tv_first_air_date);
            btnDetail = (Button)itemView.findViewById(R.id.btn_set_detail);

        }
    }
}
