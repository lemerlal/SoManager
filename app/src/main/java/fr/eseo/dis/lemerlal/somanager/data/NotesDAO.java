package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM notes")
    public List<Notes> findAllNotes();

    @Query("SELECT * FROM notes WHERE id_user = :idUser")
    public Notes findNotesFromIdUsers(int idUser);

    @Query("UPDATE Notes SET myNote = :new_notes  WHERE id_user = :idUser")
    public void updateMyNote(double new_notes,int idUser);
}
