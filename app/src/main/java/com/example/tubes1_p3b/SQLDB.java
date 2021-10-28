package com.example.tubes1_p3b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLDB extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "StorageFilm.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "StorageFilm";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JUDUL = "judul";
    private static final String COLUMN_SIPNOPSIS = "sipnosis";
    private static final String COLUMN_POSTER = "poster";




    public SQLDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_JUDUL + " TEXT, " +
                COLUMN_SIPNOPSIS + " TEXT, " +
                COLUMN_POSTER + " TEXT)";
        db.execSQL(query);
    }

    public List<Movie> get(){
        String query = "SELECT " + COLUMN_JUDUL + " FROM " + TABLE_NAME;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);

        c.moveToFirst();
        List <Movie> list = new ArrayList<>();
        while (c.moveToNext()){
            String judul = c.getString(0);
            //String sinopsis  = c.getString(1);
            Movie Movie = new Movie(judul);
            list.add(Movie);

            Log.d("film", judul+" ");
        }

        return list;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addFilm(String judul, String sinopsis, String poster){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_SIPNOPSIS, sinopsis);
        cv.put(COLUMN_POSTER, poster);

        long result = db.insert(TABLE_NAME, null, cv);
//        if (result == 1){
//            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context ,"Added Successfully!", Toast.LENGTH_SHORT).show();
//        }
    }
}
