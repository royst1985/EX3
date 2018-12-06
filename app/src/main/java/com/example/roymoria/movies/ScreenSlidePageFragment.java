package com.example.roymoria.movies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ScreenSlidePageFragment extends Fragment {

    String mUrl = null;

    // TODO: Rename and change types and number of parameters
    public static ScreenSlidePageFragment newInstance(int imageResourceIdLarge, int imageResourceId, int movieId) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();

        Bundle args = new Bundle();
        args.putString("image", String.valueOf(imageResourceId));
        args.putString("imageLarge", String.valueOf(imageResourceIdLarge));
        args.putInt("title",movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        ImageView imageTop = rootView.findViewById(R.id.TopIV);
        ImageView imageCenter = rootView.findViewById(R.id.centerIV);
        TextView title = rootView.findViewById(R.id.title);
        TextView context = rootView.findViewById(R.id.intro);;
        TextView release = rootView.findViewById(R.id.released);
        Button startTrilar = rootView.findViewById(R.id.btn_movie_trailer);
        Bundle args = getArguments();
        Resources res = getResources();

        String[] movie = res.getStringArray(args.getInt("title"));
        int imageId = Integer.parseInt(args.getString("image",""));
        int imageIdLarge = Integer.parseInt(args.getString("imageLarge",""));
        imageCenter.setImageResource(imageId);
        imageTop.setImageResource(imageIdLarge);
        title.setText(movie[0]);
        context.setText(movie[1]);
        release.setText(movie[3]);
        mUrl = movie[2];
        startTrilar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openWebPage(mUrl);
            }
        });
        return rootView;
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}
