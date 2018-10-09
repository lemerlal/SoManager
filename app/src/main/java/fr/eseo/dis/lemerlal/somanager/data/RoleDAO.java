package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RoleDAO {

    @Query("SELECT * FROM role")
    public List<Role> findAllRole();

    @Query("SELECT * FROM role WHERE id_role= :idUser")
    public Role findRoleFromIdUser(int idUser);
}
