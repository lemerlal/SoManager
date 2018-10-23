package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.eseo.dis.lemerlal.somanager.data.Notes;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;

public class NewNotesActivity extends AppCompatActivity {

    private TextView editNotesView;
    private Notes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);
        Button giveNewNotesActivityButton = findViewById(R.id.give_new_notes_button);
        editNotesView = (TextView)findViewById(R.id.edit_name);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        notes = (Notes) data.getParcelable(NotesActivity.NOTES_EXTRA);
        giveNewNotesActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newNotes = editNotesView.getText().toString();
                SoManagerDatabase.getDatabase(NewNotesActivity.this).notesDao().updateMyNote(Integer.parseInt(newNotes),notes.getUserID());
                Intent intent = new Intent(NewNotesActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });


    }
}
