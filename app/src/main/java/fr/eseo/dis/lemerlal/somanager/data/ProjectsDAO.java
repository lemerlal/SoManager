package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProjectsDAO {

    @Query("SELECT * FROM projects")
    public List<Projects> findAllProjects();

    @Query("SELECT * FROM projects WHERE id_project = :idProject")
    public Projects findNotesFromIdProjects(int idProject);
}
