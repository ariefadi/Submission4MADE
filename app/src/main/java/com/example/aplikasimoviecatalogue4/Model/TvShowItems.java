package com.example.aplikasimoviecatalogue4.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TvShowItems implements Parcelable {

    private int id;
    private String original_name;
    private String name;
    private String popularity;
    private String vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_language;
    private String vote_average;
    private String overview;
    private String poster_path;
    // poster
    private String poster;

    public TvShowItems(int id, String original_name, String name, String popularity, String vote_count, String first_air_date, String original_language, String vote_average, String overview, String poster_path, String poster) {
        this.id = id;
        this.original_name = original_name;
        this.name = name;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.original_language = original_language;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
        this.poster = poster;
    }

    public TvShowItems() {

    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public TvShowItems(JSONObject object){
        try {
            int id = object.getInt("id");
            String original_name = object.getString("original_name");
            String name = object.getString("name");
            String popularity = object.getString("popularity");
            String vote_count = object.getString("vote_count");
            String first_air_date = formateDateFromstring("yyyy-MM-dd", "dd MMM yyyy", object.getString("first_air_date"));
            String backdrop_path = object.getString("backdrop_path");
            String original_language = object.getString("original_language");
            String vote_average = object.getString("vote_average");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            String poster = "https://image.tmdb.org/t/p/w300/" +poster_path;

            this.id = id;
            this.original_name = original_name;
            this.name = name;
            this.popularity = popularity;
            this.vote_count = vote_count;
            this.first_air_date = first_air_date;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.vote_average = vote_average;
            this.overview = overview;
            this.poster_path = poster_path;
            this.poster = poster;

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected TvShowItems(Parcel in){
        this.id = in.readInt();;
        this.original_name = in.readString();
        this.name = in.readString();
        this.popularity = in.readString();
        this.vote_count = in.readString();
        this.first_air_date = in.readString();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.vote_average = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.poster = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(original_name);
        dest.writeString(name);
        dest.writeString(popularity);
        dest.writeString(vote_count);
        dest.writeString(first_air_date);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(vote_average);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(poster);
    }

    public static final Creator<TvShowItems> CREATOR = new Creator<TvShowItems>() {
        @Override
        public TvShowItems createFromParcel(Parcel source) {
            return new TvShowItems(source);
        }

        @Override
        public TvShowItems[] newArray(int size) {
            return new TvShowItems[size];
        }
    };

    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {

        }
        return outputDate;

    }
}
