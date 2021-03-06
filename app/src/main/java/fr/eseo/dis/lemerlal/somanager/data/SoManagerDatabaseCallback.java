package fr.eseo.dis.lemerlal.somanager.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by rwoodward on 22/11/17.
 */

public class SoManagerDatabaseCallback extends RoomDatabase.Callback {

    private static String[] INSERT_user = new String[] {
            "INSERT INTO users VALUES(1,'lemerlal','1234','Alexandre','Lemerle');",
            "INSERT INTO users VALUES(2,'nordstga','1234','Gaelle','Nordstrom');",

    };

    private static String[] INSERT_Students = new String[]{
            //"INSERT INTO students VALUES(1,'alexandre','lemerle');",
            //"INSERT INTO students VALUES(2,'thomas','dfdff');",
    };

    private static String[] INSERT_Juries = new String[] {

            //"INSERT INTO juries VALUES(1,15,1);",
            //"INSERT INTO juries VALUES(2,15,2);",


    };
    private static String[] INSERT_Notes = new String[]{
            //"INSERT INTO notes VALUES(1,'alexandre','Miroslav',15,19);",
            //"INSERT INTO notes VALUES(2,'thomas','Johan',10, 14);",

    };

    private static String[] INSERT_ROLES = new String[] {
            //"INSERT INTO role VALUES(0,1);",
            //"INSERT INTO role VALUES(1,2);",

    };

    private static String[] INSERT_Projects = new String[] {
            /**
            "INSERT INTO projects VALUES(1,'a','dcdscd','false',1,1);",
            "INSERT INTO projects VALUES(2,'b','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(3,'c','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(4,'d','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(5,'f','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(6,'g','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(7,'h','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(8,'i','ghhhgh','true',0,2);",
            "INSERT INTO projects VALUES(9,'j','ghhhgh','true',0,2);",

*/


    };

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        /*
            for(String creationQuery : CREATE_TABLES){
                db.execSQL(creationQuery);
            }
        */
        Log.d("ROOM_Database_Insert:","Insert User");
        for(String creationQuery : INSERT_user){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Students");
        for(String creationQuery : INSERT_Students){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Roles");
        for(String creationQuery : INSERT_ROLES){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Projects");
        for(String creationQuery : INSERT_Projects){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Juries");
        for(String creationQuery : INSERT_Juries){
            db.execSQL(creationQuery);
        }
        Log.d("ROOM_Database_Insert:","Insert Notes");
        for(String creationQuery : INSERT_Notes){
            db.execSQL(creationQuery);
        }
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        Log.d("ROOM_Database:","onOpen()");
        super.onOpen(db);
    }
}
