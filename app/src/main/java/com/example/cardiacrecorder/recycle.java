package com.example.cardiacrecorder;

public class recycle {
    private int id;
    private String date,heartRate,systolic,diastolic,comment;

    public recycle() {
    }

    /**
     * all data of class
     * @param id
     * @param date
     * @param heartRate
     * @param systolic
     * @param diastolic
     * @param comment
     */
    public recycle(int id, String date, String heartRate, String systolic, String diastolic, String comment) {
        this.id = id;
        this.date = date;
        this.heartRate = heartRate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
