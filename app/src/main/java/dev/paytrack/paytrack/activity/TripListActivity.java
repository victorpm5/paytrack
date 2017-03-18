package dev.paytrack.paytrack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.adapter.RecyclerViewItemSelectedListener;
import dev.paytrack.paytrack.adapter.TripItemAdapter;
import dev.paytrack.paytrack.domain.Trip;
import dev.paytrack.paytrack.utils.FileManager;
import io.realm.Realm;
import io.realm.RealmResults;

public class TripListActivity extends BaseActivity implements RecyclerViewItemSelectedListener {

    private RecyclerView recyclerView;

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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        assert recyclerView != null;
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Trip> trips = realm.where(Trip.class).findAll();
        trips.sort("initialDate");

        recyclerView.setAdapter(new TripItemAdapter(trips, this, FileManager.getInstance(this)));

    }

    @Override
    public void onItemSelected(int position) {
        Intent intent = new Intent(this, TripActivity.class);
        startActivity(intent);
    }
}
