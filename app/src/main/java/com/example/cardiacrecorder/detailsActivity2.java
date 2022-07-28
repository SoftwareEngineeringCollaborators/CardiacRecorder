package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

/**
 * Detailed View for each data in recycleview
 */
public class detailsActivity2 extends AppCompatActivity {

    TextView heartrate,systolic,diastolic,comment,date,time;
    String dataUid;
    public  Button editButton;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        editButton=findViewById(R.id.editButtonId);
        heartrate=findViewById(R.id.HeartRate);
        systolic=findViewById(R.id.systolicPressure);
        diastolic=findViewById(R.id.diastolicPressure);
        comment=findViewById(R.id.commentId);
        date=findViewById(R.id.dateId);
        time=findViewById(R.id.timeId);

        Bundle bundle = getIntent().getBundleExtra("alldata");

        heartrate.setText(bundle.getString("heartrate"));
        systolic.setText(bundle.getString("systolic"));
        diastolic.setText(bundle.getString("diastolic"));
        comment.setText(bundle.getString("comment"));

        String timestamp=bundle.getString("date");
        Calendar calendar=Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String pDate= android.text.format.DateFormat.format("dd/MM/yyyy", calendar).toString();
        String pTime= android.text.format.DateFormat.format("hh:mm aa", calendar).toString();
        date.setText(pDate);
        time.setText(pTime);


        /**
         * editbutton added
         */
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2=new Bundle();
                bundle2.putInt("id",bundle.getInt("id"));
                bundle2.putString("systolic",bundle.getString("systolic"));
                bundle2.putString("diastolic",bundle.getString("diastolic"));
                bundle2.putString("heartrate",bundle.getString("heartrate"));
                bundle2.putString("comment",bundle.getString("comment"));
                bundle2.putString("date",bundle.getString("date"));
                Intent intent=new Intent(detailsActivity2.this,EditActivity.class);
                intent.putExtra("beforedata",bundle2);
                startActivity(intent);
            }
        });

    }
}