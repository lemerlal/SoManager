package fr.eseo.dis.lemerlal.somanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "project")

public class Projects {

    @PrimaryKey
    @ColumnInfo(name="id_project")
    private int projectID;

    @NonNull
    private String title;

    @NonNull
    private String descrip;

    @NonNull
    private Boolean poster;

    @NonNull
    private int confid;

    @NonNull
    private String[][] supervisor;

    @NonNull
    @Relation(parentColumn = "user_id", entityColumn = "user_id", entity = Students.class)
    private List<Students> students;



    public Projects(@NonNull String[][] supervisor, int projectID, @NonNull String title, @NonNull String descrip, @NonNull Boolean poster, @NonNull int confid, @NonNull List<Students> students) {
        this.projectID = projectID;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
        this.confid = confid;
        this.students = students;
        this.supervisor = supervisor;
    }


    public int getProjectID() {
        return projectID;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescrip() {
        return descrip;
    }

    @NonNull
    public Boolean getPoster() {
        return poster;
    }

    @NonNull
    public int getConfid() {
        return confid;
    }

    @NonNull
    public List<Students> getStudents() {
        return students;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setDescrip(@NonNull String descrip) {
        this.descrip = descrip;
    }

    public void setPoster(@NonNull Boolean poster) {
        this.poster = poster;
    }

    public void setConfid(@NonNull int confid) {
        this.confid = confid;
    }

    public void setStudents(@NonNull List<Students> students) {
        this.students = students;
    }

    @NonNull
    public String[][] getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(@NonNull String[][] supervisor) {
        this.supervisor = supervisor;
    }
}
