package fr.eseo.dis.lemerlal.somanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey
    @ColumnInfo(name="id_user")
    private int userID;

    @NonNull
    private String surename;

    @NonNull
    private String forename;

    @NonNull
    private double myNote;

    @NonNull
    private double avgNote;


    public Notes(int userID, @NonNull String surename, @NonNull String forename, @NonNull double myNote, @NonNull double avgNote) {
        this.userID = userID;
        this.surename = surename;
        this.forename = forename;
        this.myNote = myNote;
        this.avgNote = avgNote;
    }

    public int getUserID() {
        return userID;
    }

    @NonNull
    public String getSurename() {
        return surename;
    }

    @NonNull
    public String getForename() {
        return forename;
    }

    @NonNull
    public double getMyNote() {
        return myNote;
    }

    @NonNull
    public double getAvgNote() {
        return avgNote;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setSurename(@NonNull String surename) {
        this.surename = surename;
    }

    public void setForename(@NonNull String forename) {
        this.forename = forename;
    }

    public void setMyNote(@NonNull double myNote) {
        this.myNote = myNote;
    }

    public void setAvgNote(@NonNull double avgNote) {
        this.avgNote = avgNote;
    }
}


