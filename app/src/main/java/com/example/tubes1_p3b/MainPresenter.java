package com.example.tubes1_p3b;

public class MainPresenter {
    protected MainActivity main;
    public MainPresenter(MainActivity MainActivity){
        this.main = MainActivity;
    }
    public void listPage(){
        main.changePage(2);
    }
    public void addPage(){
        main.changePage(3);
    }
    public void detailPage(int pos) {
        main.changePage(4);
    }
}
