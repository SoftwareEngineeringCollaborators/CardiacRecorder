package com.example.cardiacrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbmanager extends SQLiteOpenHelper {
    private static final String dbname="dbbp";
    public dbmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    /**
     * @param db is the sqlite database class object
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table tbl_bp (dataid integer primary key autoincrement, date text, heartRate text, systolic text, diastolic text, comment text)";
        db.execSQL(qry);
    }

    /**
     *here we update the database
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry="DROP TABLE IF EXISTS tbl_bp";
        db.execSQL(qry);
        onCreate(db);
    }


    public  long  addData( String heartRate, String systolic, String diastolic , String comment, String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("heartRate",heartRate);
        cv.put("date",date);
        cv.put("systolic",systolic);
        cv.put("diastolic",diastolic);

        cv.put("comment",comment);


        long res=db.insert("tbl_bp",null,cv);
        return res;
    }

    public  long updateData(int id, String heartRate, String systolic, String diastolic , String comment, String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("heartRate",heartRate);
        cv.put("date",date);
        cv.put("systolic",systolic);
        cv.put("diastolic",diastolic);

        cv.put("comment",comment);

        long res=db.update("tbl_bp",cv,"dataid="+id,null);
        return  res;
    }

    public Cursor readData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="select * from tbl_bp order by dataid  desc";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public long deleteData(int id)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        long rec=db.delete("Tbl_bp","dataid="+id,null);
        return rec;
    }

}
