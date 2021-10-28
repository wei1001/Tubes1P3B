package com.example.tubes1_p3b;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tubes1_p3b.databinding.FragmentMainBinding;


public class MainFragment extends Fragment implements View.OnClickListener {

    private FragmentMainBinding binding;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.binding.btnMulai.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnMulai){
            ((MainActivity)getActivity()).changePage(2);
        }
    }
}