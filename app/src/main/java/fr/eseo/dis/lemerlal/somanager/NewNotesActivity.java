package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewNotesActivity extends AppCompatActivity {

    private TextView editNotesView;
    public static String USERS_NAME_EXTRA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);
        Button giveNewNotesActivityButton = findViewById(R.id.give_new_notes_button);
        editNotesView = (TextView)findViewById(R.id.edit_name);
        giveNewNotesActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewNotesActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });


    }
}
