package dev.paytrack.paytrack.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.adapter.VenuesAdapter;
import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;
import dev.paytrack.paytrack.service.ServiceFactory;
import dev.paytrack.paytrack.service.TransactionService;
import dev.paytrack.paytrack.utils.Utils;

public class RecommendVenuesActivity extends AppCompatActivity {

    public static final String INTENT_CITY = "INTENT_CITY";
    private GoogleMap mMap;
    private MapView mMapView;

    private String location;

    private TransactionService transactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_venues);

        location = getIntent().getExtras().getString(INTENT_CITY);

        transactionService = ServiceFactory.getTransactionService();

        mMapView = (MapView) findViewById(R.id.mapView);

        initializeData();
        initializeMap(savedInstanceState);
    }

    private void initializeData() {
        ArrayList<FoursquareVenue> venues = (ArrayList<FoursquareVenue>)
                transactionService.getCurrentRecommendVenue();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        assert recyclerView != null;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new VenuesAdapter(venues));
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
                locateVenues();
            }
        });
    }

    private void locateVenues() {
        List<FoursquareVenue> venues = transactionService.
                getCurrentRecommendVenue();
        for (FoursquareVenue fv : venues) {
            LatLng position = new LatLng(fv.getLatitude(), fv.getLongitute());
            MarkerOptions marker = new MarkerOptions()
                        .position(position)
                        .title(String.valueOf(fv.getName()));
            mMap.addMarker(marker);
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {
        return Utils.getLocationFromAddress(strAddress, getApplicationContext());
    }

    public void back(View view) {
        onBackPressed();
    }
}
