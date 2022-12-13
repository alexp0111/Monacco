package com.example.monacco.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monacco.R;
import com.example.monacco.helpclasses.Account;
import com.example.monacco.helpclasses.MoneyCategory;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private ArrayList<Account> list;
    private Resources res;

    public AccountAdapter(ArrayList<Account> list, Resources res) {
        this.list = list;
        this.res = res;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = list.get(position);
        holder.label.setText(account.getLabel());
        holder.value.setText(String.format("%d", account.getValue()));
        holder.currency.setText((account.getCurrency()));
        //
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView label;
        public TextView value;
        public TextView currency;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.item_account_label);
            value = itemView.findViewById(R.id.item_account_value);
            currency = itemView.findViewById(R.id.item_account_currency);
        }
    }
}
