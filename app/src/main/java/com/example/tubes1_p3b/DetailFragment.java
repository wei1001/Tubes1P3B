package com.example.tubes1_p3b;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes1_p3b.databinding.FragmentAddBinding;
import com.example.tubes1_p3b.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment implements View.OnClickListener{

    FragmentDetailBinding binding;
    MainActivity activity;
    MainPresenter mainPresenter;
    int posTemp;
    public DetailFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentDetailBinding.inflate(getLayoutInflater());
        SQLDB myDB =  new SQLDB(this.activity);
        mainPresenter = new MainPresenter(this.activity);
        this.binding.btnBelum.setOnClickListener(this);
        this.binding.btnSedang.setOnClickListener(this);
        this.binding.btnSelesai.setOnClickListener(this);
        this.binding.btnSaveRating.setOnClickListener(this);
        this.binding.btnSaveReview.setOnClickListener(this);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int pos = result.getInt("bundleKey");
                posTemp = pos;

                String temp = myDB.get2(pos+1);
                binding.tvJudulDetail.setText(temp + "");
                Uri getFoto = Uri.parse(myDB.getPoster(pos+1)) ;
                binding.ivPoster.setImageURI(getFoto);

                if(myDB.getStatus(pos + 1) == null){
                    binding.tvStatus.setText("STATUS :" + "\n" + "Not Watched");
                }
                else{
                    binding.tvStatus.setText("STATUS :" + "\n" + myDB.getStatus(pos + 1));
                }

                if(myDB.getBintang(pos + 1) != null){
                    binding.ratingBarId.setRating(Float.parseFloat(myDB.getBintang(pos + 1)));
                }
                else{
                    binding.ratingBarId.setRating(0);
                }
                binding.edReview.setText(myDB.getBintang(pos + 1));

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        SQLDB myDB =  new SQLDB(this.activity);
        if(view == binding.btnBelum){
            String hasil = "Not Watched";
            myDB.addStatus(posTemp + 1, hasil);

        }
        else if(view == binding.btnSedang){
            String hasil = "Watching";
            myDB.addStatus(posTemp + 1, hasil);

        }
        else if(view == binding.btnSelesai){
            String hasil = "Watched";
            myDB.addStatus(posTemp + 1, hasil);

        }

        else if (view == binding.btnSaveRating){
            String text = String .valueOf(binding.ratingBarId.getRating());
            myDB.addBintang(posTemp + 1, text);

        }
        else if(view == binding.btnSaveReview){
            String hasil = binding.edReview.getText().toString();
            Log.d("", "hasil : " + hasil);

        }
    }
}