package fr.eseo.dis.lemerlal.somanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import fr.eseo.dis.lemerlal.somanager.data.Projects;

public class PosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        SharedPreferences pref;
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String username = pref.getString("username", null);
        String token = pref.getString("token", null);
        Projects project = getIntent().getExtras().getParcelable(PFEActivity.PROJECT_EXTRA);
        int projectId = project.getProjectID();

        InputStream in = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInputRoot = getApplicationContext().getResources().openRawResource(R.raw.root);
            Certificate caChain;
            Certificate caInter;
            Certificate caRoot;

            try {
                caRoot = cf.generateCertificate(caInputRoot);
            } finally { ;
                caInputRoot.close();
            }

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("caRoot", caRoot);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            SSLContext contextSSL = SSLContext.getInstance("TLS");
            contextSSL.init(null, tmf.getTrustManagers(), null);

            String urlS ="https://192.168.4.248/pfe/webservice.php?q=POSTR&user="+username+"&proj="+projectId+"&token="+token;
            URL url = new URL(urlS);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(contextSSL.getSocketFactory());
            conn.setRequestMethod("GET");

            in = new BufferedInputStream(conn.getInputStream());
        }
        catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e){
            e.printStackTrace();
        }

        Bitmap postR = BitmapFactory.decodeStream(in);

        ImageView posterImageView = (ImageView) findViewById(R.id.posterImageView);
        posterImageView.setImageBitmap(postR);
    }
}