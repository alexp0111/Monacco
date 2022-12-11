package com.example.monacco.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monacco.R;
import com.example.monacco.helpclasses.MoneyCategory;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private ArrayList<MoneyCategory> list;
    private Resources res;
    private ArrayList<MoneyCategory> checkedItems = new ArrayList<>();

    public CategoriesAdapter(ArrayList<MoneyCategory> list, Resources res) {
        this.list = list;
        this.res = res;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MoneyCategory category = list.get(position);
        holder.color.setImageResource(category.getColor());
        holder.label.setText(category.getLabel());
        if (category.getValue() != null) holder.value.setText(category.getValue() + " ₽");
        else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.label.getCurrentTextColor() == res.getColor(R.color.white, null)){
                        holder.label.setTextColor(res.getColor(R.color.gray_700, null));
                        checkedItems.remove(category);
                    }
                    else {
                        holder.label.setTextColor(res.getColor(R.color.white, null));
                        checkedItems.add(category);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<MoneyCategory> getCheckedItems(){
        return checkedItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView color;
        public TextView label;
        public TextView value;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            color = itemView.findViewById(R.id.item_category_iv);
            label = itemView.findViewById(R.id.item_category_label);
            value = itemView.findViewById(R.id.item_category_value);
        }
    }
}
