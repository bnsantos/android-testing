package com.bnsantos.test.support.library;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bnsantos.test.support.library.model.Movie;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bruno on 08/01/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder> {
    final SortedList<Movie> mList;
    private Gson gson;

    public MovieAdapter() {
        mList = new SortedList<>(Movie.class, new SortedListAdapterCallback<Movie>(this) {
            @Override
            public int compare(Movie o1, Movie o2) {
                Date d1 = o1.getTheaterReleaseDate();
                Date d2 = o2.getTheaterReleaseDate();
                if(d1!=null){
                    return d1.compareTo(d2);
                }else if(d2!=null){
                    return d2.compareTo(d1);
                }else{
                    return 0;
                }
            }

            @Override
            public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areItemsTheSame(Movie item1, Movie item2) {
                return item1.getId().equals(item2.getId());
            }
        });
    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(List<Movie> movieList){
        mList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void clear(){
        mList.clear();
        notifyDataSetChanged();
    }

    public static class MovieItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView release;

        public MovieItemViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            release = (TextView) itemView.findViewById(R.id.release);
        }

        public void onBind(Movie movie){
            title.setText(movie.getTitle());
            release.setText(SimpleDateFormat.getDateInstance().format(movie.getTheaterReleaseDate()));
        }
    }
}
