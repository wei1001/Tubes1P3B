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
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_BINTANG = "bintang";
    private static final String COLUMN_REVIEW = "review";



    public SQLDB(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "      + TABLE_NAME +
                " (" + COLUMN_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JUDUL        + " TEXT, " +
                COLUMN_SIPNOPSIS    + " TEXT, " +
                COLUMN_POSTER       + " TEXT, " +
                COLUMN_STATUS       + " TEXT, " +
                COLUMN_BINTANG      + " TEXT, " +
                COLUMN_REVIEW       + " TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public List<Movie> get(){
        String query = "SELECT " + COLUMN_JUDUL + " FROM " + TABLE_NAME;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);

        c.moveToFirst();
        List <Movie> list = new ArrayList<>();
        if(c == null){
            while (c.moveToNext()){
                String judul = c.getString(0);
                //String sinopsis  = c.getString(1);
                Movie Movie = new Movie(judul);
                list.add(Movie);
                Log.d("film", judul+" ");
            }
        }
        else if (c != null) {
            do {
                String judul = c.getString(0);
                Movie Movie = new Movie(judul);
                list.add(Movie);
            }
            while (c.moveToNext());
        }
//        do {
//            String judul = c.getString(0);
//            Movie Movie = new Movie(judul);
//            list.add(Movie);
//        }
//        while (c.moveToNext());

        return list;
    }
    public String get2(int posisi){
        String query =  "SELECT " + COLUMN_JUDUL +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + posisi;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);


        c.moveToFirst();
        List <Movie> list = new ArrayList<>();

        String judul = c.getString(0);
        Movie Movie = new Movie(judul);
        return judul;
    }

    public String getPoster(int posisi){
        String query = "SELECT " + COLUMN_POSTER +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + posisi;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);

        c.moveToFirst();
        List <Movie> list = new ArrayList<>();

        String foto = c.getString(0);
        Movie Movie = new Movie(foto);
        list.add(Movie);

        return foto;
    }
    public String getStatus(int posisi){
        String query = "SELECT " + COLUMN_STATUS +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + posisi;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);

        c.moveToFirst();
        List <Movie> list = new ArrayList<>();

        String status = c.getString(0);
        Movie Movie = new Movie(status);
        list.add(Movie);

        return status;
    }
    public String getBintang(int posisi){
        String query = "SELECT " + COLUMN_BINTANG +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + posisi;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);

        c.moveToFirst();
        List <Movie> list = new ArrayList<>();

        String Bintang = c.getString(0);
        Movie Movie = new Movie(Bintang);
        list.add(Movie);
        return Bintang;
    }
    public String getReview(int posisi){
        String query = "SELECT " + COLUMN_REVIEW +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID + " = " + posisi;
        SQLiteDatabase anj = this.getWritableDatabase();
        Cursor c = anj.rawQuery(query,null);

        c.moveToFirst();
        List <Movie> list = new ArrayList<>();

        String review = c.getString(0);
        Movie Movie = new Movie(review);
        list.add(Movie);
        return review;
    }


    void addFilm(String judul, String sinopsis, String poster){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_SIPNOPSIS, sinopsis);
        cv.put(COLUMN_POSTER, poster);

        long result = db.insert(TABLE_NAME, null, cv);
    }



    void addStatus(int pos, String hasil){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME +
                " SET " + COLUMN_STATUS + " = '" + hasil +
                "' WHERE " + COLUMN_ID + " = '" + pos + "'";
        db.execSQL(query);
    }
    void addReview(int pos, String hasil){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME +
                " SET " + COLUMN_REVIEW + " = '" + hasil +
                "' WHERE " + COLUMN_ID + " = '" + pos + "'";
        Log.d("", "addStatus: " + query);
        db.execSQL(query);
    }
    void addBintang(int pos, String hasil){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME +
                " SET " + COLUMN_BINTANG + " = '" + hasil +
                "' WHERE " + COLUMN_ID + " = '" + pos + "'";
        Log.d("", "addStatus: " + query);
        db.execSQL(query);
    }
    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
