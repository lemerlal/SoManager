package fr.eseo.dis.lemerlal.somanager.data.adapters;

import fr.eseo.dis.lemerlal.somanager.JuriesActivity;
import fr.eseo.dis.lemerlal.somanager.R;
import fr.eseo.dis.lemerlal.somanager.data.Juries;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JuriesAdapter extends RecyclerView.Adapter<JuriesAdapter.JuriesViewHolder>{

    private JuriesActivity activity;

    private List<Juries> Juries;

    private List<Integer> positionsExpanded;

    public JuriesAdapter(JuriesActivity JuriesActivity){
        this.activity = JuriesActivity;
        setJuries(new ArrayList<Juries>());
        this.positionsExpanded=new ArrayList<Integer>();
    }

    public void setJuries(List<Juries> Juries){
        this.Juries = Juries;
    }

    @Override
    public int getItemCount(){
        return Juries.size();
    }

    @Override
    public JuriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View JuriesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.juries_card_layout, parent, false);
        CardView JuriesCardView = (CardView)JuriesView;
        JuriesCardView.setCardElevation(3 * JuriesActivity.NEW_CARD_COUNTER++);
        return new JuriesViewHolder(JuriesView);
    }


    @Override
    public void onBindViewHolder(@NonNull JuriesViewHolder holder, final int position) {
        final Juries jury = Juries.get(position);
        holder.idJuries.setText(String.valueOf(jury.getJuryID()));
        holder.date.setText("Date"+String.valueOf(jury.getDate()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickJuriesCard(jury);
            }
        });
    }

    class JuriesViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        private final TextView idJuries;
        private final TextView date;

        public JuriesViewHolder(View view){
            super(view);
            this.view = view;
            idJuries = view.findViewById(R.id.tv_juries_id);
            date = view.findViewById(R.id.tv_juries_date);



        }
    }
}