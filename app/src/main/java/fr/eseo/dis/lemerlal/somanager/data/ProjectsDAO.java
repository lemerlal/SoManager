package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProjectsDAO {

    @Query("SELECT * FROM projects")
    public List<Projects> findAllProjects();

    @Query("SELECT * FROM projects WHERE confid =0")
    public List<Projects> findProjectsNoConfid();

    @Query("SELECT * FROM projects WHERE id_project = :idProject")
    public List<Projects> findFromIdProjects(int idProject);

    @Insert
    public void updateMyProject(Projects project);
}
