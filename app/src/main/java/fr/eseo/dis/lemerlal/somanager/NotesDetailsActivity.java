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

import fr.eseo.dis.lemerlal.somanager.data.Notes;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;

public class NotesDetailsActivity extends AppCompatActivity {

    private TextView forename;
    private TextView surname;
    private TextView mynote;
    private TextView avgnote;
    private Notes notes;
    private Button newNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        int clickedNotes = 0;
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        notes = (Notes) data.getParcelable(NotesActivity.NOTES_EXTRA);
        forename = findViewById(R.id.tv_details_forename);
        surname = findViewById(R.id.tv_details_surname);
        mynote = findViewById(R.id.tv_details_mynote);
        avgnote =  findViewById(R.id.tv_details_avgnote);
        newNotes = findViewById(R.id.button_new_notes);
        forename.setText(notes.getForename());
        surname.setText(notes.getSurename());
        mynote.setText(String.valueOf(notes.getMyNote()));
        avgnote.setText(String.valueOf(notes.getAvgNote()));
        newNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesDetailsActivity.this, NewNotesActivity.class);
                intent.putExtra(NotesActivity.NOTES_EXTRA, notes);
                startActivity(intent);
            }
        });
    }
}
