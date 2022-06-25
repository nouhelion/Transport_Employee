package com.example.templo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EmployeAdapter extends ArrayAdapter<Employe> {

    String id;
    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    Task<QuerySnapshot> reference;
    // constructor for our list view adapter.
    public EmployeAdapter(@NonNull Context context, ArrayList<Employe> EmployeArrayList) {
        super(context, 0, EmployeArrayList);
    }

    @SuppressLint("ResourceAsColor")
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
        Employe employe = getItem(position);

        // initializing our UI components of list view item.
        TextView employeName = listitemView.findViewById(R.id.grid_item);
        //TextView status = listitemView.findViewById(R.id.status);
        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.

        employeName.setText(new StringBuilder().append(employe.getNom()).append(" ").append(employe.getPrenom()).toString());
        RelativeLayout item = listitemView.findViewById(R.id.item);
        if(employe.getStatus())
        {
            item.setBackgroundResource(R.drawable.nixo);
            employeName.setTextColor(R.color.white);
        }


        // below line is use to add item
        // click listener for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                if(employe.getStatus())
                {
                    item.setBackgroundResource(R.drawable.jixo);
                    employeName.setTextColor(R.color.item);
                    employe.setStatus(Boolean.FALSE);
                }
                else{
                    item.setBackgroundResource(R.drawable.nixo);
                    employeName.setTextColor(R.color.white);
                    employe.setStatus(Boolean.TRUE);
                }


                // status.setText("True");
                Toast.makeText(getContext(), "Item clicked is : " + employe.getNom()+" "+employe.getStatus(), Toast.LENGTH_SHORT).show();
                db.collection("Employe")
                        .whereEqualTo("nom",employe.getNom())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        id = document.getId();
                                        db.collection("Employe")
                                                .document(id)
                                                .update("status",employe.getStatus());
                                    }
                                }
                            }
                        });



            }
        });
        return listitemView;
    }
}
