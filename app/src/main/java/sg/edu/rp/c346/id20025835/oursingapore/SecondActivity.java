package sg.edu.rp.c346.id20025835.oursingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Island> songList;
    CustomAdapter caIsland;
    int requestCode = 9;
    Button btn5Stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

            lv = (ListView) this.findViewById(R.id.lv);
            btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);

            DBHelper dbh = new DBHelper(this);
            songList = dbh.getAllIsland();
            dbh.close();

            caIsland = new CustomAdapter(this, R.layout.row, islandList);
            lv.setAdapter(caIsland);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                    i.putExtra("song", songList.get(position));
                    startActivityForResult(i, requestCode);
                }
            });

            btn5Stars.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbh = new DBHelper(SecondActivity.this);
                    caIsland.clear();
                    caIsland.addAll(dbh.getAllIslandsByStars(5));
                    caIsland.notifyDataSetChanged();
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == this.requestCode && resultCode == RESULT_OK){
                DBHelper dbh = new DBHelper(this);
                songList.clear();
                songList.addAll(dbh.getAllIsland());
                dbh.close();
                caIsland.notifyDataSetChanged();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }