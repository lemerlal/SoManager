package fr.eseo.dis.lemerlal.somanager.data.adapters;

import fr.eseo.dis.lemerlal.somanager.NotesActivity;
import fr.eseo.dis.lemerlal.somanager.R;
import fr.eseo.dis.lemerlal.somanager.data.Notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    private NotesActivity activity;

    private List<Notes> notes;

    private List<Integer> positionsExpanded;

    public NotesAdapter(NotesActivity notesActivity){
        this.activity = notesActivity;
        setNotes(new ArrayList<Notes>());
        this.positionsExpanded=new ArrayList<Integer>();
    }

    public void setNotes(List<Notes> notes){
        this.notes = notes;
    }

    @Override
    public int getItemCount(){
        return notes.size();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View notesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card_layout, parent, false);
        CardView notesCardView = (CardView)notesView;
        notesCardView.setCardElevation(3 * NotesActivity.NEW_CARD_COUNTER++);
        return new NotesViewHolder(notesView);
    }


    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        final Notes note = notes.get(position);
        holder.forename.setText(note.getForename());
        holder.surname.setText(note.getSurename());
        holder.mynote.setText("My Note "+String.valueOf(note.getMyNote()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickNotesCard(note);
            }
        });
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        private final TextView forename;
        private final TextView surname;
        private final TextView mynote;

        public NotesViewHolder(View view){
            super(view);
            this.view = view;
            forename = view.findViewById(R.id.tv_notes_forename);
            surname = view.findViewById(R.id.tv_notes_surname);
            mynote = view.findViewById(R.id.tv_notes_mynote);



        }
    }
}
