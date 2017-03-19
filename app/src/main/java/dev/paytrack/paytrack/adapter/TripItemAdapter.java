package dev.paytrack.paytrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.domain.Trip;
import dev.paytrack.paytrack.utils.FileManager;
import io.realm.RealmResults;


public class TripItemAdapter extends RecyclerView.Adapter<TripItemAdapter.TripItemHolder> {

    private RealmResults<Trip> trips;
    private RecyclerViewItemSelectedListener listener;
    private FileManager fileManager;

    public TripItemAdapter(RealmResults<Trip> trips, RecyclerViewItemSelectedListener listener, FileManager fileManager) {
        this.listener = listener;
        this.trips = trips;
        this.fileManager = fileManager;
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
        Trip current = trips.get(position);
        //holder.image.setImageBitmap(fileManager.loadImageFromStorage(current.getImage(), R.mipmap.barcelona));
        setImage(holder.image, current.getDestination());
        holder.destination.setText(current.getDestination());
        holder.dates.setText(current.getDates());
        holder.budget.setText(current.getPrice());
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

    private void setImage(ImageView imageView, String destination) {
        if (destination.equals("Barcelona")) {
            imageView.setImageBitmap(fileManager.loadImageFromStorage(R.mipmap.barcelona));
        } else if (destination.equals("Edinburgh")) {
            imageView.setImageBitmap(fileManager.loadImageFromStorage(R.mipmap.edinburgh));
        } else if (destination.equals("Zurich")) {
            imageView.setImageBitmap(fileManager.loadImageFromStorage(R.mipmap.zurich));
        } else if (destination.equals("Firenze")) {
            imageView.setImageBitmap(fileManager.loadImageFromStorage(R.mipmap.firenze));
        }
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    class TripItemHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView image;
        TextView destination;
        TextView dates;
        TextView budget;

        TripItemHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.destination = (TextView) itemView.findViewById(R.id.destination);
            this.dates = (TextView) itemView.findViewById(R.id.dates);
            this.budget = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
