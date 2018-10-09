package fr.eseo.dis.lemerlal.somanager;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM notes")
    public List<Notes> findAllNotes();

    @Query("SELECT * FROM notes WHERE id_user = :idUser")
    public Notes findNotesFromIdUsers(int idUser);
}
