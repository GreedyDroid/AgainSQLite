package com.example.sayed.againsqlite;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by nurud on 9/28/2017.
 */

public class MovieDatabaseSorce {
    private MovieDatabaseHelper movieDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public MovieDatabaseSorce(Context context) {
        movieDatabaseHelper = new MovieDatabaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }

    public boolean addMovie(Movie movie){
        this.open();
        ContentValues values = new ContentValues();
        values.put(movieDatabaseHelper.COL_MOVIE_NAME, movie.getMovieName());
        values.put(movieDatabaseHelper.COL_MOVIE_YEAR, movie.getMovieYear());

        long row_id = sqLiteDatabase.insert(movieDatabaseHelper.TABLE_MOVIE, null,values);
        this.close();
        if (row_id>0){
            return true;
        }else {
            return false;
        }
    }

    public ArrayList<Movie> getMovieList(){
        ArrayList<Movie>movies = new ArrayList<>();
        this.open();

        Cursor cursor = sqLiteDatabase.query(MovieDatabaseHelper.TABLE_MOVIE, null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                int id= cursor.getInt(cursor.getColumnIndex(MovieDatabaseHelper.COL_ID));
                String movieName= cursor.getString(cursor.getColumnIndex(movieDatabaseHelper.COL_MOVIE_NAME));
                String movieyear = cursor.getString(cursor.getColumnIndex(movieDatabaseHelper.COL_MOVIE_YEAR));
                //int movieImg= cursor.getInt(cursor.getColumnIndex(String.valueOf(MovieDatabaseHelper.COL_MOVIE_IMG)));
                Movie movie = new Movie(id, movieName, movieyear, R.mipmap.ic_launcher_round);

                movies.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return movies;
    }

    public boolean editMovie(Movie movie, int row_id){
        this.open();
        ContentValues values = new ContentValues();
        values.put(MovieDatabaseHelper.COL_ID, movie.getMovieID());
        values.put(movieDatabaseHelper.COL_MOVIE_NAME, movie.getMovieName());
        values.put(MovieDatabaseHelper.COL_MOVIE_YEAR, movie.getMovieYear());

        int updated = sqLiteDatabase.update(MovieDatabaseHelper.TABLE_MOVIE, values, MovieDatabaseHelper.COL_ID+" = ?", new String[] {String.valueOf(row_id)});
        if (updated>0){
            return true;
        }else {
            return false;
        }
    }


    public boolean deleteMovie(int row_id){
        this.open();
        int deleted = sqLiteDatabase.delete(movieDatabaseHelper.TABLE_MOVIE, movieDatabaseHelper.COL_ID+" = ?", new String[] {Integer.toString(row_id)});
        this.close();
        if (deleted>0){
            return true;
        }else {
            return false;
        }
    }
}
