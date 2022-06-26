package com.example.templo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StationAdapter extends ArrayAdapter <Station>{

    String id;
    // creating a variable for firebasefirestore.
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    Task<QuerySnapshot> reference;
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
        TextView stationName = listitemView.findViewById(R.id.grid_item);
        //TextView status = listitemView.findViewById(R.id.status);
        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.

        stationName.setText(station.getNom());
        RelativeLayout item = listitemView.findViewById(R.id.item);
        if(station.getStatus())
        {
            item.setBackgroundResource(R.drawable.nixo);
            stationName.setTextColor(R.color.white);
        }


        // below line is use to add item
        // click listener for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                if(station.getStatus())
                {
                    item.setBackgroundResource(R.drawable.jixo);
                    stationName.setTextColor(R.color.item);
                    station.setStatus(Boolean.FALSE);
                }
                else{
                    item.setBackgroundResource(R.drawable.nixo);
                    stationName.setTextColor(R.color.white);
                    station.setStatus(Boolean.TRUE);
                }


               // status.setText("True");
                Toast.makeText(getContext(), "Item clicked is : " + station.getNom()+" "+station.getStatus(), Toast.LENGTH_SHORT).show();
                db.collection("Station")
                        .whereEqualTo("nom",station.getNom())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                       id = document.getId();
                                        db.collection("Station")
                                                .document(id)
                                                .update("status",station.getStatus());
                                        db.collection("conducteur")
                                                .document(user.getUid())
                                                .update("stationActuelle",station.getNom());
                                    }
                                }
                            }
                        });



            }
        });
        return listitemView;
    }

}
