package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Juries.class, Notes.class, Projects.class, Students.class, User.class,Role.class}, version =2, exportSchema = false)
public abstract class SoManagerDatabase extends RoomDatabase {

    private static SoManagerDatabase INSTANCE;

    public abstract JuriesDAO juriesDao();

    public abstract NotesDAO notesDao();

    public abstract ProjectsDAO projectsDao();

    public abstract StudentsDAO studentsDao();

    public abstract UsersDAO usersDao();

    public abstract RoleDAO rolesDao();


    public static SoManagerDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            //Database needs to be 'bound' to a context, identified by a sub class of RoomDatabase
            // and have a filename where the database will be stored physically on the device
            INSTANCE = Room.databaseBuilder(context, SoManagerDatabase.class, "soManager.db")
                    // For ease of use only => Need to delete this for production code
                    .allowMainThreadQueries()
                    .addCallback(new SoManagerDatabaseCallback())
                    //When migrating delete the database and recreate it
                    .fallbackToDestructiveMigration()
                    //Create the database
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
