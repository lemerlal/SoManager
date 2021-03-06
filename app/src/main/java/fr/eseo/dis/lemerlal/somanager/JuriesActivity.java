package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLSocketFactory;

import fr.eseo.dis.lemerlal.somanager.data.Juries;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.JuriesAdapter;

import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.TOKEN_EXTRA;
import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.USERS_EXTRA;

public class JuriesActivity extends AppCompatActivity {
    public static final String JURIES_EXTRA = "juries_extra";
    public static int NEW_CARD_COUNTER;

    private JuriesAdapter juriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juries);
        NEW_CARD_COUNTER=0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.juriesList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        juriesAdapter = new JuriesAdapter(this);
        recycler.setAdapter(juriesAdapter);
        loadAllJuriesData();
    }

    private void loadAllJuriesData(){
        juriesAdapter.setJuries(SoManagerDatabase.getDatabase(JuriesActivity.this).juriesDao().findAllJuries());

    }

    public void clickJuriesCard(Juries jury) {
        Intent intent = new Intent(this, JuriesDetailsActivity.class);
        intent.putExtra(JURIES_EXTRA, jury);
        startActivity(intent);
    }
}