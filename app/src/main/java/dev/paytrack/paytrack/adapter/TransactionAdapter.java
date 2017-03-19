package dev.paytrack.paytrack.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dev.paytrack.paytrack.R;
import dev.paytrack.paytrack.domain.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private ArrayList<Transaction> transactions;

    public TransactionAdapter(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    @Override
    public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_row, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionHolder holder, int position) {
        Transaction current = transactions.get(position);
        holder.name.setText(current.getCounterPartyName());
        holder.price.setText(String.valueOf(current.getAmount()) + " €");
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class TransactionHolder extends RecyclerView.ViewHolder {

        public View view;
        TextView name;
        TextView price;

        TransactionHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
