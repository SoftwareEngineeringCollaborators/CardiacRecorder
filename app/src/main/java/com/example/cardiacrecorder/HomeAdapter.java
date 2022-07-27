package com.example.cardiacrecorder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class HomeAdapter  extends  RecyclerView.Adapter {

    private Context mContext;
    private List<recycle> mDataList;

    int danger_bp=1,normal_bp=2;

    public HomeAdapter(Context mContext,  List<recycle> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==normal_bp) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.blood_pressure_layout, parent, false);

            return new normalViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(mContext).inflate(R.layout.danger_bp, parent, false);

            return new dangerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        recycle recycle=mDataList.get(position);

        if(holder.getClass()==normalViewHolder.class)
        {
            normalViewHolder viewHolder=(normalViewHolder)holder;
            viewHolder.heartbeat.setText(recycle.getHeartRate());

            String timestamp=recycle.getDate();
            Calendar calendar=Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(Long.parseLong(timestamp));
            String pTime= android.text.format.DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
            ((normalViewHolder) holder).date.setText(pTime);

            viewHolder.systolic.setText(recycle.getSystolic());
            viewHolder.diastolic.setText(recycle.getDiastolic());

        }
        else
        {
            dangerViewHolder viewHolder=(dangerViewHolder)holder;
            viewHolder.heartbeat.setText(recycle.getHeartRate());

            String timestamp=recycle.getDate();
            Calendar calendar=Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(Long.parseLong(timestamp));
            String pTime= android.text.format.DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
            ((dangerViewHolder) holder).date.setText(pTime);


            viewHolder.systolic.setText(recycle.getSystolic());
            viewHolder.diastolic.setText(recycle.getDiastolic());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int x=recycle.getId();
                //Toast.makeText(mContext,.toString() )
                Bundle bundle=new Bundle();
                bundle.putInt("id",recycle.getId());
                bundle.putString("systolic",recycle.getSystolic());
                bundle.putString("diastolic",recycle.getDiastolic());
                bundle.putString("heartrate",recycle.getHeartRate());
                bundle.putString("comment",recycle.getComment());
                bundle.putString("date",recycle.getDate());

                Intent intent=new Intent(mContext,detailsActivity2.class);
                intent.putExtra("alldata",bundle);
                mContext.startActivity(intent);


            }
        });

        /**
         * the work of delete
         */
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setMessage("Are You Sure Want to Delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbmanager dbmanager=new dbmanager(mContext);


                                SQLiteDatabase db=dbmanager.getReadableDatabase();
                                long rec=db.delete("Tbl_bp","dataid="+recycle.getId(),null);
                                if(rec!=-1)
                                {
                                    Toast.makeText(mContext,"Delete Successfully",Toast.LENGTH_SHORT).show();
                                    mDataList.remove(holder.getAdapterPosition());
                                    notifyDataSetChanged();
                                }
                                else
                                {
                                    Toast.makeText(mContext,"Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                return  false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        recycle recycle=mDataList.get(position);
        int check1= Integer.parseInt(recycle.getSystolic());
        int check2= Integer.parseInt(recycle.getDiastolic());
        //Toast.makeText(mContext,check,Toast.LENGTH_SHORT).show();


        if((check1>=90 && check1<=140) && (check2>=60 && check2<=90))
        {
            return normal_bp;
        }
        else
        {
            return danger_bp;
        }



    }

    public  class normalViewHolder extends RecyclerView.ViewHolder  {

        public TextView heartbeat,date,systolic,diastolic;

        public normalViewHolder(@NonNull View itemView) {
            super(itemView);

            heartbeat=itemView.findViewById(R.id.heartrateShowId);
            date=itemView.findViewById(R.id.dateTv);
            systolic=itemView.findViewById(R.id.systolicShowId);
            diastolic=itemView.findViewById(R.id.diastolicShowId);

        }


    }

    public  class dangerViewHolder extends RecyclerView.ViewHolder  {

        public TextView heartbeat,date,systolic,diastolic;

        public dangerViewHolder(@NonNull View itemView) {
            super(itemView);

            heartbeat=itemView.findViewById(R.id.dangerbpShowId);
            date=itemView.findViewById(R.id.dateTv);
            systolic=itemView.findViewById(R.id.dsystolicShowId);
            diastolic=itemView.findViewById(R.id.ddiastolicShowId);

        }


    }



}
