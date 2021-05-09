package com.example.ecommerceapp.Feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class feedbackRetrieveData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Feedback> feedback;
    private FeedbackAdapter feedbackAdapter;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_retrieve_data);


        recyclerView=findViewById(R.id.recycler_feedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedback=new ArrayList<Feedback>();

        dref = FirebaseDatabase.getInstance().getReference().child("Feedback");
        dref.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            for (DataSnapshot dataSnapshotl : dataSnapshot.getChildren()) {
                Feedback feedback1 = dataSnapshotl.getValue(Feedback.class);
                feedback.add(feedback1);
            }

            feedbackAdapter = new FeedbackAdapter(feedbackRetrieveData.this, feedback);
            recyclerView.setAdapter(feedbackAdapter);



        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }


    };

}