package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import fr.eseo.dis.lemerlal.somanager.data.Notes;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.NotesAdapter;

public class NotesActivity extends AppCompatActivity {
    public static final String NOTES_EXTRA = "notes_extra";
    public static int NEW_CARD_COUNTER;

    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        NEW_CARD_COUNTER=0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.notesList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        notesAdapter = new NotesAdapter(this);
        recycler.setAdapter(notesAdapter);
        loadAllNotesData();
    }

    private void loadAllNotesData(){
        notesAdapter.setNotes(SoManagerDatabase.getDatabase(NotesActivity.this).notesDao().findAllNotes());
    }

    public void clickNotesCard(Notes notes) {
        Intent intent = new Intent(this, NotesDetailsActivity.class);
        intent.putExtra(NOTES_EXTRA, notes);
        startActivity(intent);
    }
}
