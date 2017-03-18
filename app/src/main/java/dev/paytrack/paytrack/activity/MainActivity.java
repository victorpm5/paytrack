package dev.paytrack.paytrack.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.deutschebank.ApiOperations;
import dev.paytrack.paytrack.deutschebank.ApiOperationsImpl;
import dev.paytrack.paytrack.utils.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new AsyncTask<Integer, Void, Void>(){

            @Override
            protected Void doInBackground(Integer... params) {

                ApiOperationsImpl api = new ApiOperationsImpl();
                api.Authoritzation();

                return null;
            }
        }.execute(1);
    }
}
