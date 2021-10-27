package com.example.tubes1_p3b;

//public class MovieListAAdapter extends BaseAdapter{
//    private list list;
//    private MoviesPresenter mp;
//    private ArrayList<MoviesModel> film;
//
//    public MoviesListAdapter(list list, MoviesPresenter mp){
//        this.list=list;
//        this.mp=mp;
//        insertMovie(mp.getData());
//    }
//
//    public void insertMovie(ArrayList<MoviesModel> arr){
//        this.film=arr;
//    }
//
//    public void updateMovie(ArrayList<MoviesModel> arr){
//        this.film=null;
//        this.film=arr;
//        this.notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return this.film.size();
//    }
//
//    @Override
//    public MoviesModel getItem(int position) {
//        return this.film.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        MoviesListBinding bindingMovies;
//        ViewHolder vh;
//        if(convertView==null){
//            bindingMovies=MoviesListBinding.inflate(this.list.getLayoutInflater());
//            convertView=bindingMovies.getRoot();
//            vh=new ViewHolder(bindingMovies,position,getItem(position),this.mp);
//            convertView.setTag(bindingMovies);
//        }else{
//            bindingMovies=(MoviesListBinding) convertView.getTag();
//        }
//        return bindingMovies.getRoot();
//    }
//}
//
//
//class ViewHolder implements View.OnClickListener {
//    private MoviesListBinding binding;
//    private int index;
//    private MoviesModel moviesModelEntitiy;
//    private  ImageView arr[];
//    private MoviesPresenter presenter;
//
//    public ViewHolder(MoviesListBinding binding, int index, MoviesModel entitas, MoviesPresenter presenter){
//        this.binding=binding;
//        this.index=index;
//        this.moviesModelEntitiy=entitas;
//        this.presenter=presenter;
//        ImageView star[]={binding.star, binding.star1, binding.star2, binding.star3, binding.star4};
//        this.arr=star;
//        setText();
//        binding.boxMovies.setOnClickListener(this);
//    }
//
//    public void setText(){
//        binding.MoviesJudul.setText(moviesModelEntitiy.getJudul());
//        binding.MoviesStatus.setText(moviesModelEntitiy.getStatus());
//        String foto=moviesModelEntitiy.getFoto();
//        binding.gambarMovie.setImageURI(Uri.parse(foto));
//        int skor=moviesModelEntitiy.getBintang();
//
//        for(int i=0;i<skor;i++){
//            this.arr[i].setImageResource(R.drawable.ic_star_full);
//        }
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        int clicked=v.getId();
//        if(clicked==binding.boxMovies.getId()){
//            presenter.openMovie(index);
//        }else {
//            //untuk delete
//        }
//    }
//}
