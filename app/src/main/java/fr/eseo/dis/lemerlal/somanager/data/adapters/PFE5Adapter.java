package fr.eseo.dis.lemerlal.somanager.data.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.lemerlal.somanager.MyPFEActivity;
import fr.eseo.dis.lemerlal.somanager.PFE5Activity;
import fr.eseo.dis.lemerlal.somanager.PFEActivity;
import fr.eseo.dis.lemerlal.somanager.R;
import fr.eseo.dis.lemerlal.somanager.data.Projects;

public class PFE5Adapter extends RecyclerView.Adapter<PFE5Adapter.PFEViewHolder>{

    private PFE5Activity activity;

    private List<Projects> projects;

    private List<Integer> positionsExpanded;

    public PFE5Adapter(PFE5Activity pfeActivity){
        this.activity = pfeActivity;
        setProjects(new ArrayList<Projects>());
        this.positionsExpanded=new ArrayList<Integer>();
    }

    public void setProjects(List<Projects> projects){
        this.projects = projects;
    }

    @Override
    public int getItemCount(){
        return projects.size();
    }

    @Override
    public PFEViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View pfeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pfe_card_layout, parent, false);
        CardView pfeCardView = (CardView)pfeView;
        pfeCardView.setCardElevation(3 * PFEActivity.NEW_CARD_COUNTER++);
        return new PFEViewHolder(pfeView);
    }


    @Override
    public void onBindViewHolder(@NonNull PFEViewHolder holder, final int position) {
        final Projects project = projects.get(position);
        holder.title.setText(project.getTitle());
        holder.description.setText(project.getDescrip());
    }

    class PFEViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        private final TextView title;
        private final TextView description;

        public PFEViewHolder(View view){
            super(view);
            this.view = view;
            title = view.findViewById(R.id.tv_pfe_title);
            description = view.findViewById(R.id.tv_pfe_description);



        }
    }
}