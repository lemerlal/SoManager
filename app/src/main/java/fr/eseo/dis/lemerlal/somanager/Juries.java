package fr.eseo.dis.lemerlal.somanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "juries")
public class Juries {
        @PrimaryKey
        @ColumnInfo(name="id_jury")
        private int juryID;

        @NonNull
        private Date date;

        @NonNull
        @Relation(parentColumn = "id_project", entityColumn = "id_project", entity = Projects.class)
        private List<Projects> students;


    public Juries(int juryID, @NonNull Date date, @NonNull List<Projects> students) {
        this.juryID = juryID;
        this.date = date;
        this.students = students;
    }

    public int getJuryID() {
        return juryID;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    @NonNull
    public List<Projects> getStudents() {
        return students;
    }

    public void setJuryID(int juryID) {
        this.juryID = juryID;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public void setStudents(@NonNull List<Projects> students) {
        this.students = students;
    }
}


