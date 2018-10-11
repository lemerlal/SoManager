package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fr.eseo.dis.lemerlal.somanager.data.Projects;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.PFEAdapter;

public class PFEActivity extends AppCompatActivity {
    public static final String PROJECT_EXTRA = "project_extra";
    public static int NEW_CARD_COUNTER;

    private PFEAdapter pfeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfe);
        NEW_CARD_COUNTER=0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.pfeList);
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
    //database : voir projet 4 impelmentation

    public void clickProjectCard(Projects project) {
        Intent intent = new Intent(this, PFEDetailsActivity.class);
        intent.putExtra(PROJECT_EXTRA, project);
        startActivity(intent);
    }
}
