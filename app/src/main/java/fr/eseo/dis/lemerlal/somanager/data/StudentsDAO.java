package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentsDAO {

    @Query("SELECT * FROM students")
    public List<Students> findAllStudents();

    @Query("SELECT * FROM students WHERE id_student= :idUser")
    public Students findNotesFromIdStudent(int idUser);

    @Insert
    public void updateMyStudents(Students students);

}
