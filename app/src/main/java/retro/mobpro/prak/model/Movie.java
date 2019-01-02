package retro.mobpro.prak.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static android.provider.BaseColumns._ID;
import static retro.mobpro.prak.model.DatabaseContract.getColumnString;

public class Movie implements Parcelable{

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("popularity")
    private double popularity;

    public Movie(Cursor cursor) {
        this.id = getColumnString(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.MovieColumns.TITLE);
        this.posterPath = getColumnString(cursor, DatabaseContract.MovieColumns.IMGURL);
        this.releaseDate = getColumnString(cursor, DatabaseContract.MovieColumns.RELDATE);
        this.synopsis = getColumnString(cursor, DatabaseContract.MovieColumns.OVERVIEW);
    }

    Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        synopsis = in.readString();
    }

    public Movie() {
    }

    public Movie(String id, String title, String posterPath, String synopsis, String releaseDate, double popularity) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(synopsis);
    }
}
