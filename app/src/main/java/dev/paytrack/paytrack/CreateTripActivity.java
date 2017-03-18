package dev.paytrack.paytrack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dev.paytrack.paytrack.utils.DateUtils;

public class CreateTripActivity extends AppCompatActivity {

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        inflater = LayoutInflater.from(this);
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

}
