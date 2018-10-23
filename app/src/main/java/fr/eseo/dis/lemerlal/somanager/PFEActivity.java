package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Button pfeRandomSecondActivityButton = findViewById(R.id.pfe_random_button);
        pfeRandomSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFiveProjectsData();
            }
        });
    }

    private void loadAllProjectsData(){
        pfeAdapter.setProjects(SoManagerDatabase.getDatabase(PFEActivity.this).projectsDao().findAllProjects());
    }

    private void loadFiveProjectsData(){
        recycler.getRecycledViewPool().clear();
        List<Projects> allProjects =SoManagerDatabase.getDatabase(PFEActivity.this).projectsDao().findProjectsNoConfid();
        List<Projects> fiveProjects = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<5; i++){
            int randomProject = random.nextInt(allProjects.size());
            fiveProjects.add(allProjects.get(randomProject));
            allProjects.remove(randomProject);
        }
        pfeAdapter.setProjects(fiveProjects);
        pfeAdapter.notifyDataSetChanged();
    }

    public void clickProjectCard(Projects project) {
        Intent intent = new Intent(this, PFEDetailsActivity.class);
        intent.putExtra(PROJECT_EXTRA, project);
        startActivity(intent);
    }


}
