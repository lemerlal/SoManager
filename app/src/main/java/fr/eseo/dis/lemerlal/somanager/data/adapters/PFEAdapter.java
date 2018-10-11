package fr.eseo.dis.lemerlal.somanager.data.adapters;

import fr.eseo.dis.lemerlal.somanager.PFEActivity;
import fr.eseo.dis.lemerlal.somanager.R;
import fr.eseo.dis.lemerlal.somanager.data.Projects;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PFEAdapter extends RecyclerView.Adapter<PFEAdapter.PFEViewHolder>{

    private PFEActivity activity;

    private List<Projects> projects;

    private List<Integer> positionsExpanded;

    public PFEAdapter(PFEActivity pfeActivity){
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
        holder.confid.setText(String.valueOf(project.getConfid()));
        if (positionsExpanded.contains(position)) {
            holder.description.setVisibility(View.VISIBLE);
        } else {
            holder.description.setVisibility(View.GONE);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickProjectCard(project);
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView description = (TextView) v.findViewById(R.id.tv_pfe_description);
                TextView descriptionLabel = (TextView) v.findViewById(R.id.tv_pfe_description_label);
                if (positionsExpanded.contains(position)) {
                    description.setVisibility(View.GONE);
                    descriptionLabel.setVisibility(View.GONE);
                    positionsExpanded.remove(new Integer(position));
                } else {
                    description.setVisibility(View.VISIBLE);
                    descriptionLabel.setVisibility(View.VISIBLE);
                    positionsExpanded.add(position);
                }
                return true;
            }
        });
    }

    class PFEViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        private final TextView title;
        private final TextView description;
        private final TextView confid;

        public PFEViewHolder(View view){
            super(view);
            this.view = view;
            title = view.findViewById(R.id.tv_pfe_title);
            description = view.findViewById(R.id.tv_pfe_description);
            confid = view.findViewById(R.id.tv_pfe_confid);



        }
    }
}