package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

import fr.eseo.dis.lemerlal.somanager.data.Juries;
import fr.eseo.dis.lemerlal.somanager.data.Notes;
import fr.eseo.dis.lemerlal.somanager.data.Projects;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.Students;

import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.TOKEN_EXTRA;
import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.USERS_EXTRA;

public class MenuActivity extends AppCompatActivity {

    private String token;
    private String username;
    private TextView textUser;
    private TextView textToken;
    public ArrayList<Integer> idJurie = new ArrayList<>();
    public ArrayList<Integer> idPFE = new ArrayList<>();
    public ArrayList<Integer> idNote = new ArrayList<>();
    public ArrayList<Integer> idPFENote = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button pfeSecondActivityButton = findViewById(R.id.my_pfe_button);
        pfeSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref;
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                String username = pref.getString("username", null);
                String token = pref.getString("token", null);

                String url ="https://192.168.4.248/pfe/webservice.php?q=MYPRJ&user="+username+"&token="+token;
                WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
                SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

                RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

                JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject s) {
                                try {
                                    JSONArray jsonProject = s.getJSONArray("projects");
                                    for (int i=0; i<jsonProject.length();i++){
                                        JSONObject jsonObjectProject = jsonProject.getJSONObject(i);
                                        int id =jsonObjectProject.getInt("projectId");
                                        String title =jsonObjectProject.getString("title");
                                        String description =jsonObjectProject.getString("descrip");
                                        Boolean poster =jsonObjectProject.getBoolean("poster");
                                        int confid = jsonObjectProject.getInt("confid");

                                        JSONObject jsonObjectSupervisor = jsonObjectProject.getJSONObject("supervisor");
                                        String forename = jsonObjectSupervisor.getString("forename");
                                        String surname = jsonObjectSupervisor.getString("surname");

                                        Projects projects = new Projects(id,title,description,poster,confid,forename,surname);
                                        idPFE.add(id);
                                        for (int j=0 ; j<= idPFE.size();j++) {
                                            if (SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().findFromIdProjects(id).size()== 0) {
                                                SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().updateMyProject(projects);

                                            }
                                        }

                                    }
                                    Gson gson = new Gson();
                                    String jsonText = gson.toJson(idPFE);
                                    SharedPreferences prefMyPFE = getApplicationContext().getSharedPreferences("prefMyPFE", 0); // 0 - for private mode
                                    SharedPreferences.Editor editor = prefMyPFE.edit();
                                    editor.putString("listeMyPFE", jsonText);
                                    editor.apply();

                                    Intent intent = new Intent(MenuActivity.this, MyPFEActivity.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e("RESULTERROR",volleyError.getMessage()); }
                        } );
                rq.add(s);


            }
        });

        Button MyJurySecondActivityButton = findViewById(R.id.my_jurries_button);
        MyJurySecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref;
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                String username = pref.getString("username", null);
                String token = pref.getString("token", null);

                String url ="https://192.168.4.248/pfe/webservice.php?q=MYJUR&user="+username+"&token="+token;
                WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
                SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

                RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

                JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject s) {
                                try {
                                    JSONArray jsonJuries = s.getJSONArray("juries");
                                    for (int i=0; i<jsonJuries.length();i++){
                                        JSONObject jsonObjectJuries = jsonJuries.getJSONObject(i);
                                        int id =jsonObjectJuries.getInt("idJury");
                                        String date = jsonObjectJuries.getString("date");
                                        JSONObject jsonInfo = jsonObjectJuries.getJSONObject("info");
                                        JSONArray jsonProject = jsonInfo.getJSONArray("projects");
                                        for (int j=0; j<jsonProject.length();j++){
                                            JSONObject jsonObjectProject = jsonProject.getJSONObject(j);
                                            int projectId = jsonObjectProject.getInt("projectId");
                                            String title = jsonObjectProject.getString("title");
                                            Boolean poster = jsonObjectProject.getBoolean("poster");
                                            int confid = jsonObjectProject.getInt("confid");
                                            JSONObject jsonSupervisor = jsonObjectProject.getJSONObject("supervisor");
                                            String forenameS = jsonSupervisor.getString("forename");
                                            String surnameS = jsonSupervisor.getString("surname");
                                            Projects project = new Projects(projectId,title,poster,confid,forenameS,surnameS,id);
                                            if(SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().findFromIdProjects(id).size()== 0){
                                                SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().updateMyProject(project);

                                            }
                                        }
                                        Juries juries = new Juries(id,date);
                                        idJurie.add(id);
                                        for (int j=0 ; j<= idJurie.size();j++){
                                            if(SoManagerDatabase.getDatabase(MenuActivity.this).juriesDao().findJuriesFromId(id).size()== 0){
                                                SoManagerDatabase.getDatabase(MenuActivity.this).juriesDao().updateMyJuries(juries);
                                            }
                                        }

                                    }
                                    Gson gson = new Gson();
                                    String jsonText = gson.toJson(idJurie);
                                    SharedPreferences prefMyJurie = getApplicationContext().getSharedPreferences("prefMyJurie", 0); // 0 - for private mode
                                    SharedPreferences.Editor editor = prefMyJurie.edit();
                                    editor.putString("listeMyJurie", jsonText);
                                    editor.apply();

                                    Intent intent = new Intent(MenuActivity.this, MyJuriesActivity.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e("RESULTERROR",volleyError.getMessage()); }
                        } );
                rq.add(s);
            }
        });

        Button MyPfeSecondActivityButton = findViewById(R.id.pfe_button);
        MyPfeSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref;
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                String username = pref.getString("username", null);
                String token = pref.getString("token", null);
                //String url = "https://192.168.4.248/pfe/webservice.php?q=NOTES&user=" + username +"&proj=27"+"&token=" + token;

                String url ="https://192.168.4.248/pfe/webservice.php?q=LIPRJ&user="+username+"&token="+token;
                WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
                SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

                RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

                JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject s) {
                                try {
                                    JSONArray jsonProject = s.getJSONArray("projects");
                                    for (int i=0; i<jsonProject.length();i++){
                                        JSONObject jsonObjectProject = jsonProject.getJSONObject(i);
                                        int id =jsonObjectProject.getInt("projectId");
                                        String title =jsonObjectProject.getString("title");
                                        String description =jsonObjectProject.getString("descrip");
                                        Boolean poster =jsonObjectProject.getBoolean("poster");
                                        int confid = jsonObjectProject.getInt("confid");

                                        JSONObject jsonObjectSupervisor = jsonObjectProject.getJSONObject("supervisor");
                                        String forename = jsonObjectSupervisor.getString("forename");
                                        String surname = jsonObjectSupervisor.getString("surname");

                                        JSONArray jsonStudents = jsonObjectProject.getJSONArray("students");
                                        for (int j=0; j<jsonStudents.length();j++){
                                            JSONObject jsonObjectStudent= jsonStudents.getJSONObject(j);
                                            int studentId = jsonObjectStudent.getInt("userId");
                                            String forenameS = jsonObjectStudent.getString("forename");
                                            String surnameS = jsonObjectStudent.getString("surname");
                                            Students student = new Students(studentId,forenameS,surnameS);
                                            if(SoManagerDatabase.getDatabase(MenuActivity.this).studentsDao().findNotesFromIdStudent(studentId)==null){
                                                SoManagerDatabase.getDatabase(MenuActivity.this).studentsDao().updateMyStudents(student);

                                            }
                                        }

                                        Projects projects = new Projects(id,title,description,poster,confid,forename,surname);
                                        if(SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().findFromIdProjects(id).size()== 0){
                                            SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().updateMyProject(projects);

                                        }

                                    }
                                    Intent intent = new Intent(MenuActivity.this, PFEActivity.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e("RESULTERROR",volleyError.getMessage()); }
                        } );
                rq.add(s);
            }
        });

        Button juriesSecondActivityButton = findViewById(R.id.juries_button);
        juriesSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref;
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                String username = pref.getString("username", null);
                String token = pref.getString("token", null);

                String url ="https://192.168.4.248/pfe/webservice.php?q=LIJUR&user="+username+"&token="+token;
                WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
                SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

                RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

                JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject s) {
                                try {
                                    JSONArray jsonJuries = s.getJSONArray("juries");
                                    for (int i=0; i<jsonJuries.length();i++){
                                        JSONObject jsonObjectJuries = jsonJuries.getJSONObject(i);
                                        int id =jsonObjectJuries.getInt("idJury");
                                        String date = jsonObjectJuries.getString("date");
                                        JSONObject jsonInfo = jsonObjectJuries.getJSONObject("info");
                                        JSONArray jsonProject = jsonInfo.getJSONArray("projects");
                                        for (int j=0; j<jsonProject.length();j++){
                                            JSONObject jsonObjectProject = jsonProject.getJSONObject(j);
                                            int projectId = jsonObjectProject.getInt("projectId");
                                            String title = jsonObjectProject.getString("title");
                                            Boolean poster = jsonObjectProject.getBoolean("poster");
                                            int confid = jsonObjectProject.getInt("confid");
                                            JSONObject jsonSupervisor = jsonObjectProject.getJSONObject("supervisor");
                                            String forenameS = jsonSupervisor.getString("forename");
                                            String surnameS = jsonSupervisor.getString("surname");
                                            Projects project = new Projects(projectId,title,poster,confid,forenameS,surnameS,id);
                                            if(SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().findFromIdProjects(id).size()== 0){
                                                SoManagerDatabase.getDatabase(MenuActivity.this).projectsDao().updateMyProject(project);

                                            }
                                        }
                                        Juries juries = new Juries(id,date);
                                        if(SoManagerDatabase.getDatabase(MenuActivity.this).juriesDao().findJuriesFromId(id).size()== 0){
                                            SoManagerDatabase.getDatabase(MenuActivity.this).juriesDao().updateMyJuries(juries);
                                        }
                                    }
                                    Intent intent = new Intent(MenuActivity.this, JuriesActivity.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e("RESULTERROR",volleyError.getMessage()); }
                        } );
                rq.add(s);
            }
        });

        Button notesSecondActivityButton = findViewById(R.id.notes_button);
        notesSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref;
                pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                final String username = pref.getString("username", null);
                final String token = pref.getString("token", null);


                String url ="https://192.168.4.248/pfe/webservice.php?q=MYPRJ&user="+username+"&token="+token;
                WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
                final SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

                RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

                JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject s) {
                                Log.e("RESULT", String.valueOf(s));
                                try {
                                    JSONArray jsonProject = s.getJSONArray("projects");
                                    Log.e("RESULT2", String.valueOf(jsonProject));
                                    for (int i=0; i<jsonProject.length();i++){
                                        JSONObject jsonObjectProject = jsonProject.getJSONObject(i);
                                        int id =jsonObjectProject.getInt("projectId");
                                        idPFENote.add(id);
                                        Log.e("RESULNOOTEEEEEEEE", String.valueOf(idPFENote.size()));
                                    }
                                    Log.e("RESULEEEEEEEE3", String.valueOf(idPFENote.size()));
                                    for (int j=0 ; j<idPFENote.size()  ;j++) {

                                        String urlNote = "https://192.168.4.248/pfe/webservice.php?q=NOTES&user=" + username +"&proj="+ idPFENote.get(j) +"&token=" + token;
                                        RequestQueue rqNote = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));
                                        Log.e("RESULNEEEEE3", urlNote);
                                        JsonObjectRequest sNote = new JsonObjectRequest(Request.Method.GET, urlNote, null,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject sNote) {
                                                        Log.e("RESULTNNNNEEEEE", String.valueOf(sNote));
                                                        try {
                                                            JSONArray jsonNote = sNote.getJSONArray("notes");
                                                            Log.e("RESULT2", String.valueOf(jsonNote));
                                                            for (int i = 0; i < jsonNote.length(); i++) {
                                                                Log.e("RESULT3", String.valueOf(jsonNote));
                                                                JSONObject jsonObjectNote = jsonNote.getJSONObject(i);
                                                                Log.e("RESULT4", String.valueOf(jsonObjectNote));
                                                                int id = jsonObjectNote.getInt("userID");
                                                                String forename = jsonObjectNote.getString("forename");
                                                                String surename = jsonObjectNote.getString("surename");
                                                                double note = jsonObjectNote.getInt("mynote");
                                                                double avgnote = jsonObjectNote.getInt("avgnote");

                                                                Notes notes = new Notes(id, forename, surename, note, avgnote);
                                                                idNote.add(id);
                                                                for (int j = 0; j <= idNote.size(); j++) {
                                                                    if (SoManagerDatabase.getDatabase(MenuActivity.this).notesDao().findNotesFromIdUsers(id).size() == 0) {
                                                                        SoManagerDatabase.getDatabase(MenuActivity.this).notesDao().updateMyNote(note, id);

                                                                    }
                                                                }

                                                            }

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                },

                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError volleyError) {
                                                        Log.e("RESULTERROR", volleyError.getMessage());
                                                    }
                                                });
                                        rqNote.add(sNote);

                                    }
                                }
                                    catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e("RESULTERROR",volleyError.getMessage()); }
                        } );
                rq.add(s);

                Log.e("RESULNEEEE2", String.valueOf(idPFENote.size()));

                Gson gson = new Gson();
                String jsonText = gson.toJson(idNote);
                SharedPreferences prefNote = getApplicationContext().getSharedPreferences("prefNote", 0); // 0 - for private mode
                SharedPreferences.Editor editor = prefNote.edit();
                editor.putString("listeNote", jsonText);
                editor.apply();


                Intent intent = new Intent(MenuActivity.this, NotesActivity.class);
                startActivity(intent);

            }

        });

        SharedPreferences pref;
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        username = pref.getString("username", null);
        token = pref.getString("token", null);

        String url ="https://192.168.4.248/pfe/webservice.php?q=MYINF&user="+username+"&token="+token;
        WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
        SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

        RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {
                        try {
                            JSONArray jsonObject = s.getJSONArray("info");
                            String resultat = jsonObject.getString(Integer.parseInt("0"));
                            JSONObject jsonObject1 = new JSONObject(resultat);
                            String resultat2= jsonObject1.getString("idRole");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("RESULTERROR",volleyError.getMessage()); }
                } );
        rq.add(s);

    }

}




