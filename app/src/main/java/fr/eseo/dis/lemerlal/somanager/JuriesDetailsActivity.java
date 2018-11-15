package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.lemerlal.somanager.data.Juries;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.Projects;
import fr.eseo.dis.lemerlal.somanager.data.adapters.PFEAdapter;
import fr.eseo.dis.lemerlal.somanager.data.adapters.ProjectsJuriesAdapter;

public class JuriesDetailsActivity extends AppCompatActivity {

    public static final String PROJECT_EXTRA = "project_extra";
    private TextView idJuries;
    private TextView date;
    private Juries jury;
    private Button notes;
    private ProjectsJuriesAdapter projectsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juries_details);
        int clickedJuries = 0;
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        jury = (Juries) data.getParcelable(JuriesActivity.JURIES_EXTRA);
        idJuries = findViewById(R.id.tv_details_id);
        date = findViewById(R.id.tv_details_date);
        idJuries.setText(String.valueOf(jury.getJuryID()));
        date.setText(String.valueOf(jury.getDate()));
        RecyclerView recycler = findViewById(R.id.details_projectsjury);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        projectsAdapter = new ProjectsJuriesAdapter(this);
        recycler.setAdapter(projectsAdapter);
        loadJuriesDetails();
    }

    private void loadJuriesDetails(){
        List<Projects> projectsJuries = new ArrayList<>();
        for(Projects projects : SoManagerDatabase.getDatabase(JuriesDetailsActivity.this).projectsDao().findAllProjects()){
            if(projects.getJuryId() == jury.getJuryID()) {
                projectsJuries.add(projects);
            }
        }

        projectsAdapter.setProjects(projectsJuries);
        DecimalFormat df = new DecimalFormat("0.0");
        projectsAdapter.notifyDataSetChanged();
    }

    public void clickProjectCard(Projects project) {
        Intent intent = new Intent(this, ProjetJuryDetailsActivity.class);
        intent.putExtra(PROJECT_EXTRA, project);
        startActivity(intent);
    }
}