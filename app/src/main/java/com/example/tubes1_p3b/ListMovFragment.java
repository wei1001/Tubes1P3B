package com.example.tubes1_p3b;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.example.tubes1_p3b.databinding.FragmentListMovBinding;

import java.util.ArrayList;
import java.util.List;


public class ListMovFragment extends Fragment implements View.OnClickListener{

    private FragmentListMovBinding binding;
    private MovieListPlainAdapter adapter;
    private MainActivity activity;
    private FragmentListener listener;
    private MainPresenter mainPresenter;
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
        this.binding.lvFilm.setAdapter(this.adapter);
        SQLDB myDB =  new SQLDB(this.activity);
        this.adapter.add(myDB.get());
        this.binding.btnAddList.setOnClickListener(this);
        binding.lvFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", " " +  "    " + position);
//                mainPresenter.detailPage(position);
                Bundle result= new Bundle();
                result.putInt("bundleKey", position);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                ((MainActivity) getActivity()).changePage(4);

            }
        });
        return view;
    }
    @Override
    public void onClick(View view) {
        if (view == binding.btnAddList) {
            ((MainActivity) getActivity()).changePage(3);
        }
    }
}