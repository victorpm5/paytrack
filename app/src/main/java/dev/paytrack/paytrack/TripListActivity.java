package dev.paytrack.paytrack;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class TripListActivity extends BaseActivity implements RecyclerViewItemSelectedListener {

    private ArrayList<TripItem> tripItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        FloatingActionButton addFAB = (FloatingActionButton) findViewById(R.id.add_trip_button);
        assert addFAB != null;
        addFAB.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), CreateTripActivity.class));
                    }
                }
        );

        tripItems = new ArrayList<>();
        exampleData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        assert recyclerView != null;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TripItemAdapter(tripItems, this));

    }

    private void exampleData() {

        tripItems.add(new TripItem(
                BitmapFactory.decodeResource(getResources(), R.mipmap.barcelona),
                "Barcelona",
                "01/01/2017 - 07/01/2017",
                "500 €"
        ));
        tripItems.add(new TripItem(
                BitmapFactory.decodeResource(getResources(), R.mipmap.edinburgh),
                "Edinburgh",
                "11/01/2017 - 14/01/2017",
                "350 €"
        ));
        tripItems.add(new TripItem(
                BitmapFactory.decodeResource(getResources(), R.mipmap.firenze),
                "Firenze",
                "01/02/2017 - 01/03/2017",
                "1900 €"
        ));
        tripItems.add(new TripItem(
                BitmapFactory.decodeResource(getResources(), R.mipmap.zurich),
                "Zurich",
                "17/03/2017 - 19/01/2017",
                "200 €"
        ));
        tripItems.add(new TripItem(
                BitmapFactory.decodeResource(getResources(), R.mipmap.san_francisco),
                "San Francisco",
                "28/07/2017 - 06/08/2017",
                "3000 €"
        ));

    }

    @Override
    public void onItemSelected(int position) {
        Intent intent = new Intent(this, TripActivity.class);
        startActivity(intent);
    }
}
