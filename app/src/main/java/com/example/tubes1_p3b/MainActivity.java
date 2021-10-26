package com.example.tubes1_p3b;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tubes1_p3b.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentListener, IMainActivity{

    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private AddMenuFragment addMenuFragment;
    private EditMenuFragment editMenuFragment;
    private DetailMenuFragment detailMenuFragment;
    private MainPresenter presenter;
    private FoodListAdapter adapter;
    private SettingFragment settingFragment;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getPreferences(MODE_PRIVATE);
        this.presenter = new MainPresenter(this, sp);
        this.adapter = new FoodListAdapter(this, this.presenter);

        this.drawer = binding.drawerLayout;
        this.toolbar = binding.toolbar;
        this.fragmentManager = this.getSupportFragmentManager();



        this.setSupportActionBar(this.toolbar);

        this.mainFragment = new MainFragment();
        this.addMenuFragment = addMenuFragment.newInstance(this.adapter, this.presenter);
        this.editMenuFragment = new EditMenuFragment();
        this.detailMenuFragment = new DetailMenuFragment();
        this.settingFragment = SettingFragment.newInstance(sp);

        boolean isNightMode = this.loadTheme();
        if(isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        //hamburger menu icon
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, this.editMenuFragment);
        ft.add(R.id.fragment_container, this.mainFragment).commit();
    }

    @Override
    public void changePage(String page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page.equals("home")){
            if(this.mainFragment.isAdded()) {
                ft.show(this.mainFragment).addToBackStack(null);
            }
            else {
                ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null);
            }
            if(this.addMenuFragment.isAdded()) {
                ft.hide(this.addMenuFragment).addToBackStack(null);
            }
            if(this.editMenuFragment.isAdded()) {
                ft.hide(this.editMenuFragment).addToBackStack(null);
            }
            if(this.detailMenuFragment.isAdded()){
                ft.hide(this.detailMenuFragment).addToBackStack(null);
            }
            if (this.settingFragment.isAdded()) {
                ft.hide(this.settingFragment).addToBackStack(null);
            }
        } else if(page.equals("addMenu")) {
            if(this.addMenuFragment.isAdded()) {
                ft.show(this.addMenuFragment).addToBackStack(null);
            }
            else {
                ft.add(R.id.fragment_container, this.addMenuFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment).addToBackStack(null);
            }
            if(this.editMenuFragment.isAdded()) {
                ft.hide(this.editMenuFragment).addToBackStack(null);
            }
            if(this.detailMenuFragment.isAdded()) {
                ft.hide(this.detailMenuFragment).addToBackStack(null);
            }
            if (this.settingFragment.isAdded()) {
                ft.hide(this.settingFragment).addToBackStack(null);
            }
        } else if(page.equals("editMenu")) {
            this.editMenuFragment.makeEmptyEditMenu();
            if(this.editMenuFragment.isAdded()) {
                ft.show(this.editMenuFragment).addToBackStack(null);
            }
            else {
                ft.add(R.id.fragment_container, this.editMenuFragment).addToBackStack(null);
            }
            if(this.addMenuFragment.isAdded()) {
                ft.hide(this.addMenuFragment).addToBackStack(null);
            }
            if(this.detailMenuFragment.isAdded()) {
                ft.hide(this.detailMenuFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment).addToBackStack(null);
            }
            if (this.settingFragment.isAdded()) {
                ft.hide(this.settingFragment).addToBackStack(null);
            }
        } else if(page.equals("editMenuOfList")) {
            if(this.editMenuFragment.isAdded()) {
                ft.show(this.editMenuFragment).addToBackStack(null);
            }
            else {
                ft.add(R.id.fragment_container, this.editMenuFragment).addToBackStack(null);
            }
            if(this.addMenuFragment.isAdded()) {
                ft.hide(this.addMenuFragment).addToBackStack(null);
            }
            if(this.detailMenuFragment.isAdded()) {
                ft.hide(this.detailMenuFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment).addToBackStack(null);
            }
            if (this.settingFragment.isAdded()) {
                ft.hide(this.settingFragment).addToBackStack(null);
            }
        } else if(page.equals("detailMenu")) {
            if(this.detailMenuFragment.isAdded()) {
                ft.show(this.detailMenuFragment).addToBackStack(null);
            }
            else {
                ft.add(R.id.fragment_container, this.detailMenuFragment).addToBackStack(null);
            }
            if(this.addMenuFragment.isAdded()) {
                ft.hide(this.addMenuFragment).addToBackStack(null);
            }
            if(this.editMenuFragment.isAdded()) {
                ft.hide(this.editMenuFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()) {
                ft.hide(this.mainFragment).addToBackStack(null);
            }
            if (this.settingFragment.isAdded()) {
                ft.hide(this.settingFragment).addToBackStack(null);
            }
        } else if(page.equals("setting")) {
            if(this.settingFragment.isAdded()) {
                ft.show(this.settingFragment).addToBackStack(null);
            }
            else {
                ft.add(R.id.fragment_container, this.settingFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment).addToBackStack(null);
            }
            if (this.addMenuFragment.isAdded()){
                ft.hide(this.addMenuFragment).addToBackStack(null);
            }
            if(this.detailMenuFragment.isAdded()) {
                ft.hide(this.detailMenuFragment).addToBackStack(null);
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
    public Food getRandomFoods() {
        Food res = null;
        List<Food> temp = this.addMenuFragment.getFoodList();
        if (!temp.isEmpty()) {
            Random rand = new Random();
            res = temp.get(rand.nextInt(temp.size()));
        }
        return res;
    }

    @Override
    public void addList(String title, String desc, String tag, String bahan, String steps) {
        this.addMenuFragment.addList(title, desc, tag, bahan, steps);
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
    public void updateList(List<Food> foods) {
        this.adapter.addLine(foods);
    }

    @Override
    public void createDetailMenuFragment(Food food, int position) {
        this.detailMenuFragment = detailMenuFragment.newInstance(food, position);
        this.changePage("detailMenu");
    }

    @Override
    public void transferData(int position, String title, String desc, String tag, String bahan, String steps) {
        this.editMenuFragment.transferData(position, title, desc, tag, bahan, steps);
    }

    @Override
    public void changeList(int position, String title, String desc, String tag, String bahan, String steps) {
        this.presenter.changeList(position, title, desc, tag, bahan, steps);
    }

    @Override
    public void saveTheme(boolean bool){
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putBoolean("Theme", bool);
        editor.apply();
    }

    @Override
    public boolean loadTheme(){
        boolean res = sp.getBoolean("Theme", false);
        return res;
    }

    @Override
    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}