package fr.eseo.dis.lemerlal.somanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "students")
public class Students {
    @PrimaryKey
    @ColumnInfo(name="id_student")
    private int studentID;

    @NonNull
    private String surename;

    @NonNull
    private String forename;


    public Students(int studentID, @NonNull String surename, @NonNull String forename) {
        this.studentID = studentID;
        this.surename = surename;
        this.forename = forename;
    }

    public int getStudentID() {
        return studentID;
    }

    @NonNull
    public String getSurename() {
        return surename;
    }

    @NonNull
    public String getForename() {
        return forename;
    }

    public void setStudentID(int userID) {
        this.studentID = userID;
    }

    public void setSurename(@NonNull String surename) {
        this.surename = surename;
    }

    public void setForename(@NonNull String forename) {
        this.forename = forename;
    }
}


