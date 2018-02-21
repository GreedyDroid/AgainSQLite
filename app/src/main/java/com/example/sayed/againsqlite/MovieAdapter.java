package com.example.sayed.againsqlite;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MovieAdapter extends ArrayAdapter<Movie>{
    private ArrayList<Movie>movies;
    private Context context;

    public MovieAdapter(@NonNull Context context, ArrayList<Movie> movies) {
        super(context, R.layout.row_layout, movies);
        this.context=context;
        this.movies=movies;
    }

    public class MovieViewHolder{
        private TextView movieNameTV, movieYearTV;
        private ImageView movieImageIV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MovieViewHolder movieViewHolder;
        if (convertView == null){
            movieViewHolder = new MovieViewHolder();
            convertView = inflater.inflate(R.layout.row_layout, parent, false);

            movieViewHolder.movieNameTV= convertView.findViewById(R.id.movieNameID);
            movieViewHolder.movieYearTV=convertView.findViewById(R.id.movieYearID);
            movieViewHolder.movieImageIV=convertView.findViewById(R.id.movieImgID);

            convertView.setTag(movieViewHolder);
        }else{
            movieViewHolder = (MovieViewHolder) convertView.getTag();
        }

        movieViewHolder.movieNameTV.setText(movies.get(position).getMovieName());
        movieViewHolder.movieYearTV.setText(movies.get(position).getMovieYear());
        movieViewHolder.movieImageIV.setImageResource(movies.get(position).getMovieImg());

        return convertView;
    }
}
