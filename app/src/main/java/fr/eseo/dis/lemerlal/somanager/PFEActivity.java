package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.net.ssl.SSLSocketFactory;

import fr.eseo.dis.lemerlal.somanager.data.Juries;
import fr.eseo.dis.lemerlal.somanager.data.Projects;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.PFEAdapter;

public class PFEActivity extends AppCompatActivity {
    public static final String PROJECT_EXTRA = "project_extra";
    public static int NEW_CARD_COUNTER;
    public static RecyclerView recycler;

    private PFEAdapter pfeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfe);
        NEW_CARD_COUNTER=0;
        recycler = (RecyclerView)findViewById(R.id.pfeList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        pfeAdapter = new PFEAdapter(this);
        recycler.setAdapter(pfeAdapter);
        loadAllProjectsData();
    }

    private void loadAllProjectsData(){
        pfeAdapter.setProjects(SoManagerDatabase.getDatabase(PFEActivity.this).projectsDao().findAllProjects());
    }

    public void clickProjectCard(Projects project) {
        Intent intent = new Intent(this, PFEDetailsActivity.class);
        intent.putExtra(PROJECT_EXTRA, project);
        startActivity(intent);
    }


}
