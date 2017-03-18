package dev.paytrack.paytrack.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import dev.paytrack.paytrack.adapter.PaymentAdapter;
import dev.paytrack.paytrack.model.PaymentItem;
import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.utils.Utils;

public class TripActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        mMapView = (MapView) findViewById(R.id.mapView);

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

        initialitzeMap();
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

    }

    public LatLng getLocationFromAddress(String strAddress) {
        return Utils.getLocationFromAddress(strAddress, getApplicationContext());
    }
}
