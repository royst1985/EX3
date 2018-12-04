package com.example.roymoria.movies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MoviesActivity extends AppCompatActivity {

    public class PageOnClickListener implements View.OnClickListener
    {
        int pos;
        public PageOnClickListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View v)
        {
            Intent openSecondActivity  = new Intent(MoviesActivity.this, MovieDetailActivity.class);
            openSecondActivity.putExtra("id", pos);
            startActivity(openSecondActivity);
        }
    };

    public class MovieModel {

        private String name;
        private int imageResourceId;
        private String overview;


        public MovieModel(int imageResourceId, String name,  String overview) {
            this.name = name;
            this.imageResourceId = imageResourceId;
            this.overview = overview;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }

        public void setImageResourceId(int imageResourceId) {
            this.imageResourceId = imageResourceId;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }
    }



    private List<MovieModel> LoadMovies() {

        List<MovieModel> movies = new ArrayList<>();

        MovieModel movie1 = new MovieModel(R.drawable.jurassic,"Jurassic World - Fallen Kingdom","Three years after…");
        MovieModel movie2 = new MovieModel(R.drawable.meg,"The Meg","A deep …");
        MovieModel movie3 = new MovieModel(R.drawable.deadpool,"Deadpool","Three years after…");
        MovieModel movie4 = new MovieModel(R.drawable.black,"The black panter","A deep …");
        MovieModel movie5 = new MovieModel(R.drawable.gurdians,"The guardians of the galaxy","Three years after…");
        MovieModel movie6 = new MovieModel(R.drawable.ocean,"Ocean 8","A deep …");
        MovieModel movie7 = new MovieModel(R.drawable.thor,"Thor","Three years after…");
        MovieModel movie8 = new MovieModel(R.drawable.purge,"The first purge","A deep …");
        MovieModel movie9 = new MovieModel(R.drawable.inter,"Intestellar","Three years after…");

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
        private List<MovieModel> mDataSource;

        public MoviesViewAdapter(Context context, List<MovieModel> movies) {
            mDataSource = movies;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.movie_row_screen, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.onBindViewHolder(mDataSource.get(i % mDataSource.size()));
            PageOnClickListener on_click = new PageOnClickListener (i % mDataSource.size());
            viewHolder.clLayout.setOnClickListener(on_click);
        }

        @Override
        public int getItemCount() {
            return mDataSource.size();
            //return Integer.MAX_VALUE;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final ImageView ivImage;
            public final TextView tvTitle;
            public final TextView tvOverview;
            public final ConstraintLayout clLayout;

            public ViewHolder(View view) {
                super(view);
                ivImage = view.findViewById(R.id.singleMoviePic);
                tvTitle = view.findViewById(R.id.singleMovieTitle);
                tvOverview = view.findViewById(R.id.singleMovieContext);
                clLayout = view.findViewById(R.id.singleMovieView);
            }

            public void onBindViewHolder(MovieModel movieModel) {
                ivImage.setImageResource(movieModel.getImageResourceId());
                tvTitle.setText(movieModel.getName());
                tvOverview.setText(movieModel.getOverview());
            }
        }
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private void initRecyclerView() {

        List<MovieModel> dataSource = LoadMovies();
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MoviesViewAdapter(this,dataSource);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //Adding RecyclerView Divider / Separator
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);
        initRecyclerView();

        //mRecyclerView.setOnClickListener();
    }
}

