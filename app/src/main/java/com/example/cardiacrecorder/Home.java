package com.example.cardiacrecorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * RecycleView Showing all the data from sql lite Database
 */
public class Home extends AppCompatActivity {


    private RecyclerView recyclerView;


    private HomeAdapter homeAdapter;
    private List<recycle> recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //RecyclerView extends its size by the length of database
        recycleList=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerListId);


        //linear layout manager fit it to this class
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        //retrive information from sqlite database
        readComments();


        //floating action button to add new data
        FloatingActionButton fab = findViewById(R.id.addDataId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,addDataActivity.class));
            }
        });


    }


    /**
     * fetch data from database
     * added in list
     * put on adapter
     */

    private void readComments() {
       Cursor cursor=new dbmanager(this).readData();

       while ( cursor.moveToNext() )
       {
           recycle recycle=new recycle(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
           recycleList.add(recycle);

       }

        /**
         * set in adapter
         */

        homeAdapter=new HomeAdapter(Home.this,recycleList);
        recyclerView.setAdapter(homeAdapter);

    }


}

