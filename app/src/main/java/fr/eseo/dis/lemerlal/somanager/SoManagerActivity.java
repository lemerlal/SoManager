package fr.eseo.dis.lemerlal.somanager;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.app.Application;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class SoManagerActivity extends AppCompatActivity {

    private TextView editNameView;
    private TextView editmpView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_manager);

        editNameView = (TextView) findViewById(R.id.edit_name);
        editmpView = (TextView) findViewById(R.id.edit_mp);
    }
}


