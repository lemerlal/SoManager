package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "projects")

public class Projects implements Parcelable{

    public static final Parcelable.Creator<Projects> CREATOR = new Parcelable.Creator<Projects>(){
        public Projects createFromParcel(Parcel source){
            return new Projects(source);
        }

        public Projects[] newArray(int size){
            return new Projects[size];
        }
    };

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
    private int idSupervisor;

    public Projects(int projectID, @NonNull String title){
        this.projectID=projectID;
        this.title=title;
    }


    public Projects(int projectID, @NonNull String title, @NonNull String descrip, @NonNull Boolean poster, @NonNull int confid, @NonNull int idSupervisors) {
        this.projectID = projectID;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
        this.confid = confid;
        this.idSupervisor = idSupervisor;
    }

    @Ignore
    public Projects(Parcel in){
        this.projectID = in.readInt();
        this.title = in.readString();
        this.descrip = in.readString();
        this.poster = (in.readInt() == 0) ? false : true;
        this.confid = in.readInt();
        this.idSupervisor = in.readInt();
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


    @NonNull
    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(@NonNull int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    @Ignore
    public int describeContents(){
        return 0;
    }

    @Ignore
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.projectID);
        dest.writeString(this.title);
        dest.writeString(this.descrip);
        dest.writeInt(this.poster ? 1 : 0);
        dest.writeInt(this.confid);
        dest.writeInt(this.idSupervisor);
    }
}
