package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    EditText heartrate,systolic,diastolic,comment;
    String hr,s,d,c;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        heartrate=findViewById(R.id.heartRateAddId);
        systolic=findViewById(R.id.systolicPressureAddId);
        diastolic=findViewById(R.id.diastolicPressureAddId);
        comment=findViewById(R.id.commentAddId);
        updateButton=findViewById(R.id.addButtonId);


        dbmanager dbmanager=new dbmanager(this);
        Bundle bundle = getIntent().getBundleExtra("beforedata");

        heartrate.setText(bundle.getString("heartrate"));
        systolic.setText(bundle.getString("systolic"));
        diastolic.setText(bundle.getString("diastolic"));
        comment.setText(bundle.getString("comment"));



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditActivity.this,"Clicked",Toast.LENGTH_SHORT).show();

                hr=heartrate.getText().toString();
                s=systolic.getText().toString();
                d=diastolic.getText().toString();
                c=comment.getText().toString();
                String date = ""+System.currentTimeMillis();

                ContentValues cv=new ContentValues();
                cv.put("heartRate",hr);
                cv.put("date",date);
                cv.put("systolic",s);
                cv.put("diastolic",d);
                cv.put("comment",c);





                long recedit=dbmanager.updateData(bundle.getInt("id"),hr,s,d,c,date);
                if(recedit!=-1)
                {
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(EditActivity.this,Home.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error updating",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
