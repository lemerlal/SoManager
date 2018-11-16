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

    private Boolean poster;

    private Boolean porte;

    @NonNull
    private int confid;

    @NonNull
    private String forenameSupervisor;

    @NonNull
    private String surnameSupervisor;

    private int juryId;

    @Ignore
    public Projects(int projectID, @NonNull String title, @NonNull Boolean poster, @NonNull int confid, @NonNull String forenameSupervisor, @NonNull String surnameSupervisor, int juryId) {
        this.projectID = projectID;
        this.title = title;
        this.descrip = "";
        this.poster = poster;
        this.confid = confid;
        this.forenameSupervisor = forenameSupervisor;
        this.surnameSupervisor = surnameSupervisor;
        this.juryId = juryId;
    }

    @Ignore
    public Projects(boolean porte,int projectID, @NonNull String title, @NonNull String descrip) {
        this.porte=porte;
        this.projectID = projectID;
        this.title = title;
        this.descrip = "";
    }

    @Ignore
    public Projects(int projectID, @NonNull String title, @NonNull String descrip, @NonNull Boolean poster, @NonNull int confid, @NonNull String forenameSupervisor, @NonNull String surnameSupervisor) {
        this.projectID = projectID;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
        this.confid = confid;
        this.forenameSupervisor = forenameSupervisor;
        this.surnameSupervisor = surnameSupervisor;
    }

    public Projects(int projectID, @NonNull String title, @NonNull String descrip, @NonNull Boolean poster, @NonNull int confid, @NonNull String forenameSupervisor, @NonNull String surnameSupervisor, int juryId) {
        this.projectID = projectID;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
        this.confid = confid;
        this.forenameSupervisor = forenameSupervisor;
        this.surnameSupervisor = surnameSupervisor;
        this.juryId = juryId;
    }

    @Ignore
    public Projects(Parcel in){
        this.projectID = in.readInt();
        this.title = in.readString();
        this.descrip = in.readString();
        this.porte = (in.readInt() == 0) ? false : true;
        this.poster = (in.readInt() == 0) ? false : true;
        this.confid = in.readInt();
        this.forenameSupervisor = in.readString();
        this.surnameSupervisor = in.readString();
        this.juryId = in.readInt();
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

    public Boolean getPoster() {
        return poster;
    }

    public Boolean getPorte() {
        return porte;
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

    public void setPorte(@NonNull Boolean porte) {
        this.porte = porte;
    }

    public void setConfid(@NonNull int confid) {
        this.confid = confid;
    }


    @NonNull
    public String getForenameSupervisor() {
        return forenameSupervisor;
    }

    public void setForenameSupervisor(@NonNull String forenameSupervisor) {
        this.forenameSupervisor = forenameSupervisor;
    }

    @NonNull
    public String getSurnameSupervisor() {
        return surnameSupervisor;
    }

    public void setSurnameSupervisor(@NonNull String surnameSupervisor) {
        this.surnameSupervisor = surnameSupervisor;
    }

    public int getJuryId() {
        return juryId;
    }

    public void setJuryId(@NonNull int juryId) {
        this.juryId = juryId;
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
        dest.writeInt(this.porte ? 1 : 0);
        dest.writeInt(this.confid);
        dest.writeString(this.forenameSupervisor);
        dest.writeString(this.surnameSupervisor);
        dest.writeInt(this.juryId);
    }
}