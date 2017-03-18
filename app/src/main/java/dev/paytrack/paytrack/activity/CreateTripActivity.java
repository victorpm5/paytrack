package dev.paytrack.paytrack.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.domain.Trip;
import dev.paytrack.paytrack.utils.DateUtils;
import dev.paytrack.paytrack.utils.FileManager;
import dev.paytrack.paytrack.utils.ImageData;
import io.realm.Realm;

public class CreateTripActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Integer TAKE_PICTURE_REQUEST = 10;
    private static final Integer PICK_IMAGE_REQUEST = 11;

    protected Bitmap image;

    private LayoutInflater inflater;
    private Realm realm;
    private EditText destination;
    private EditText date1;
    private EditText date2;
    private RelativeLayout button;
    private ImageView imageView;
    private boolean imageEdited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        inflater = LayoutInflater.from(this);

        imageEdited = false;

        imageView = (ImageView) findViewById(R.id.image);
        destination = (EditText) findViewById(R.id.destination);
        date1 = (EditText) findViewById(R.id.date1);
        date2 = (EditText) findViewById(R.id.date2);
        button = (RelativeLayout) findViewById(R.id.done_button);

        realm = Realm.getDefaultInstance();

        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        registerForContextMenu(imageView);
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

        switch (v.getId()) {
            case R.id.image:
                openContextMenu(v);
                break;

            case R.id.done_button:
                button.setOnClickListener(null);
                String dst = destination.getText().toString();
                String ini = date1.getText().toString();
                String fin = date2.getText().toString();
                String img = "";
                if (dst.length() == 0 || ini.length() == 0 || fin.length() == 0) {
                    Toast.makeText(this, "Fill every field", Toast.LENGTH_SHORT).show();
                } else {
                    realm.beginTransaction();
                    Trip trip = realm.createObject(Trip.class);
                    trip.setDestination(dst);
                    trip.setInitialDate(DateUtils.parseStringToDate(ini));
                    trip.setFinalDate(DateUtils.parseStringToDate(fin));
                    if (imageEdited) img = "image" + new Date().getTime() + ".png";
                    trip.setImage(img);
                    new SaveImageTask().execute(ImageData.build(image, img));
                    realm.commitTransaction();
                }
                finish();
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choose_intent_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.camera_item:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, TAKE_PICTURE_REQUEST);
                }
                break;

            case R.id.file_item:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PICTURE_REQUEST) {
                Bundle extras = data.getExtras();
                image = (Bitmap) extras.get("data");
            } else if (requestCode == PICK_IMAGE_REQUEST) {
                Uri uri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imageEdited = true;
                } catch (IOException ignored) {}
            }
            if (imageView != null) imageView.setImageBitmap(image);
        }
    }

    private class SaveImageTask extends AsyncTask<ImageData, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(ImageData... params) {
            Bitmap image = params[0].getImage();
            String name = params[0].getName();
            FileManager.getInstance(getApplicationContext()).saveToInternalStorage(name, image);
            return image;

        }
    }
}
