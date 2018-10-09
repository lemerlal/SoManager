package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by rwoodward on 22/11/17.
 */

public class SoManagerDatabaseCallback extends RoomDatabase.Callback {

    private static String[] INSERT_Juries = new String[] {

            "INSERT INTO juries VALUES(1,2015/08/15);",
            "INSERT INTO juries VALUES(2,2018/04/15);",


    };
    private static String[] INSERT_Notes = new String[]{
            "INSERT INTO notes VALUES(1,'alexandre','Miroslav',15,19);",
            "INSERT INTO notes VALUES(2,'thomas','Johan',10, 14);",

    };

    private static String[] INSERT_Projects = new String[] {
            "INSERT INTO projects VALUES(1,'pfe','dcdscd',false,1,1);",
            "INSERT INTO projects VALUES(2,'gpi','ghhhgh',true,0,2);",

    };

    private static String[] INSERT_Students = new String[]{
            "INSERT INTO students VALUES(1,'alexandre','lemerle');",
            "INSERT INTO students VALUES(2,'thomas','dfdff');",
    };

    private static String[] INSERT_ROLES = new String[] {
            "INSERT INTO roles VALUES(1);",
            "INSERT INTO roles VALUES(2);",

    };

    private static String[] INSERT_user = new String[] {
            "INSERT INTO users VALUES('lemerlal','dfsffkl');",
            "INSERT INTO users VALUES('dfdsfdf','fgdfg');",

    };

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        /*
            for(String creationQuery : CREATE_TABLES){
                db.execSQL(creationQuery);
            }
        */
        Log.d("ROOM_Database_Insert:","Insert Artistes");
        for(String creationQuery : INSERT_Juries){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Internautes");
        for(String creationQuery : INSERT_Notes){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Pays");
        for(String creationQuery : INSERT_Projects){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Films");
        for(String creationQuery : INSERT_Students){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Roles");
        for(String creationQuery : INSERT_ROLES){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Notations");
        for(String creationQuery : INSERT_user){
            db.execSQL(creationQuery);
        }
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        Log.d("ROOM_Database:","onOpen()");
        super.onOpen(db);
    }
}
