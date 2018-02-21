package com.example.sayed.againsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {

    private ArrayList<Movie>movies;
    private MovieAdapter movieAdapter;
    private ListView movieLV;
    private MovieDatabaseSorce movieDatabaseSorce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movieDatabaseSorce = new MovieDatabaseSorce(this);
        movieLV = (ListView) findViewById(R.id.movieLVid);
        movies = movieDatabaseSorce.getMovieList();

        movieAdapter = new MovieAdapter(this, movies);
        movieLV.setAdapter(movieAdapter);
        
        movieLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = movies.get(i).getMovieName();
                String year = movies.get(i).getMovieYear();
                int id = movies.get(i).getMovieID();
                int image = movies.get(i).getMovieImg();

                startActivity(new Intent(MovieList.this, MovieDetailinfo.class).putExtra("name", name).putExtra("year", year)
                        .putExtra("id", id).putExtra("image", image));
            }
        });
    }
}
