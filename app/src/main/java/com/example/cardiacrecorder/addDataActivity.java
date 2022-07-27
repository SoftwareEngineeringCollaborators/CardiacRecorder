package com.example.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addDataActivity extends AppCompatActivity {

    TextView heartrate,systolic,diastolic,comment;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        heartrate=findViewById(R.id.heartRateAddId);
        systolic=findViewById(R.id.systolicPressureAddId);
        diastolic=findViewById(R.id.diastolicPressureAddId);
        comment=findViewById(R.id.commentAddId);
        addButton=findViewById(R.id.addButtonId);


        /**
         * data addition button
         */
         addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(addDataActivity.this,"clicked",Toast.LENGTH_SHORT).show();

                 addData();
             }
         });


    }


    /**
     * data addition on sqlite
     */
    public void addData()
    {


        String date = ""+System.currentTimeMillis();

        long res=new dbmanager(this).addData(heartrate.getText().toString(),systolic.getText().toString(),diastolic.getText().toString(),comment.getText().toString(),date);
        Toast.makeText(addDataActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(addDataActivity.this,Home.class));


    }


}