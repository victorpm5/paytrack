package dev.paytrack.paytrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.foursquare.FoursquareVenue;

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.VenueHolder> {

    private ArrayList<FoursquareVenue> venues;

    public VenuesAdapter(ArrayList<FoursquareVenue> venues) {
        this.venues = venues;
        notifyDataSetChanged();
    }

    @Override
    public VenueHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.venue_row, parent, false);
        return new VenueHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueHolder holder, int position) {
        FoursquareVenue current = venues.get(position);
        holder.name.setText(current.getName());
        holder.category.setText(current.getCategory());
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    class VenueHolder extends RecyclerView.ViewHolder {

        public View view;
        TextView name;
        TextView category;

        VenueHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.category = (TextView) itemView.findViewById(R.id.category);
        }
    }
}
