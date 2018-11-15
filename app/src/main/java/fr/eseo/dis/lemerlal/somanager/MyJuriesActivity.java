package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

import fr.eseo.dis.lemerlal.somanager.data.Juries;
import fr.eseo.dis.lemerlal.somanager.data.SoManagerDatabase;
import fr.eseo.dis.lemerlal.somanager.data.adapters.JuriesAdapter;
import fr.eseo.dis.lemerlal.somanager.data.adapters.MyJuriesAdapter;

public class MyJuriesActivity extends AppCompatActivity {
    public static final String JURIES_EXTRA = "juries_extra";
    public static int NEW_CARD_COUNTER;
    public ArrayList<Integer>  idJurie = new ArrayList<>();

    private MyJuriesAdapter juriesAdapter;
    private ArrayList<Juries> juries = new ArrayList<>();

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
        juriesAdapter = new MyJuriesAdapter(this);
        recycler.setAdapter(juriesAdapter);
        loadAllJuriesData();
    }

    private void loadAllJuriesData(){
        Gson gson = new Gson();
        SharedPreferences prefMyJurie;
        prefMyJurie = getApplicationContext().getSharedPreferences("prefMyJurie", 0);
        String jsonText = prefMyJurie.getString("listeMyJurie", null);
        int[] listeMyJurie = gson.fromJson(jsonText, int[].class);  //EDIT: gso to gson

        for (int i=0 ; i<listeMyJurie.length;i++){
            juriesAdapter.setJuries(SoManagerDatabase.getDatabase(MyJuriesActivity.this).juriesDao().findJuriesFromId(listeMyJurie[i]));
        }
    }

    public void clickJuriesCard(Juries jury) {
        Intent intent = new Intent(this, JuriesDetailsActivity.class);
        intent.putExtra(JURIES_EXTRA, jury);
        startActivity(intent);
    }
}