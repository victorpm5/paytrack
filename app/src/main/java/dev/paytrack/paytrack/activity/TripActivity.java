package dev.paytrack.paytrack.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dev.paytrack.paytrack.adapter.PaymentAdapter;
import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.model.PaymentItem;
import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.service.TransactionService;
import dev.paytrack.paytrack.utils.Utils;

public class TripActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private MapView mMapView;

    private TransactionService transactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        mMapView = (MapView) findViewById(R.id.mapView);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); //without this, map showed but was empty

        // Gets to GoogleMap from the MapView and does initialization stuff
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(getApplicationContext());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(44.14, 14.2), 10);
        mMap.animateCamera(cameraUpdate);

        ArrayList<PaymentItem> paymentItems = new ArrayList<>();
        paymentItems.add(
                new PaymentItem(
                        "Starbucks Pl. Universitat",
                        4.70
                )
        );
        paymentItems.add(
                new PaymentItem(
                        "Restaurante el Puma",
                        12.30
                )
        );
        paymentItems.add(
                new PaymentItem(
                        "Tiger",
                        1.00
                )
        );

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        assert recyclerView != null;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new PaymentAdapter(paymentItems));

    }

    private void initialitzeMap() {
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                //we search for the fib
                LatLng BCN = getLocationFromAddress("Barcelona");
                if (BCN != null) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(BCN)      // Sets the center to BCN
                            .zoom(10)         // we set the zoom
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);
                }
                locateTransactions();
            }
        });
    }

    private void locateTransactions() {
        List<Transaction> transactions = transactionService.getTransactionsByOriginIbanBetweenDates("iban", new Date(), new Date());
        for (Transaction t : transactions) {
            LatLng position = getLocationFromAddress(t.getCounterPartyName());
            if (position != null) {
                MarkerOptions marker = new MarkerOptions()
                        .position(position)
                        .title(String.valueOf(t.getAmount()));
                mMap.addMarker(marker);
            }
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {
        return Utils.getLocationFromAddress(strAddress, getApplicationContext());
    }
}
