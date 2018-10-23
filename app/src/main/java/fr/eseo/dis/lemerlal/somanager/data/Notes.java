package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Update;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "notes")
public class Notes implements Parcelable {

    public static final Parcelable.Creator<Notes> CREATOR = new Parcelable.Creator<Notes>(){
        public Notes createFromParcel(Parcel source){
            return new Notes(source);
        }

        public Notes[] newArray(int size){
            return new Notes[size];
        }
    };
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

    @Ignore
    public Notes(Parcel in){
        this.userID = in.readInt();
        this.surename = in.readString();
        this.forename = in.readString();
        this.myNote = in.readDouble();
        this.avgNote = in.readDouble();
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

    @Ignore
    public int describeContents(){
        return 0;
    }

    @Ignore
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.userID);
        dest.writeString(this.surename);
        dest.writeString(this.forename);
        dest.writeDouble(this.myNote);
        dest.writeDouble(this.avgNote);
    }
}


