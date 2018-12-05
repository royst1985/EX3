package com.example.roymoria.movies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MoviesViewAdapter extends RecyclerView.Adapter<MoviesViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<MovieModel> mDataSource;
    private Context mContext;

    public MoviesViewAdapter(Context context, List<MovieModel> movies) {
        mDataSource = movies;
        mContext = context;
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
        PageOnClickListener on_click = new PageOnClickListener(mContext,i % mDataSource.size());
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

    public static class PageOnClickListener implements View.OnClickListener
    {
        private Context mContext;
        int pos;

        public PageOnClickListener(Context Context, int pos) {
            this.mContext = Context;
            this.pos = pos;
        }

        @Override
        public void onClick(View v)
        {
            Intent openSecondActivity  = new Intent(mContext, MovieDetailActivity.class);
            openSecondActivity.putExtra("id", pos);
            mContext.startActivity(openSecondActivity);
        }
    };
}