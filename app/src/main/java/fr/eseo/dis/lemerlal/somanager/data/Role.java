package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "role")
public class Role {

    @PrimaryKey
    @ColumnInfo(name="id_role")
    private int roleID;
    @NonNull
    private int userId;

    public Role(int roleID, @NonNull int userId) {
        this.roleID=roleID;
        this.userId = userId;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
