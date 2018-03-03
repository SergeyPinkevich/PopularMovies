package com.innopolis.sergeypinkevich.popularmovies.feature.main;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innopolis.sergeypinkevich.popularmovies.R;
import com.innopolis.sergeypinkevich.popularmovies.model.Movie;
import com.innopolis.sergeypinkevich.popularmovies.repository.RemoteRepositoryImpl;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sergey Pinkevich
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private MovieAdapterClickListener clickListener;
    private List<Movie> movies;

    public MovieAdapter(MovieAdapterClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_poster)
        ImageView moviePoster;
        @BindView(R.id.movie_title)
        TextView movieTitle;
        @BindView(R.id.movie_rating)
        TextView movieRating;

        CardView cardView;

        public MovieViewHolder(CardView v, MovieAdapterClickListener listener) {
            super(v);
            cardView = v;
            ButterKnife.bind(this, v);

            clickListener = listener;
            cardView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            Picasso.with(cardView.getContext()).load(RemoteRepositoryImpl.IMAGE_PATH + movie.getPosterPath()).into(moviePoster);
            movieTitle.setText(movie.getTitle());
            movieRating.setText(String.valueOf(movie.getVoteAverage()));
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }
}
