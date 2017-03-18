package dev.paytrack.paytrack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentHolder> {

    private ArrayList<PaymentItem> paymentItems;

    public PaymentAdapter(ArrayList<PaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
        notifyDataSetChanged();
    }

    @Override
    public PaymentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_row, parent, false);
        return new PaymentHolder(view);
    }

    @Override
    public void onBindViewHolder(PaymentHolder holder, int position) {
        PaymentItem current = paymentItems.get(position);
        holder.name.setText(current.getName());
        holder.price.setText(String.valueOf(current.getPrice()) + " €");
    }

    @Override
    public int getItemCount() {
        return paymentItems.size();
    }

    class PaymentHolder extends RecyclerView.ViewHolder {

        public View view;
        TextView name;
        TextView price;

        PaymentHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
