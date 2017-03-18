package dev.paytrack.paytrack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class TripItemAdapter extends RecyclerView.Adapter<TripItemAdapter.TripItemHolder> {

    private ArrayList<TripItem> tripItems;
    private RecyclerViewItemSelectedListener listener;

    public TripItemAdapter(ArrayList<TripItem> tripItems, RecyclerViewItemSelectedListener listener) {
        this.listener = listener;
        this.tripItems = tripItems;
        notifyDataSetChanged();
    }

    @Override
    public TripItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.trip_row_layout, parent, false);
        return new TripItemHolder(view);
    }

    @Override
    public void onBindViewHolder(TripItemHolder holder, int position) {
        TripItem current = tripItems.get(position);
        holder.image.setImageBitmap(current.getImage());
        holder.destination.setText(current.getDestination());
        holder.dates.setText(current.getDates());
        holder.price.setText(current.getPrice());
        final int pos = position;
        holder.view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemSelected(pos);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return tripItems.size();
    }

    class TripItemHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView image;
        TextView destination;
        TextView dates;
        TextView price;

        TripItemHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.destination = (TextView) itemView.findViewById(R.id.destination);
            this.dates = (TextView) itemView.findViewById(R.id.dates);
            this.price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
