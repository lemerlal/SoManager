package fr.eseo.dis.lemerlal.somanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "role")
public class Role {

    @PrimaryKey
    @ColumnInfo(name="id_role")
    private int roleID;


    public Role(int roleID) {
        this.roleID = roleID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
