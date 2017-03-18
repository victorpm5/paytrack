package dev.paytrack.paytrack.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.utils.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String authToken = Preferences.getAuthToken(this);
        Log.d("TOKEN", authToken);
    }
}
