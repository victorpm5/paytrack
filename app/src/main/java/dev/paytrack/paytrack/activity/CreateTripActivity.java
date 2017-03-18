package dev.paytrack.paytrack.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.domain.Trip;
import dev.paytrack.paytrack.utils.DateUtils;
import io.realm.Realm;

public class CreateTripActivity extends AppCompatActivity implements View.OnClickListener {

    private LayoutInflater inflater;
    private Realm realm;
    private EditText destination;
    private EditText date1;
    private EditText date2;
    private RelativeLayout button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        inflater = LayoutInflater.from(this);

        destination = (EditText) findViewById(R.id.destination);
        date1 = (EditText) findViewById(R.id.date1);
        date2 = (EditText) findViewById(R.id.date2);
        button = (RelativeLayout) findViewById(R.id.done_button);

        realm = Realm.getDefaultInstance();

        button.setOnClickListener(this);
    }

    public void showDatePicker(final View view) {

        final DatePicker datePicker = (DatePicker) inflater.inflate(getResources().getLayout(R.layout.date_picker), null);
        new AlertDialog.Builder(this)
                .setTitle("Date")
                .setMessage("Enter the date")
                .setView(datePicker)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView = (TextView) view;
                        textView.setText(DateUtils.parseDatePickerToString(datePicker));
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        button.setOnClickListener(null);
        String dst = destination.getText().toString();
        String ini = date1.getText().toString();
        String fin = date2.getText().toString();
        if (dst.length() == 0 || ini.length() == 0 || fin.length() == 0) {
            Toast.makeText(this, "Fill every fields", Toast.LENGTH_SHORT).show();
        } else {
            realm.beginTransaction();
            Trip trip = realm.createObject(Trip.class);
            trip.setDestination(dst);
            trip.setInitialDate(DateUtils.parseStringToDate(ini));
            trip.setFinalDate(DateUtils.parseStringToDate(fin));
            realm.commitTransaction();
        }
        button.setOnClickListener(this);
    }
}
