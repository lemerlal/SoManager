package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.lemerlal.somanager.data.Projects;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.Students;
import fr.eseo.dis.lemerlal.somanager.data.User;
import fr.eseo.dis.lemerlal.somanager.data.adapters.StudentsAdapter;

public class PFEDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView confid;
    private TextView supervisor;
    private StudentsAdapter studentsAdapter;
    private Projects project;
    private TextView description;
    private Button poster;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfe_details);
        int clickedProject = 0;
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        project = (Projects) data.getParcelable(PFEActivity.PROJECT_EXTRA);
        title = findViewById(R.id.tv_details_title);
        confid = findViewById(R.id.tv_details_confid);
        description = findViewById(R.id.tv_details_description);
        supervisor = findViewById(R.id.tv_details_supervisor);
        poster =  findViewById(R.id.button_poster);
        title.setText(project.getTitle());
        confid.setText(String.valueOf(project.getConfid()));
        description.setText(project.getDescrip());
        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PFEDetailsActivity.this, PosterActivity.class);
                intent.putExtra(PFEActivity.PROJECT_EXTRA, project);
                startActivity(intent);
            }
        });
        description.setText(project.getDescrip());
        RecyclerView recycler = findViewById(R.id.details_students);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        studentsAdapter = new StudentsAdapter(this);
        recycler.setAdapter(studentsAdapter);
        loadProjectDetails();
    }

    private void loadProjectDetails(){
        User supervisorProject = null;
        int indiceSupervisor = 0;
        List<User> user = SoManagerDatabase.getDatabase(PFEDetailsActivity.this).usersDao()
                .findAllUsers();
        while(indiceSupervisor < user.size() && supervisorProject == null){
            if(user.get(indiceSupervisor).getUserId() == project.getIdSupervisor()){
                supervisorProject = user.get(indiceSupervisor);
            }
            else{
                indiceSupervisor++;
            }
        }


        List<Students> usersStudents = new ArrayList<>();
        for(Students students : SoManagerDatabase.getDatabase(PFEDetailsActivity.this).studentsDao().findAllStudents()){
            if(students.getStudentID() == project.getProjectID()) {
                usersStudents.add(students);
            }
        }

        studentsAdapter.setRoles(usersStudents);
        supervisor.setText(getString(R.string.supervisor_label) + supervisorProject.getSurename()+" "+supervisorProject.getForename());
        DecimalFormat df = new DecimalFormat("0.0");
        studentsAdapter.notifyDataSetChanged();
    }
}
