package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UsersDAO {

    @Query("SELECT * FROM users")
    public List<User> findAllUsers();

    @Query("SELECT * FROM users WHERE user= :idUser")
    public User findUsersFromNotes(int idUser);
}
