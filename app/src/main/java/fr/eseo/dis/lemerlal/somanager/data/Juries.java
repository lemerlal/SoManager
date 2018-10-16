package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.util.List;

@Entity(tableName = "juries")
public class Juries implements Parcelable {

    public static final Juries.Creator<Juries> CREATOR = new Parcelable.Creator<Juries>(){
        public Juries createFromParcel(Parcel source){
            return new Juries(source);
        }

        public Juries[] newArray(int size){
            return new Juries[size];
        }
    };

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

    @Ignore
    public Juries(Parcel in){
        this.juryID = in.readInt();
        this.date = in.readString();
        this.juryProjectId = in.readInt();
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

    @Ignore
    public int describeContents(){
        return 0;
    }

    @Ignore
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.juryID);
        dest.writeString(this.date);
        dest.writeInt(this.juryProjectId);
    }

}


