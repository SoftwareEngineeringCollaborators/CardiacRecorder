package com.example.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

public class detailsActivity extends AppCompatActivity {

    TextView heartrate,systolic,diastolic,comment,date,time;
    String dataUid;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=getIntent();
        dataUid=intent.getStringExtra("dataUid");

        databaseReference= FirebaseDatabase.getInstance().getReference("dataInfo").child(dataUid);


        heartrate=findViewById(R.id.HeartRate);
        systolic=findViewById(R.id.systolicPressure);
        diastolic=findViewById(R.id.diastolicPressure);
        comment=findViewById(R.id.commentId);
        date=findViewById(R.id.dateId);
        time=findViewById(R.id.timeId);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                heartrate.setText(snapshot.child("heartRate").getValue(String.class));
                systolic.setText(snapshot.child("systolic").getValue(String.class));
                diastolic.setText(snapshot.child("diastolic").getValue(String.class));
                comment.setText(snapshot.child("comment").getValue(String.class));

                String timestamp=snapshot.child("date").getValue(String.class);
                Calendar calendar=Calendar.getInstance(Locale.getDefault());
                calendar.setTimeInMillis(Long.parseLong(timestamp));
                //String pTime= android.text.format.DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
                String pDate= android.text.format.DateFormat.format("dd/MM/yyyy", calendar).toString();
                String pTime= android.text.format.DateFormat.format("hh:mm aa", calendar).toString();
                date.setText(pDate);
                time.setText(pTime);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}