package sg.edu.rp.c346.id22017139.mymovies;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class editMovie extends AppCompatActivity {

    EditText etId, etTitle, etGenre, etYear;
    Spinner spinnerRating;
    Button btnUpdate, btnDelete, btnCancel;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        etId = findViewById(R.id.etId);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinnerRating = findViewById(R.id.spinnerRating);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        movie = (Movie) i.getSerializableExtra("movie");

        etId.setText(movie.getId()+"");
        etId.setEnabled(false);
        etGenre.setText(movie.getGenre());
        etTitle.setText(movie.getTitle());
        etYear.setText(movie.getYear()+"");

        String getRating = movie.getRating();
        int checkRating = -99;
        if (getRating.equalsIgnoreCase("G")) {
            checkRating = 0;
        } else if (getRating.equalsIgnoreCase("PG")) {
            checkRating = 1;
        } else if (getRating.equalsIgnoreCase("PG13")) {
            checkRating = 2;
        } else if (getRating.equalsIgnoreCase("NC16")) {
            checkRating = 3;
        } else if (getRating.equalsIgnoreCase("M18")) {
            checkRating = 4;
        } else if (getRating.equalsIgnoreCase("R21")) {
            checkRating = 5;
        }
        spinnerRating.setSelection(checkRating);

        btnCancel.setOnClickListener(v -> {
            finish();
        });

        btnUpdate.setOnClickListener(v -> {
            if (!isEmpty()) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = spinnerRating.getSelectedItem().toString();

                DBHelper db = new DBHelper(editMovie.this);
                movie.setMovieDetails(title, genre, year, rating);
                db.updateMovie(movie);
                db.close();
                finish();
                Intent intent = new Intent(editMovie.this, movieList.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Movie updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            DBHelper db = new DBHelper(editMovie.this);
            db.deleteMovie(movie.getId());
            finish();
            Intent intent = new Intent(editMovie.this, movieList.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Movie deleted", Toast.LENGTH_SHORT).show();
        });
    }
    private boolean isEmpty() {
        String title = etTitle.getText().toString();
        String genre = etGenre.getText().toString();
        String year = etYear.getText().toString();
        if (title.isEmpty() && genre.isEmpty() && year.isEmpty()) {
            etTitle.setError("Enter title");
            etGenre.setError("Enter genre");
            etYear.setError("Enter year");
            return true;
        } else if (title.isEmpty() && genre.isEmpty()) {
            etTitle.setError("Enter title");
            etGenre.setError("Enter genre");
            return true;
        } else if (title.isEmpty() && year.isEmpty()) {
            etTitle.setError("Enter title");
            etYear.setError("Enter year");
            return true;
        }  else if (genre.isEmpty() && year.isEmpty()) {
            etGenre.setError("Enter genre");
            etYear.setError("Enter year");
            return true;
        }  else if (title.isEmpty()) {
            etTitle.setError("Enter title");
            return true;
        }  else if (genre.isEmpty()) {
            etGenre.setError("Enter genre");
            return true;
        }  else if (year.isEmpty()) {
            etYear.setError("Enter year");
            return true;
        } return false;
    }
}