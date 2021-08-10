package sg.edu.rp.c346.id20025835.oursingapore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView edID;
    EditText etName, etDescription, etArea;
    Button btnInsert, btnShowList;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etArea = findViewById(R.id.etArea);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        ratingBar = findViewById(R.id.ratingBar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = etName.getText().toString().trim();
                String Description = etDescription.getText().toString().trim();
                if (Name.length() == 0 || Description.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String area_str = etArea.getText().toString().trim();
                int area = 0;
                try {
                    area = Integer.valueOf(area_str);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                int rating = (int) ratingBar.getRating();
                dbh.insertIsland(Name, Description, area, rating);
                dbh.close();
                Toast.makeText(MainActivity.this, "Insertion Successful", Toast.LENGTH_LONG).show();

                etName.setText("");
                etDescription.setText("");
                etArea.setText("");
            }
        });

        btnShowList.setOnClickListener((arg0) -> {
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(i);
        });
    }
}