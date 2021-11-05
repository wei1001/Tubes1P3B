package com.example.tubes1_p3b;

public class Movie {
    private int id = -1;
    private String judul = "";
    private int rating = 0;
    private int status = 0;
    private String synopsis="";
    private String comment="";
    private String foto;

    public Movie(String judul){
        this.judul = judul;
    }

//    public Movie(int id,String judul, int rating, int status,String synopsis,String comment, String foto){
//        this.id = id;
//        this.judul = judul;
//        this.rating = rating;
//        this.status = status;
//        this.synopsis = synopsis;
//        this.comment = comment;
//        this.foto = foto;
//    }

//    public Movie(String judul,String synopsis,String foto){
//        this.judul = judul;
//        this.synopsis = synopsis;
//        this.foto = foto;
//    }

    public String getJudul(){
        return this.judul;
    }
    public String setJudul(String judul){
        return this.judul = judul;
    }

//    public int getRating(){
//        return this.rating;
//    }
//
//    public String getStatusString(){
//        int status=this.status;
//        switch (status){
//            case 1:
//                return "Selesai ditonton";
//            case 2:
//                return "On progress";
//            default:
//                return "Belum ditonton";
//        }
//    }
//
//    public int getStatus(){
//        return this.status;
//    }
//    public String getSynopsis(){
//        return this.synopsis;
//    }
//    public int getId(){
//        return this.id;
//    }
//    public String getComment(){
//        return this.comment;
//    }
//
//    public String getFoto(){
//        return this.foto;
//    }
}
