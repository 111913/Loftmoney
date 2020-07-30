package com.scorp.loftmoney.cells.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.scorp.loftmoney.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<ItemCellModel> itemCellModels = new ArrayList<>();

    public void setData(List<ItemCellModel> itemCellModels){
        this.itemCellModels.clear();
        this.itemCellModels.addAll(itemCellModels);
        notifyDataSetChanged();
    }

    public  void addData(List<ItemCellModel> itemCellModels){
        this.itemCellModels.addAll(itemCellModels);
        notifyDataSetChanged();
    }

    public void addItem(ItemCellModel itemCellModel){
        this.itemCellModels.add(itemCellModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cell_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(itemCellModels.get(position));
    }

    @Override
    public int getItemCount() {
        return itemCellModels.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        //private final ItemAdapterClick itemAdapterClick;
        TextView nameView;
        TextView costView;
        TextView currencyView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //this.itemAdapterClick = itemAdapterClick;

            nameView = itemView.findViewById(R.id.cellMoneyNameView);
            costView = itemView.findViewById(R.id.cellMoneyCostView);
            currencyView = itemView.findViewById(R.id.cellMoneyCurrencyView);
        }

        public void bind(final ItemCellModel itemCellModel){
            nameView.setText(itemCellModel.getName());
            costView.setText(itemCellModel.getCost());
            currencyView.setText(itemCellModel.getCurrency());
            costView.setTextColor(ContextCompat.getColor(costView.getContext(), itemCellModel.getColor()));
            currencyView.setTextColor((ContextCompat.getColor(currencyView.getContext(), itemCellModel.getColor())));
        }
    }
}
