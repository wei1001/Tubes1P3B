package com.example.tubes1_p3b;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieListPlainAdapter extends BaseAdapter {
    private List<Movie> listItems;
    private Activity activity;

    public MovieListPlainAdapter(Activity activity){
        this.activity = activity;
        this.listItems = new ArrayList<Movie>();
    }
    public void add(List<Movie> newItem){
        this.listItems = newItem;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount(){
        return listItems.size();
    }

    @Override
    public Object getItem(int i){
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        MovieListPlainAdapter.ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(this.activity).inflate(R.layout.movie_list_plain, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (MovieListPlainAdapter.ViewHolder) convertView.getTag();
        }
        Movie currentFilm = (Movie) this.getItem(i);
        viewHolder.updateView(currentFilm);

        //title
        TextView tvTitle = convertView.findViewById(R.id.tv_judul);
        tvTitle.setText(currentFilm.getJudul());

        return convertView;
    }

    public void tambah(List <Movie> Movie){
        this.listItems = Movie;
        notifyDataSetChanged();
    }

    class ViewHolder{
        protected TextView title;
        private LinearLayout list;
        protected int i;

        public ViewHolder (View view){
            this.title = view.findViewById(R.id.tv_judul);
            this.list= view.findViewById(R.id.ll_judul);
            this.i = i;
        }
        public void updateView(Movie mov){
            this.title.setText(mov.getJudul());
        }
    }


}
