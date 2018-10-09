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


    public Juries(int juryID, @NonNull String date) {
        this.juryID = juryID;
        this.date = date;
    }

    public int getJuryID() {
        return juryID;
    }

    @NonNull
    public String getDate() {
        return date;
    }


    public void setJuryID(int juryID) {
        this.juryID = juryID;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

}


