package com.example.aplikasimoviecatalogue4.Model;

import java.util.ArrayList;

public class TvShowData {

    static TvShowItems tvShowItems = new TvShowItems();

    public  static String[][] data = new String[][]{
            {tvShowItems.getOriginal_name(), tvShowItems.getName(), tvShowItems.getPopularity(), tvShowItems.getVote_count(), tvShowItems.getFirst_air_date(), tvShowItems.getBackdrop_path(), tvShowItems.getOriginal_language(), tvShowItems.getVote_average(), tvShowItems.getOverview(), tvShowItems.getPoster_path()}
    };

    public static ArrayList<TvShowItems> getListData(){
        TvShowItems tvShowItems1 = null;
        ArrayList<TvShowItems> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++){
            tvShowItems1 = new TvShowItems();
            tvShowItems1.setOriginal_name(data[i][0]);
            tvShowItems1.setName(data[i][1]);
            tvShowItems1.setPopularity(data[i][2]);
            tvShowItems1.setVote_count(data[i][3]);
            tvShowItems1.setFirst_air_date(data[i][4]);
            tvShowItems1.setBackdrop_path(data[i][5]);
            tvShowItems1.setOriginal_language(data[i][6]);
            tvShowItems1.setVote_average(data[i][7]);
            tvShowItems1.setOverview(data[i][8]);
            tvShowItems1.setPoster_path(data[i][9]);

            list.add(tvShowItems1);
        }
        return list;
    }
}
