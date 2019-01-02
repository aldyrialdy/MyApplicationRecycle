package retro.mobpro.prak.myapplicationrecycle;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import retro.mobpro.prak.model.Movie;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static List<Movie> movies;
    private int rowLayout;
    private final ThreadLocal<Context> context = new ThreadLocal<Context>();
    private int statusdb;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        TextView movieDesc;
        TextView movieRelease;
        ImageView movieImage;
        TextView moviePopularity;
        Button btnDetail;
        Button btnShare;


        public MovieViewHolder(View v) {
            super(v);
            movieTitle = v.findViewById(R.id.tv_title);
            movieDesc = v.findViewById(R.id.tv_detail);
            movieRelease = v.findViewById(R.id.tv_date);
            moviePopularity = v.findViewById(R.id.tv_popular);
            movieImage = v.findViewById(R.id.img_movie);
            btnDetail = v.findViewById(R.id.btn_detail);
            btnShare = v.findViewById(R.id.btn_share);
        }
    }

    public MovieAdapter(List<Movie> movies, Context context, int x) {
        this.movies = movies;
        this.context.set(context);
        this.statusdb = x;
    }

    public void refill(List<Movie> movies) {
        this.movies = new ArrayList<>();
        this.movies.addAll(movies);

        notifyDataSetChanged();
    }

    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, null, false);
//        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(layoutParams);
        return new MovieViewHolder(view);
    }


    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieDesc.setText(movies.get(position).getSynopsis());
        holder.movieRelease.setText(movies.get(position).getReleaseDate());
        holder.moviePopularity.setText(Double.toString(movies.get(position).getPopularity()));

        String urlimage = "";
        if (movies.get(position).getPosterPath() != null) {
            if(statusdb==1){
                urlimage = movies.get(position).getPosterPath().toString();
                Uri myUri = Uri.parse(urlimage);
                Glide.with(this.context.get())
                        .load(myUri)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.capamerica))
                        .into(holder.movieImage);
            }else{
                urlimage = MainActivity.baseUrlImage + movies.get(position).getPosterPath().toString();
                Uri myUri = Uri.parse(urlimage);
                Glide.with(this.context.get())
                        .load(myUri)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.capamerica))
                        .into(holder.movieImage);
            }
//            urlimage = MainActivity.baseUrlImage + movies.get(position).getPosterPath().toString();
//            Uri myUri = Uri.parse(urlimage);
//            Glide.with(this.context)
//                    .load(myUri)
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.capamerica))
//                    .into(holder.movieImage);

        } else {
            holder.movieImage.setImageDrawable(null);
        }

    }

    public int getItemCount() {
        return movies.size();
    }

    public static List<Movie> getMovies() {
        return movies;
    }
}
