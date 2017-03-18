package dev.paytrack.paytrack.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import dev.paytrack.paytrack.adapter.PaymentAdapter;
import dev.paytrack.paytrack.domain.Transaction;
import dev.paytrack.paytrack.foursquare.FoursquareAPI;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;
import dev.paytrack.paytrack.model.PaymentItem;
import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.service.ServiceFactory;
import dev.paytrack.paytrack.service.TransactionService;
import dev.paytrack.paytrack.utils.Utils;

public class TripActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private MapView mMapView;

    private TransactionService transactionService;
    private FoursquareAPI foursquareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        transactionService = ServiceFactory.getTransactionService();
        foursquareAPI = ServiceFactory.getFoursquareAPI();
        mMapView = (MapView) findViewById(R.id.mapView);

        initializeData();
        initializeMap(savedInstanceState);
    }

    private void initializeData() {
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

    private void initializeMap(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng BCN = getLocationFromAddress("Barcelona");
                if (BCN != null) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(BCN)      // Sets the center to BCN
                            .zoom(10)         // we set the zoom
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);
                    List<FoursquareVenue> list = foursquareAPI.getVenuesFromCity(BCN.longitude, BCN.longitude);
                }
                mMapView.onResume();
                locateTransactions();
            }
        });
    }

    private void locateTransactions() {
        List<Transaction> transactions = transactionService.
                getTransactionsByOriginIbanBetweenDates("iban", new Date(), new Date());
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
