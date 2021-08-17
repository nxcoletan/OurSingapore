package sg.edu.rp.c346.id20025835.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    EditText etID, etName, etDescription, etArea;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            rb1 = (RadioButton) findViewById(R.id.radio1);
            rb2 = (RadioButton) findViewById(R.id.radio2);
            rb3 = (RadioButton) findViewById(R.id.radio3);
            rb4 = (RadioButton) findViewById(R.id.radio4);
            rb5 = (RadioButton) findViewById(R.id.radio5);
            rg = (RadioGroup) findViewById(R.id.rgStars);
            btnCancel = (Button) findViewById(R.id.btnCancel);
            btnDelete = (Button) findViewById(R.id.btnDelete);
            btnUpdate = (Button) findViewById(R.id.btnUpdate);
            etID = (EditText) findViewById(R.id.etID);
            etName = (EditText) findViewById(R.id.etName2);
            etDescription = (EditText) findViewById(R.id.etDescription2);
            etArea = (EditText) findViewById(R.id.etArea2);

            Intent i = getIntent();
            final Island currentIsland = (Island) i.getSerializableExtra("island");

            etID.setText(currentIsland.getId()+"");
            etName.setText(currentIsland.getName());
            etDescription.setText(currentIsland.getDescription());
            etArea.setText(currentIsland.getArea()+"");
            switch (currentIsland.getStars()){
                case 5: rb5.setChecked(true);
                    break;
                case 4: rb4.setChecked(true);
                    break;
                case 3: rb3.setChecked(true);
                    break;
                case 2: rb2.setChecked(true);
                    break;
                case 1: rb1.setChecked(true);
            }

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                                                 myBuilder.setTitle("Danger");
                                                 myBuilder.setMessage("Are you sure you want to discard the changes?");
                                                 myBuilder.setCancelable(false);

                                                 myBuilder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                                                     @Override
                                                     public void onClick(DialogInterface dialog, int which) {
                                                         DBHelper dbh = new DBHelper(ThirdActivity.this);
                                                         currentIsland.setName(etName.getText().toString().trim());
                                                         currentIsland.setDescription(etDescription.getText().toString().trim());
                                                         int area = 0;
                                                         try {
                                                             area = Integer.valueOf(etArea.getText().toString().trim());
                                                         } catch (Exception e) {
                                                             Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                                                             return;
                                                         }
                                                         currentIsland.setArea(area);

                                                         int selectedRB = rg.getCheckedRadioButtonId();
                                                         RadioButton rb = (RadioButton) findViewById(selectedRB);
                                                         currentIsland.setStars(Integer.parseInt(rb.getText().toString()));
                                                         int result = dbh.updateIsland(currentIsland);
                                                         if (result > 0) {
                                                             Toast.makeText(ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                                                             Intent i = new Intent();
                                                             setResult(RESULT_OK);
                                                             finish();
                                                         }
                                                         ;

                                                         myBuilder.setNeutralButton("Do Not Discard", null);
                                                         AlertDialog myDialog = myBuilder.create();
                                                         myDialog.show();
                                                         Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                                                     }
                                                 });
                                             }
                                         });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbh = new DBHelper(ThirdActivity.this);
                    int result = dbh.deleteIsland(currentIsland.getId());
                    if (result > 0) {

                        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                        myBuilder.setTitle("Danger");
                        myBuilder.setMessage("Are you sure you want to delete the Island");
                        myBuilder.setCancelable(false);

                        myBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ThirdActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent();
                                setResult(RESULT_OK);
                                finish();
                            }
                        });

                        myBuilder.setNeutralButton("Cancel", null);
                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                };
            });
    }
}
