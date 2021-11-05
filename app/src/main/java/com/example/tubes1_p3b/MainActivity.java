package com.example.tubes1_p3b;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import com.example.tubes1_p3b.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentListener, IMainActivity{

    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private ListMovFragment listFragment;
    private AddFragment addFragment;
    private DetailFragment detailFragment;
    private MainPresenter mainPresenter;
    private ListView lv;
//    private MovieListPlainAdapter adapter;
//    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());

//        sp = getPreferences(MODE_PRIVATE);
//        this.presenter = new MainPresenter(this, sp);
//        this.adapter = new MovieListPlainAdapter(this);
        this.mainPresenter = new MainPresenter(this);
        this.drawer = binding.drawerLayout;
        this.lv = findViewById(R.id.lv_film);
//        this.toolbar = binding.toolbar;
        this.fragmentManager = this.getSupportFragmentManager();
        this.mainFragment = new MainFragment();
        this.listFragment = new ListMovFragment(this);
        this.addFragment = new AddFragment(this);
        this.detailFragment = new DetailFragment(this);

//        this.setSupportActionBar(binding.toolbar);



//        boolean isNightMode = this.loadTheme();
//        if(isNightMode){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }


        //hamburger menu icon
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null).commit();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == 1){
            Log.d("debug", "starting page");
            if (this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }
            else{
                ft.add(R.id.fragment_container,this.mainFragment).addToBackStack(null);
            }

        }
        else if (page == 2){
            Log.d("debug", "menuju halaman utama");
            if (this.listFragment.isAdded()){
                ft.show(this.listFragment);
            }else{
                ft.add(R.id.fragment_container,this.listFragment).addToBackStack(null);
            }

            if(this.listFragment.isAdded()){
                ft.hide(this.addFragment);
            }
        }
        else if(page == 3){
            Log.d("debug", "menuju halaman add");
            if (this.addFragment.isAdded()){
                ft.show(this.addFragment);
            }else{
                ft.add(R.id.fragment_container,this.addFragment).addToBackStack(null);
            }
        }
        else if(page == 4){
            Log.d("debug", "menuju halaman detail");
            if (this.detailFragment.isAdded()){
                ft.show(this.detailFragment);
            }else{
                ft.add(R.id.fragment_container,this.detailFragment).addToBackStack(null);
            }
        }
        ft.commit();
        this.drawer.closeDrawers();
    }

    @Override
    public void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
    }


    @Override
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }



    @Override
    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}