package com.example.sayed.againsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText movieNameET, moveYearET;
    private MovieDatabaseSorce movieDatabaseSorce;
    private Movie movie;

    private String movieNameED, movieYearED;
    private int rowIdED;
    private Button movieAddBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDatabaseSorce = new MovieDatabaseSorce(this);
        movieNameET= (EditText) findViewById(R.id.movieNameET);
        moveYearET = (EditText) findViewById(R.id.movieYearET);

        movieNameED=getIntent().getStringExtra("name");
        movieYearED=getIntent().getStringExtra("year");
        rowIdED=getIntent().getIntExtra("id",0);

        if (rowIdED>0){
            movieAddBT = (Button) findViewById(R.id.addMovieBT);
            movieAddBT.setText("Update");

            movieNameET.setText(movieNameED);
            moveYearET.setText(movieYearED);

        }
    }





    public void addMovie(View view) {
        String name = movieNameET.getText().toString();
        String year = moveYearET.getText().toString();
        if (name.isEmpty()){
            movieNameET.setError("This field must not be empty");
        }else if (year.isEmpty()){
            moveYearET.setError("This field must not be empty");
        }else {
            if (rowIdED>0){
                Movie movie = new Movie(rowIdED, name, year, R.mipmap.ic_launcher_round);
                boolean status = movieDatabaseSorce.editMovie(movie, rowIdED);
                if (status){
                    Toast.makeText(this, "update successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MovieList.class));
                }else {
                    Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }else {

                movie = new Movie(name, year);
                boolean status = movieDatabaseSorce.addMovie(movie);
                if(status){
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MovieList.class));
                }else
                    Toast.makeText(this, "Could not Save", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void movieList(View view) {
        startActivity(new Intent(this, MovieList.class));
    }
}
