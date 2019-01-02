package retro.mobpro.prak.rest;

import retro.mobpro.prak.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search/movie")
    Call<MovieResponse> getMovieSearch(@Query("api_key") String apiKey, @Query("language") String lang, @Query("query") String query);

    @GET("movie/now_playing")
    Call<MovieResponse> getMovieNowPlay(@Query("api_key") String apiKey, @Query("language") String lang);

    @GET("movie/upcoming")
    Call<MovieResponse> getMovieUpComing(@Query("api_key") String apiKey, @Query("language") String lang);


}
