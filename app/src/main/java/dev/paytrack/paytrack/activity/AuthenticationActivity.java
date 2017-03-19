package dev.paytrack.paytrack.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.deutschebank.ApiOperationsImpl;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {

            Uri uri = intent.getData();
            String code = uri.getQueryParameter("code");
            String state = uri.getQueryParameter("state");

            new AsyncTask<String, Void, Void>(){

                @Override
                protected Void doInBackground(String... params) {

                    ApiOperationsImpl api = new ApiOperationsImpl();
                    String token = api.getUserToken(params[0]);

                    return null;
                }
            }.execute(code);

        }
    }

}
