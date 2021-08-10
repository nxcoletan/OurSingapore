package sg.edu.rp.c346.id20025835.oursingapore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
        Context parent_context;
        int layout_id;
        ArrayList<Island> islandList;

        public CustomAdapter(Context context, int resource, ArrayList<Island> objects) {
            super(context, resource, objects);

            parent_context = context;
            layout_id = resource;
            islandList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(layout_id, parent, false);

            TextView tvName = rowView.findViewById(R.id.textViewName);
            TextView tvArea = rowView.findViewById(R.id.textViewArea);
            TextView tvDescription = rowView.findViewById(R.id.textViewDescription);

            RatingBar ratingBar = rowView.findViewById(R.id.ratingBar);

            Island currentList = islandList.get(position);

            tvName.setText(currentList.getName());
            tvArea.setText(currentList.getArea());
            tvDescription.setText(currentList.getDescription());
            ratingBar.setRating(currentList.getStars());

            if (currentList.getYearReleased() >= 2019) {
                ivNew.setVisibility(View.VISIBLE);
            } else {
                ivNew.setVisibility(View.INVISIBLE);
            }
            return rowView;
        }
    }
}
