package com.example.roymoria.movies;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;


public class MoviesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        initRecyclerView();
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent openSecondActivity;
        switch (item.getItemId()) {
            case R.id.action_async:
                openSecondActivity  = new Intent(this, AsyncTaskActivity.class);
                //openSecondActivity.putExtra("id", pos);
                startActivity(openSecondActivity);
                return true;

            case R.id.action_threads:
                openSecondActivity  = new Intent(this, AsyncTaskActivity.class);
                //openSecondActivity.putExtra("id", pos);
                startActivity(openSecondActivity);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

