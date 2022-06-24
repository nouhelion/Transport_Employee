package com.example.templo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class StationAdapter extends ArrayAdapter <Station>{

    // constructor for our list view adapter.
    public StationAdapter(@NonNull Context context, ArrayList<Station> StationArrayList) {
        super(context, 0, StationArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Station station = getItem(position);

        // initializing our UI components of list view item.
        TextView stationName = listitemView.findViewById(R.id.station);
        //TextView status = listitemView.findViewById(R.id.status);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        stationName.setText(station.getNom());
        //status.setText(station.getStatus());

        // below line is use to add item
        // click listener for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : " + station.getNom(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }

}
