package sg.edu.rp.c346.id22017139.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class movieList extends AppCompatActivity {

    ToggleButton tbFilter;
    ListView lv;
    ArrayList<Movie> movieList, filteredMovieList;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        tbFilter = findViewById(R.id.tbFilter);
        lv = findViewById(R.id.lv);
        movieList = new ArrayList<>();
        filteredMovieList = new ArrayList<>();

        DBHelper db = new DBHelper(getApplicationContext());
        movieList = db.getMovies();
        db.close();

        adapter = new CustomAdapter(this, R.layout.row, movieList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tbFilter.setOnClickListener(v -> {
            boolean isChecked = tbFilter.isChecked();
            if (isChecked) {
                filteredMovieList = db.getFilteredMovies();
                adapter = new CustomAdapter(this, R.layout.row, filteredMovieList);
            } else {
                movieList = db.getMovies();
                adapter = new CustomAdapter(this, R.layout.row, movieList);
            }
            db.close();
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Movie movie = movieList.get(position);
            Intent i = new Intent(movieList.this, editMovie.class);
            i.putExtra("movie", movie);
            startActivity(i);
        });

    }
}