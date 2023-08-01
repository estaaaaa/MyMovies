package sg.edu.rp.c346.id22017139.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;
    TextView tvTitle, tvGenre, tvYear;
    ImageView ivRating;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);
        tvTitle = rowView.findViewById(R.id.tvTitle);
        tvGenre = rowView.findViewById(R.id.tvGenre);
        tvYear = rowView.findViewById(R.id.tvYear);
        ivRating = rowView.findViewById(R.id.ivRating);
        Movie currentMovie = movieList.get(position);
        tvTitle.setText(currentMovie.getTitle());
        tvGenre.setText(currentMovie.getGenre());
        tvYear.setText(currentMovie.getYear() + "");

        int img = 0;
        String movieRating = currentMovie.getRating();
        if (movieRating.equalsIgnoreCase("G")) {
            img = R.drawable.rating_g;
        } else if (movieRating.equalsIgnoreCase("PG")) {
            img = R.drawable.rating_pg;
        } else if (movieRating.equalsIgnoreCase("PG13")) {
            img = R.drawable.rating_pg13;
        } else if (movieRating.equalsIgnoreCase("NC16")) {
            img = R.drawable.rating_nc16;
        } else if (movieRating.equalsIgnoreCase("M18")) {
            img = R.drawable.rating_m18;
        } else {
            img = R.drawable.rating_r21;
        }

        ivRating.setImageResource(img);
        return rowView;
    }
}
