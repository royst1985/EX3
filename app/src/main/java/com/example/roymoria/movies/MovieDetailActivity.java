package com.example.roymoria.movies;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("id",0);
        setContentView(R.layout.movie_details);
        //setContentView(R.layout.fragment_screen_slide_page);
        List<ScreenSlidePageFragment> fragments = LoadMovies();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new MovieDetailActivity.ScreenSlidePagerAdapter(getSupportFragmentManager(),fragments);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setCurrentItem(pos);
    }

    private List<ScreenSlidePageFragment> LoadMovies()
    {
        List<ScreenSlidePageFragment> fragments = new ArrayList<>();

        ScreenSlidePageFragment movieFragment1 = ScreenSlidePageFragment.newInstance(R.drawable.jursic_large, R.drawable.jurassic,R.array.movie_Jurassic);
        ScreenSlidePageFragment movieFragment2 = ScreenSlidePageFragment.newInstance(R.drawable.meg_large,R.drawable.meg,R.array.movie_Meg);
        ScreenSlidePageFragment movieFragment3 = ScreenSlidePageFragment.newInstance(R.drawable.deadpool_large,R.drawable.deadpool,R.array.movie_Deadpool);
        ScreenSlidePageFragment movieFragment4 = ScreenSlidePageFragment.newInstance(R.drawable.panter_large,R.drawable.black,R.array.movie_Black);
        ScreenSlidePageFragment movieFragment5 = ScreenSlidePageFragment.newInstance(R.drawable.gard_large,R.drawable.gurdians,R.array.movie_Guardians);
        ScreenSlidePageFragment movieFragment6 = ScreenSlidePageFragment.newInstance(R.drawable.oceans_large,R.drawable.ocean,R.array.movie_Ocean);
        ScreenSlidePageFragment movieFragment7 = ScreenSlidePageFragment.newInstance(R.drawable.thor_large,R.drawable.thor,R.array.movie_Thor);
        ScreenSlidePageFragment movieFragment8 = ScreenSlidePageFragment.newInstance(R.drawable.parge_large,R.drawable.purge,R.array.movie_Purge);
        ScreenSlidePageFragment movieFragment9 = ScreenSlidePageFragment.newInstance(R.drawable.interstellar_large,R.drawable.inter,R.array.movie_Interstellar);

        fragments.add(movieFragment1);
        fragments.add(movieFragment2);
        fragments.add(movieFragment3);
        fragments.add(movieFragment4);
        fragments.add(movieFragment5);
        fragments.add(movieFragment6);
        fragments.add(movieFragment7);
        fragments.add(movieFragment8);
        fragments.add(movieFragment9);

        return fragments;
    }

//    @Override
//    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            super.onBackPressed();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
//    }


    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        private List<ScreenSlidePageFragment> fragments;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<ScreenSlidePageFragment> movieFragmentList) {
            super(fm);
            fragments = movieFragmentList;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
