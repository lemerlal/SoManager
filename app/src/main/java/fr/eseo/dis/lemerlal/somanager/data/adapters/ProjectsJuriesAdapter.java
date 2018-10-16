package fr.eseo.dis.lemerlal.somanager.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.eseo.dis.lemerlal.somanager.R;
import fr.eseo.dis.lemerlal.somanager.data.Projects;

public class ProjectsJuriesAdapter extends RecyclerView.Adapter<ProjectsJuriesAdapter.ProjectsJuriesViewHolder>{

    private final Context context;

    private Projects projectsJuries;

    public ProjectsJuriesAdapter(Context context, int idFilm){
        this.context = context;
        projectsJuries = null;
    }

    public int getItemCount(){
        return 1;
    }

    @NonNull
    @Override
    public ProjectsJuriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_juries_card_layout, parent, false);
        return new ProjectsJuriesViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsJuriesAdapter.ProjectsJuriesViewHolder holder, int position) {
        holder.title.setText(projectsJuries.getTitle());
        holder.description.setText(projectsJuries.getDescrip());
        holder.confid.setText("Confid"+String.valueOf(projectsJuries.getConfid()));
    }

    public void setJuryProject(Projects projectsJuries){
        this.projectsJuries = projectsJuries;
    }

    class ProjectsJuriesViewHolder extends RecyclerView.ViewHolder {
            private final View view;

            private final TextView title;
            private final TextView description;
            private final TextView confid;

        public ProjectsJuriesViewHolder(View view){
                super(view);
                this.view = view;
                title = view.findViewById(R.id.tv_projectsJuries_title);
                description = view.findViewById(R.id.tv_projectsJuries_description);
                confid = view.findViewById(R.id.tv_projectsJuries_confid);
        }
    }
}
