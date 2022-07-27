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

public class Home extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private TextView textView;
    private EditText editText;

    private ProgressDialog loader;

    dbmanager mgr;

    private HomeAdapter homeAdapter;
    private List<recycle> recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recycleList=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerListId);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        //readComments();
        readComments2();

        FloatingActionButton fab = findViewById(R.id.addDataId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,addDataActivity.class));
            }
        });


    }

    private void readComments() {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("dataInfo");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recycleList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    recycle recycle=dataSnapshot.getValue(recycle.class);
                    recycleList.add(recycle);
                }
                homeAdapter=new HomeAdapter(Home.this, recycleList);


                recyclerView.setAdapter(homeAdapter);
                //HomeAdapter.notifyDataSetChanged();
                //recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void readComments2() {
       Cursor cursor=new dbmanager(this).readData();

       while ( cursor.moveToNext() )
       {
           recycle recycle=new recycle(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
           recycleList.add(recycle);

       }

        homeAdapter=new HomeAdapter(Home.this,recycleList);
        recyclerView.setAdapter(homeAdapter);

    }

    private void addInfo() {
        /*
        loader.setMessage("Adding a new data");
        loader.setCanceledOnTouchOutside(false);
        loader.show();
         */

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dataInfo");
        String dataId = reference.push().getKey();

        String date = ""+System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", editText.getText().toString());
        hashMap.put("dataId", dataId);


        reference.child(dataId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Home.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(Home.this, "Error Adding Data", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //loader.dismiss();


    }
}

