package com.example.roymoria.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

import static android.support.v7.widget.helper.ItemTouchHelper.*;
import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {


   public class MovieModel {

        private String mName;
       private String mName1;
       private String mName2;
        private int mImageResourceId;
        private String mOverview;

        void SetName(String name){
            mName = name;
        }

        void SetOverView(String overview){
            mOverview = overview;
        }

        void SetId(int id){
            mImageResourceId = id;
        }

        public int getImageRes() {
            return mImageResourceId;
        }

        public String getName() {
            return mName;
        }

        public String getOverview() {
            return mOverview;
        }
    }

    private ArrayList<MovieModel> LoadMovies() {

        ArrayList<MovieModel> movies = new ArrayList<>(9);

        MovieModel movie1 = new MovieModel();
        MovieModel movie2 = new MovieModel();
        MovieModel movie3 = new MovieModel();
        MovieModel movie4 = new MovieModel();
        MovieModel movie5 = new MovieModel();
        MovieModel movie6 = new MovieModel();
        MovieModel movie7 = new MovieModel();
        MovieModel movie8 = new MovieModel();
        MovieModel movie9 = new MovieModel();

        movie1.SetId(R.drawable.jurassic);
        movie2.SetId(R.drawable.meg);
        movie3.SetId(R.drawable.deadpool);
        movie4.SetId(R.drawable.black);
        movie5.SetId(R.drawable.gurdians);
        movie6.SetId(R.drawable.ocean);
        movie7.SetId(R.drawable.thor);
        movie8.SetId(R.drawable.purge);
        movie9.SetId(R.drawable.inter);

        movie1.SetName("Jurassic World - Fallen Kingdom");
        movie2.SetName("The Meg");
        movie3.SetName("Deadpool");
        movie4.SetName("The black panter");
        movie5.SetName("The guardians of the galaxy");
        movie6.SetName("Ocean 8");
        movie7.SetName("Thor");
        movie8.SetName("The first purge");
        movie9.SetName("Intestellar");

        movie1.SetOverView("Three years after…");
        movie2.SetOverView("A deep …");
        movie3.SetOverView("Three years after…");
        movie4.SetOverView("A deep …");
        movie5.SetOverView("Three years after…");
        movie6.SetOverView("A deep …");
        movie7.SetOverView("Three years after…");
        movie8.SetOverView("A deep …");
        movie9.SetOverView("Three years after…");

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
        movies.add(movie6);
        movies.add(movie7);
        movies.add(movie8);
        movies.add(movie9);

        return movies;
    }

    public class MoviesViewAdapter extends RecyclerView.Adapter<MoviesViewAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private ArrayList<MovieModel> mDataSource;
        public MoviesViewAdapter(Context context, ArrayList<MovieModel> movies) {
            mDataSource = movies;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.item_movie, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.onBindViewHolder(mDataSource.get(i % mDataSource.size()));
            viewHolder.parentLayout.setOnClickListener(this::onClick,i);
        }

        @Override
        public int getItemCount() {
            //return mDataSource.size();
            return Integer.MAX_VALUE;
        }

        private void onClick(View view) {
            Intent largeWindow = new Intent(this,LargeActivity.class);
            largeWindow.putExtra("image name",getItemCount())
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final ImageView ivImage;
            public final TextView tvTitle;
            public final TextView tvOverview;
            public ConstraintLayout parentLayout;

            public ViewHolder(View view) {
                super(view);
                ivImage = view.findViewById(R.id.movie_image);
                tvTitle = view.findViewById(R.id.movie_title);
                tvOverview = view.findViewById(R.id.movie_overview);
                parentLayout = view.findViewById(R.id.ConstraintLayout);
            }

            public void onBindViewHolder(MovieModel movieModel) {

                ivImage.setImageResource(movieModel.getImageRes());
                tvTitle.setText(movieModel.getName());
                tvOverview.setText(movieModel.getOverview());
            }
        }

    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private void initRecyclerView() {

        ArrayList<MovieModel> dataSource = LoadMovies();
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MoviesViewAdapter(this,dataSource);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Adding RecyclerView Divider / Separator
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);
    }

    class SwipeController extends Callback {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, LEFT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);
        initRecyclerView();
        //mRecyclerView.setOnClickListener();
    }
}

