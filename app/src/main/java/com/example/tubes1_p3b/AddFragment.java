package com.example.tubes1_p3b;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tubes1_p3b.databinding.FragmentAddBinding;


public class AddFragment extends Fragment implements View.OnClickListener {
    private FragmentAddBinding binding;
    private String poster;
    private ActivityResultLauncher<Intent> intentLauncher;
    private MainActivity activity;
    private MainPresenter mainPresenter;

    public AddFragment(MainActivity activity) {
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddBinding.inflate(getLayoutInflater());
        this.binding.btnAdd.setOnClickListener(this);
        this.binding.edPoster.setOnClickListener(this);

        this.intentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent hasil = result.getData();
                        String data = hasil.getDataString();
                        Uri x = Uri.parse(data);
                        String realPath = getPathFromUri(x);
                        binding.edPoster.setImageURI(Uri.parse(realPath));
                        poster = realPath;
                    }
                });

        return binding.getRoot();

    }
    @Override
    public void onClick(View v) {
        if (v == binding.btnAdd){
            SQLDB myDB =  new SQLDB(this.activity);
            myDB.addFilm(binding.edNama.getText().toString().trim(), binding.edSinopsis.getText().toString().trim(),this.poster);
//            mainPresenter.addPage();
            ((MainActivity) getActivity()).changePage(2);
        }
        else if (v== binding.edPoster){
            Intent getImage= new Intent(Intent.ACTION_PICK);
            getImage.setType("image/*");
            this.intentLauncher.launch(getImage);
        }

//        Bundle result = new Bundle();
//        if (v == binding.btnAdd) {
//            String judul = binding.edNama.getText().toString();
//            String sinopsis = binding.edSinopsis.getText().toString();
//            result.putString("judul", judul);
//            result.putString("sinopsis", sinopsis);
//
//            binding.edNama.setText("");
//            binding.edPoster.setImageResource(R.drawable.ic_add);
//            binding.edSinopsis.setText("");
//
//            result.putString("poster", this.poster);
//            getParentFragmentManager().setFragmentResult("insertData", result);
//
//        }
//        else if (v== binding.edPoster) {
//            Intent getImage = new Intent(Intent.ACTION_PICK);
//            getImage.setType("image/*");
//            this.intentLauncher.launch(getImage);
//        }
    }


    public String getPathFromUri(Uri content) {
        String path = null;
        String s[] = {MediaStore.MediaColumns.DATA};
        Cursor c = getContext().getContentResolver().query(content, s, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(s[0]);
        path = c.getString(columnIndex);
        c.close();
        return path;
    }
}
