package com.example.aplikasimoviecatalogue4.Model;

import java.util.ArrayList;

public class MoviesData {

    static MoviesItems moviesItems = new MoviesItems();

    public static String[][] data = new String[][]{
            {moviesItems.getTitle(), moviesItems.getOverview(), moviesItems.getRelease_date(), moviesItems.getVote_average(), moviesItems.getPopularity(), moviesItems.getVote_count(), moviesItems.getPoster_path(), moviesItems.getBackdrop_path(), moviesItems.getOriginal_title(), moviesItems.getOriginal_language()}
    };

    public static ArrayList<MoviesItems> getListData(){
        MoviesItems moviesItems1 = null;
        ArrayList<MoviesItems> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++){
            moviesItems1 = new MoviesItems();
            moviesItems1.setTitle(data[i][0]);
            moviesItems1.setOverview(data[i][1]);
            moviesItems1.setRelease_date(data[i][2]);
            moviesItems1.setVote_average(data[i][3]);
            moviesItems1.setPopularity(data[i][4]);
            moviesItems1.setVote_count(data[i][5]);
            moviesItems1.setPoster_path(data[i][6]);
            moviesItems1.setBackdrop_path(data[i][7]);
            moviesItems1.setOriginal_language(data[i][8]);
            moviesItems1.setOriginal_title(data[i][9]);

            list.add(moviesItems1);
        }
        return list;
    }
}
