package dev.paytrack.paytrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.springframework.web.util.UriComponentsBuilder;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.deutschebank.ApiOperations;
import dev.paytrack.paytrack.deutschebank.ApiOperationsImpl;
import dev.paytrack.paytrack.utils.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String url = UriComponentsBuilder.fromUriString("https://simulator-api.db.com/gw/oidc")
                .path("/authorize")
                .queryParam("response_type","code")
                .queryParam("redirect_uri", "http://bienepaytrack.dev")
                .queryParam("client_id", "70b4ee41-2475-491c-bb15-33881819c5f4")
                .build()
                .toString();


        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

    }
}
