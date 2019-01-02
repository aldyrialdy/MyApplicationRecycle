package retro.mobpro.prak.model;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_FAV = "favourite";

    public static final class MovieColumns implements BaseColumns {
        //Movie title
        public static String TITLE = "title";
        //Movie description
        public static String OVERVIEW = "overview";
        //Movie date
        public static String RELDATE = "reldate";
        //Movie Popularity
        public static String POPULARITY = "popular";
        //Movie URL Image
        public static String IMGURL = "imageurl";
        //Movie Fav
        public static String FAVOURITE = "favourite";
    }

    public static final String AUTHORITY = "com.dicoding.associate.madesubmission2";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAV)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble( cursor.getColumnIndex(columnName) );
    }
}
