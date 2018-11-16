package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import fr.eseo.dis.lemerlal.somanager.data.Projects;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.MyPFEAdapter;
import fr.eseo.dis.lemerlal.somanager.data.adapters.PFE5Adapter;

public class PFE5Activity extends AppCompatActivity {
    public static final String PROJECT_EXTRA = "project_extra";
    public static int NEW_CARD_COUNTER;
    public static RecyclerView recycler;

    private PFE5Adapter pfeAdapter;

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
        pfeAdapter = new PFE5Adapter(this);
        recycler.setAdapter(pfeAdapter);
        loadAllProjectsData();
    }

    private void loadAllProjectsData(){

        Gson gson = new Gson();
        SharedPreferences prefPortePFE;
        prefPortePFE = getApplicationContext().getSharedPreferences("prefPortePFE", 0);
        String jsonText = prefPortePFE.getString("listePortePFE", null);
        int[] listePortePFE = gson.fromJson(jsonText, int[].class);  //EDIT: gso to gson

        for (int i=0 ; i<listePortePFE.length;i++){
            pfeAdapter.setProjects(SoManagerDatabase.getDatabase(PFE5Activity.this).projectsDao().findFromIdProjects(listePortePFE[i]));
        }

    }


}
