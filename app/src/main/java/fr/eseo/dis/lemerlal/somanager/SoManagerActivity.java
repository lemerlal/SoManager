package fr.eseo.dis.lemerlal.somanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


public class SoManagerActivity extends AppCompatActivity {
    public static String USERS_EXTRA;
    public static String TOKEN_EXTRA;

    private  EditText textuser;
    private EditText textmp;
    private Button connexion;
    private TextView erreurConnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_manager);



        textuser = findViewById(R.id.edit_user);
        textmp = findViewById(R.id.edit_mp);
        connexion =  findViewById(R.id.button_connexion);
        erreurConnexion = findViewById(R.id.erreur_connexion);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {

                RequestQueue rq = Volley.newRequestQueue(SoManagerActivity.this, new HurlStack(null, getSocketFactory()));
                String url ="https://192.168.4.248/pfe/webservice.php?q=LOGON&user="+textuser.getText().toString()+"&pass="+textmp.getText().toString();

                JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET,  url,   null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject s) {
                                try {
                                    String resultat = s.getString("result");
                                    if(resultat.equals("OK")){
                                        String token =s.getString("token");
                                        Log.e("RESULT", String.valueOf(s));

                                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putString("username", textuser.getText().toString());
                                        editor.putString("token", token);
                                        editor.commit();

                                        Intent intent = new Intent(SoManagerActivity.this, MenuActivity.class);

                                        startActivity(intent);
                                    }else if(resultat.equals("KO")){
                                        erreurConnexion.setText("Erreur Connexion");
                                        Log.e("ErreurConnexion", String.valueOf(s));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e("RESULTfailder",volleyError.getMessage()); }
                        } );

                rq.add(s);
        }
    });



    }


    private SSLSocketFactory getSocketFactory() {

        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = getResources().openRawResource(R.raw.root);
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                Log.e("CERT", "ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }


            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);


            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);


            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {

                    Log.e("CipherUsed", session.getCipherSuite());
                    return hostname.compareTo("192.168.4.248")==0; //The Hostname of your server

                }
            };


            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            SSLContext context = null;
            context = SSLContext.getInstance("TLS");

            context.init(null, tmf.getTrustManagers(), null);
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

            SSLSocketFactory sf = context.getSocketFactory();


            return sf;

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return  null;
    }



}
