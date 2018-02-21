package com.example.sayed.againsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailinfo extends AppCompatActivity {
    MovieDatabaseSorce movieDatabaseSorce;
    TextView movieNameTV, movieYearTv;
    ImageView movieIV;
    int row_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailinfo);

        movieNameTV = (TextView) findViewById(R.id.movieNameDetailET);
        movieYearTv = (TextView) findViewById(R.id.movieYearDetailET);
        movieIV = (ImageView) findViewById(R.id.movieDetailImgID);

        movieNameTV.setText(getIntent().getStringExtra("name"));
        movieYearTv.setText(getIntent().getStringExtra("year"));
        movieIV.setImageResource(getIntent().getIntExtra("image",0));
        row_id = getIntent().getIntExtra("id",0);
        movieDatabaseSorce = new MovieDatabaseSorce(this);


    }

    public void editMovie(View view) {
        String name = movieNameTV.getText().toString();
        String year = movieYearTv.getText().toString();
        startActivity(new Intent(this, MainActivity.class).putExtra("name", name).putExtra("year", year).putExtra("id", row_id));
    }

    public void deleteMovie(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Item");
        alert.setMessage("Are you sure you want to delete this item");
        alert.setNegativeButton("Cancel", null);
        alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean status = movieDatabaseSorce.deleteMovie(row_id);
                if (status){
                    Toast.makeText(MovieDetailinfo.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MovieDetailinfo.this, MovieList.class));
                }else {
                    Toast.makeText(MovieDetailinfo.this, "UnSuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.show();
    }
}
