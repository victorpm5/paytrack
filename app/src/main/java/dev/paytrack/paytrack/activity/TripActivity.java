package dev.paytrack.paytrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.adapter.TransactionAdapter;
import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareAPI;
import dev.paytrack.paytrack.service.ServiceFactory;
import dev.paytrack.paytrack.service.TransactionService;
import dev.paytrack.paytrack.utils.Utils;

public class TripActivity extends AppCompatActivity {

    public static final String INTENT_CITY = "INTENT_CITY";
    public static final String START_DATE = "START_DATE";
    public static final String END_DATE = "END_DATE";
    public static final String BUDGET = "BUDGET";

    private GoogleMap mMap;
    private MapView mMapView;

    private String location;
    private Date startDate;
    private Date endDate;

    private String budget;

    private TransactionService transactionService;
    private FoursquareAPI foursquareAPI;
    private TextView budgetText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        transactionService = ServiceFactory.getTransactionService();
        foursquareAPI = ServiceFactory.getFoursquareAPI();

        location = getIntent().getExtras().getString(INTENT_CITY);
        startDate = (Date) getIntent().getExtras().getSerializable(START_DATE);
        endDate = (Date) getIntent().getExtras().getSerializable(END_DATE);
        budget =  "--";//getIntent().getExtras().getString(BUDGET, "200");


        LatLng coordinates = getLocationFromAddress(location);
        foursquareAPI.generateVenuesFromCity(   coordinates.latitude,
                                                coordinates.longitude);

        mMapView = (MapView) findViewById(R.id.mapView);

        budgetText = (TextView) findViewById(R.id.money);
        budgetText.setText(budget + " €");

        initializeData();
        initializeMap(savedInstanceState);
    }

    private void initializeData() {
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionService.
                getTransactionsByOriginIbanBetweenDates(null, startDate, endDate);

        double total = 0.0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        budgetText.setText(String.valueOf(total) + " €");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        assert recyclerView != null;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TransactionAdapter(transactions));
    }

    private void initializeMap(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng BCN = getLocationFromAddress(location);
                if (BCN != null) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(BCN)      // Sets the center to BCN
                            .zoom(10)         // we set the zoom
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);
                }
                mMapView.onResume();
                locateTransactions();
            }
        });
    }

    private void locateTransactions() {
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionService.
                getTransactionsByOriginIbanBetweenDates(null, startDate, endDate);

        for (Transaction t : transactions) {
            LatLng position = getLocationFromAddress(t.getCounterPartyName());
            if (position != null) {
                MarkerOptions marker = new MarkerOptions()
                        .position(position)
                        .title(String.valueOf(t.getCounterPartyName()));
                mMap.addMarker(marker);
            }
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {
        return Utils.getLocationFromAddress(strAddress, getApplicationContext());
    }

    public void showRecommendPlaces(View view) {
        Intent intent = new Intent(this, RecommendVenuesActivity.class);
        intent.putExtra(RecommendVenuesActivity.INTENT_CITY, location);
        startActivity(intent);
    }
}
