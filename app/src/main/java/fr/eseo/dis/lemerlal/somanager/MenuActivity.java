package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONArray;

public class MenuActivity extends AppCompatActivity {

    private TextView editNameView;
    private TextView editmpView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button pfeSecondActivityButton = findViewById(R.id.pfe_button);
        pfeSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PFEActivity.class);
                startActivity(intent);
            }
        });

        Button juriesSecondActivityButton = findViewById(R.id.juries_button);
        juriesSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, JuriesActivity.class);
                startActivity(intent);
            }
        });
    }
}


