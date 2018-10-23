package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.TOKEN_EXTRA;
import static fr.eseo.dis.lemerlal.somanager.SoManagerActivity.USERS_EXTRA;

public class MenuActivity extends AppCompatActivity {

    private String token;
    private String user;
    private TextView textUser;
    private TextView textToken;
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

        Button notesSecondActivityButton = findViewById(R.id.notes_button);
        notesSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        textUser = findViewById(R.id.token);
        textToken = findViewById(R.id.token2);

        //Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        token = extras.getString(TOKEN_EXTRA);
        user =  extras.getString(USERS_EXTRA);
        textUser.setText(user);
        textToken.setText(token);

        String url ="https://192.168.4.248/pfe/webservice.php?q=MYINF&user=jpo&token="+token;
        WebServiceRequest webServiceRequest =new WebServiceRequest(MenuActivity.this);
        SSLSocketFactory certificat =webServiceRequest.getSocketFactory();

        RequestQueue rq = Volley.newRequestQueue(MenuActivity.this, new HurlStack(null, certificat));

        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {
                        Log.e("RESULT", String.valueOf(s));
                        try {
                            JSONArray jsonObject = s.getJSONArray("info");
                            Log.e("RESULzzed", String.valueOf(jsonObject));
                            String resultat = jsonObject.getString(Integer.parseInt("0"));
                            JSONObject jsonObject1 = new JSONObject(resultat);
                            String resultat2= jsonObject1.getString("idRole");
                            Log.e("RESULzzed", String.valueOf(resultat2));

                            if(resultat2.equals("4")){
                                Log.e("RESULTd$odffd", String.valueOf(s));
                            }else{
                                Log.e("RESzed", String.valueOf(s));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("RESULTERROR",volleyError.getMessage()); }
                } );
        rq.add(s);

    }

}




