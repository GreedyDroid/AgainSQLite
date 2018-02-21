package com.example.sayed.againsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nurud on 9/28/2017.
 */

public class MovieDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME= "movie database";
    public static final int DATABASE_VERSION= 1;

    public static final String TABLE_MOVIE= "tbl_movie";
    public static final String COL_ID= "col_id";
    public static final String COL_MOVIE_NAME= "movie_name";
    public static final String COL_MOVIE_YEAR= "movie_year";
    public static final String COL_MOVIE_IMG= "movie_img";

//    public static final String CREATE_MOVIE_TABLE="create table "+TABLE_MOVIE+"("+COL_ID+
//            " integer primary key autoincrement, "+COL_MOVIE_NAME+" text, "+COL_MOVIE_YEAR+" text);";
    public static final String CREATE_TABLE_MOVIE = "create table "+TABLE_MOVIE+"("+COL_ID+" integer primary key autoincrement, "+
            COL_MOVIE_NAME+" text, "+COL_MOVIE_YEAR+" text);";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist "+TABLE_MOVIE);
    }
}
