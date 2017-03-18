package dev.paytrack.paytrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

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
}
