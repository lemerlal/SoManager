package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLSocketFactory;

import fr.eseo.dis.lemerlal.somanager.data.Juries;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.JuriesAdapter;

import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.TOKEN_EXTRA;
import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.USERS_EXTRA;

public class JuriesActivity extends AppCompatActivity {
    public static final String JURIES_EXTRA = "juries_extra";
    public static int NEW_CARD_COUNTER;

    private JuriesAdapter juriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juries);
        NEW_CARD_COUNTER=0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.juriesList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        juriesAdapter = new JuriesAdapter(this);
        recycler.setAdapter(juriesAdapter);
        loadAllJuriesData();
    }

    private void loadAllJuriesData(){

        SharedPreferences pref;
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String username = pref.getString("username", null);
        String token = pref.getString("token", null);

        String url ="https://192.168.4.248/pfe/webservice.php?q=LIJUR&user="+username+"&token="+token;
        WebServiceRequest webServiceRequest =new WebServiceRequest(JuriesActivity.this);
        SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

        RequestQueue rq = Volley.newRequestQueue(JuriesActivity.this, new HurlStack(null, certificat));

        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {
                        Log.e("RESULT", String.valueOf(s));
                        try {
                            JSONArray jsonJuries = s.getJSONArray("juries");
                            Log.e("RESULT2", String.valueOf(jsonJuries));
                            for (int i=0; i<jsonJuries.length();i++){
                                Log.e("RESULT3", String.valueOf(jsonJuries));
                                JSONObject jsonObjectJuries = jsonJuries.getJSONObject(i);
                                Log.e("RESULT4", String.valueOf(jsonObjectJuries));
                                int id =jsonObjectJuries.getInt("idJury");
                                String date = jsonObjectJuries.getString("date");
                                Log.e("RESULT4", String.valueOf(id));
                                /**
                                JSONObject jsonInfo = jsonObjectJuries.getJSONObject("info");
                                JSONArray jsonProject = jsonInfo.getJSONArray("projects");
                                Log.e("RESULT5", String.valueOf(jsonInfo));
                                Log.e("RESULT6", String.valueOf(jsonProject));
                                for (int j=0; j<jsonProject.length();j++){
                                    JSONObject jsonObjectProject = jsonProject.getJSONObject(j);
                                    int projectId = jsonObjectProject.getInt("projectId");

                                   Juries juries = new Juries(id,date,projectId);
                                   SoManagerDatabase.getDatabase(JuriesActivity.this).juriesDao().updateMyJuries(juries);
                                }
                                 */
                                Juries juries = new Juries(id,date);
                                if(SoManagerDatabase.getDatabase(JuriesActivity.this).juriesDao().findJuriesFromId(id)== null){
                                    SoManagerDatabase.getDatabase(JuriesActivity.this).juriesDao().updateMyJuries(juries);
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
                        Log.e("RESULTERROR",volleyError.getMessage()); }
                } );
        rq.add(s);

        juriesAdapter.setJuries(SoManagerDatabase.getDatabase(JuriesActivity.this).juriesDao().findAllJuries());

    }

    public void clickJuriesCard(Juries jury) {
        Intent intent = new Intent(this, JuriesDetailsActivity.class);
        intent.putExtra(JURIES_EXTRA, jury);
        startActivity(intent);
    }
}