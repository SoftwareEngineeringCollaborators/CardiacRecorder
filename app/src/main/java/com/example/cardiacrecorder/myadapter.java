package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {

    Context context;
    SQLiteDatabase sqLiteDatabase;
    ArrayList<recycle> dataholder;

    public myadapter(Context context,  ArrayList<recycle> dataholder) {
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.danger_bp,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        String s1=dataholder.get(position).getSystolic().toString()+"mmHg";
        holder.dSystolic.setText(s1);
        String s2=dataholder.get(position).getDiastolic().toString()+"mmHg";
        holder.dDistolic.setText(s2);
        String s3=dataholder.get(position).getHeartRate().toString()+"bps";
        holder.dPulse.setText(s3);
        /*holder.dComment.setText(dataholder.get(position).getComment());*/
        final  recycle md=dataholder.get(position);
        holder.dms_date.setText(md.getDate());

        /*holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putInt("id",md.getId());
                bundle.putString("systolic",md.getSystolic());
                bundle.putString("diastolic",md.getDiastolic());
                bundle.putString("pulse",md.getHeartRate());
                bundle.putString("comment",md.getComment());
                bundle.putString("ms_date",md.getDate());

                Intent intent=new Intent(context,addData.class);
                intent.putExtra("userdata",bundle);
                context.startActivity(intent);

            }
        });
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbmanager mgr=new dbmanager(context);
                String Table_name="Tbl_contact";
                SQLiteDatabase db=mgr.getReadableDatabase();
                long rec=db.delete(Table_name,"id="+md.getId(),null);
                if(rec!=-1)
                {
                    Toast.makeText(context,"Delete Successfully",Toast.LENGTH_SHORT).show();
                    dataholder.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class  myviewholder extends RecyclerView.ViewHolder{

        TextView dSystolic,dDistolic,dPulse,dComment,dms_date,dTime;
        Button button1,button2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            dSystolic=(TextView) itemView.findViewById(R.id.dsystolicShowId);
            dDistolic=(TextView) itemView.findViewById(R.id.ddiastolicShowId);
            dPulse=(TextView) itemView.findViewById(R.id.dangerbpShowId);
            /*dComment=(TextView) itemView.findViewById(R.id.comment_list);*/
            dms_date=(TextView) itemView.findViewById(R.id.dateTv);

           /* button1=(Button) itemView.findViewById(R.id.delete_list);
            button2=(Button) itemView.findViewById(R.id.update_list);*/
        }
    }
}