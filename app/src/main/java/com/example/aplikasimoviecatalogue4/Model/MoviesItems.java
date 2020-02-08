package com.example.aplikasimoviecatalogue4.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoviesItems implements Parcelable {

    private int id;
    private String title;
    private String overview;
    private String release_date;
    private String vote_average;
    private String popularity;
    private String vote_count;
    private String poster_path;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    // poster
    private String poster;

    public MoviesItems(int id, String title, String overview, String release_date, String vote_average, String popularity, String vote_count, String poster_path, String original_language, String poster) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.poster = poster;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
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

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public MoviesItems(){

    }

    public MoviesItems(JSONObject object){
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release_date = formateDateFromstring("yyyy-MM-dd", "dd MMM yyyy", object.getString("release_date"));
            String vote_average = object.getString("vote_average");
            String popularity = object.getString("popularity");
            String vote_count = object.getString("vote_count");
            String poster_path = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");
            String original_language = object.getString("original_language");
            String original_title = object.getString("original_title");
            String poster = "https://image.tmdb.org/t/p/w300/" +poster_path;


            this.id = id;
            this.title = title;
            this.overview = overview;
            this.release_date = release_date;
            this.vote_average = vote_average;
            this.popularity = popularity;
            this.vote_count = vote_count;
            this.poster_path = poster_path;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.original_title = original_title;
            this.poster = poster;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected MoviesItems(Parcel in){
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.vote_average = in.readString();
        this.popularity = in.readString();
        this.vote_count = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.poster = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(vote_average);
        dest.writeString(popularity);
        dest.writeString(vote_count);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(poster);

    }

    public static final Creator<MoviesItems> CREATOR = new Parcelable.Creator<MoviesItems>() {
        @Override
        public MoviesItems createFromParcel(Parcel source) {
            return new MoviesItems(source);
        }

        @Override
        public MoviesItems[] newArray(int size) {
            return new MoviesItems[size];
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
