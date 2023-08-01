package sg.edu.rp.c346.id22017139.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText etTitle, etGenre, etYear;
    Spinner spinnerRating;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinnerRating = findViewById(R.id.spinnerRating);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShowList = findViewById(R.id.btnDelete);

        btnInsert.setOnClickListener(v -> {
            if (!isEmpty()) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = spinnerRating.getSelectedItem().toString();

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(title, genre, year, rating);
                etTitle.getText().clear();
                etGenre.getText().clear();
                etYear.getText().clear();
                Toast.makeText(getApplicationContext(), "Movie inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, movieList.class);
            startActivity(intent);
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