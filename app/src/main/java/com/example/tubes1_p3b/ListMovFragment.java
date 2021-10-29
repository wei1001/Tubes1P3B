package com.example.tubes1_p3b;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tubes1_p3b.databinding.FragmentListMovBinding;

import java.util.ArrayList;
import java.util.List;


public class ListMovFragment extends Fragment implements View.OnClickListener{

    private FragmentListMovBinding binding;
    private MovieListPlainAdapter adapter;
    private MainActivity activity;
    private FragmentListener listener;
    public ListMovFragment(MainActivity activity) {
        this.activity = activity;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentListMovBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.adapter = new MovieListPlainAdapter(this.activity);

        List<Movie> objarr=new ArrayList<>();
        objarr.add(new Movie("Testing"));
        this.adapter.tambah(objarr);
        binding.lvFilm.setAdapter(this.adapter);
        SQLDB myDB =  new SQLDB(this.activity);
        this.adapter.add(myDB.get());

        return view;
    }
    @Override
    public void onClick(View view) {
        if (view == binding.btnAddList) {
            this.listener.changePage(3);
        }
    }
}