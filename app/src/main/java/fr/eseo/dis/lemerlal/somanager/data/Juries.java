package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.util.List;

@Entity(tableName = "juries")
public class Juries {
        @PrimaryKey
        @ColumnInfo(name="id_jury")
        private int juryID;

        @NonNull
        private String date;

        @NonNull
        private int juryProjectId;


    public Juries(int juryID, @NonNull String date, @NonNull int juryProjectId) {
        this.juryID = juryID;
        this.date = date;
        this.juryProjectId = juryProjectId;
    }

    public int getJuryID() {
        return juryID;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public int getJuryProjectId() {
        return juryProjectId;
    }


    public void setJuryID(int juryID) {
        this.juryID = juryID;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setJuryProjectId(@NonNull int juryProjectId) {
        this.juryProjectId = juryProjectId;
    }

}


