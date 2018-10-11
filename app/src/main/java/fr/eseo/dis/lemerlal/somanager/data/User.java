package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    @ColumnInfo(name="id_user")
    private int userId;

    @NonNull
    private String user;

    @NonNull
    private String pass;

    @NonNull
    private String surename;

    @NonNull
    private String forename;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User(int userId, @NonNull String user, @NonNull String pass) {
        this.userId=userId;
        this.user = user;
        this.pass = pass;
        this.surename = surename;
        this.forename = forename;
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
    public String getUser() {
        return user;
    }

    @NonNull
    public String getPass() {
        return pass;
    }

    public void setUser(@NonNull String user) {
        this.user = user;
    }

    public void setPass(@NonNull String pass) {
        this.pass = pass;
    }

    public void setSurename(@NonNull String surename) {
        this.surename = surename;
    }

    public void setForename(@NonNull String forename) {
        this.forename = forename;
    }
}


