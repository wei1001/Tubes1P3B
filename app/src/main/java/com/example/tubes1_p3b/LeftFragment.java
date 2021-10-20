package com.example.tubes1_p3b;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tubes1_p3b.databinding.FragmentLeftBinding;

public class LeftFragment extends Fragment implements View.OnClickListener{
    private FragmentListener listener;
    private FragmentLeftBinding binding;

    public LeftFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentLeftBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.binding.homeBtn.setOnClickListener(this);
        this.binding.daftarBtn.setOnClickListener(this);
        this.binding.historyBtn.setOnClickListener(this);
        this.binding.exitBtn.setOnClickListener(this);
        this.binding.settingBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == this.binding.homeBtn) {
            this.listener.changePage("home");
        }
        else if(v == this.binding.daftarBtn) {
            this.listener.changePage("daftar");
        }
        else if(v == this.binding.historyBtn) {
            this.listener.changePage("history");
        }
        else if(v == this.binding.exitBtn) {
            this.listener.closeApplication();
        }
        else if(v == this.binding.settingBtn){
            this.listener.changePage("setting");
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }
}
