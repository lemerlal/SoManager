package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JuriesDAO {


        @Query("SELECT * FROM juries")
        public List<Juries> findAllJuries();

        @Query("SELECT * FROM juries WHERE id_jury = :idJurie")
        public List<Juries> findJuriesFromId(int idJurie);

        @Insert
        public void updateMyJuries(Juries juries);


}
