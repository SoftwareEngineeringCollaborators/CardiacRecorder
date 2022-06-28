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


         addButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(addDataActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                 addData();
             }
         });


    }

    public void addData()
    {
        /*
        loader.setMessage("Adding a new data");
        loader.setCanceledOnTouchOutside(false);
        loader.show();
         */

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dataInfo");
        String dataId = reference.push().getKey();


        String date = ""+System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("dataId", dataId);
        hashMap.put("date", date);
        hashMap.put("heartRate", heartrate.getText().toString());
        hashMap.put("systolic", systolic.getText().toString());
        hashMap.put("diastolic", diastolic.getText().toString());
        hashMap.put("comment", comment.getText().toString());



        reference.child(dataId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(addDataActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(addDataActivity.this,Home.class));

                }
                else {
                    Toast.makeText(addDataActivity.this, "Error Adding Data", Toast.LENGTH_SHORT).show();

                }

            }
        });


        //loader.dismiss();
    }
}