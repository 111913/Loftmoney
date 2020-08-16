package com.scorp.loftmoney.cells.item;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.scorp.loftmoney.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<ItemCellModel> itemCellModels = new ArrayList<>();
    private ItemsAdapterListener itemsAdapterListener;

    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public void setItemsAdapterListener(ItemsAdapterListener itemsAdapterListener) {
        this.itemsAdapterListener = itemsAdapterListener;
    }

    public void clearSelections(){
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public void toggleItem(final int position){
        selectedItems.put(position, !selectedItems.get(position));
        notifyDataSetChanged();
    }

    public void clearSelectItem(final int position){
        //selectedItems.put(position, false);
        selectedItems.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount(){
//        int res = 0;
//        for(int i = 0; i < selectedItems.size(); i++){
//            if(selectedItems.get(i)){
//                res++;
//            }
//        }
//        return res;
        return selectedItems.size();
    }

    public List<String> getSelectedItemsId(){
        List<String> result = new ArrayList<>();
        int i = 0;
        for(ItemCellModel item: itemCellModels){
            if(selectedItems.get(i)){
                result.add(item.getId());
            }
            i++;
        }
        return result;
    }

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

    public void sortItemsByCreatedDate(){
        Collections.sort(itemCellModels, new Comparator<ItemCellModel>() {
            @Override
            public int compare(ItemCellModel itemCellModel, ItemCellModel t1) {
                return itemCellModel.getDate().compareTo(t1.getDate());
            }
        });
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
        holder.bind(itemCellModels.get(position), selectedItems.get(position));
        holder.setListener(itemsAdapterListener, itemCellModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return itemCellModels.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        //private final ItemAdapterClick itemAdapterClick;
        private View itemView;
        private TextView nameView;
        private TextView costView;
        private TextView currencyView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //this.itemAdapterClick = itemAdapterClick;

            this.itemView = itemView;
            nameView = itemView.findViewById(R.id.cellMoneyNameView);
            costView = itemView.findViewById(R.id.cellMoneyCostView);
            currencyView = itemView.findViewById(R.id.cellMoneyCurrencyView);
        }

        public void bind(final ItemCellModel itemCellModel, final boolean isSelected){
            itemView.setSelected(isSelected);

            nameView.setText(itemCellModel.getName());
            costView.setText(itemCellModel.getCost());
            currencyView.setText(itemCellModel.getCurrency());
            costView.setTextColor(ContextCompat.getColor(costView.getContext(), itemCellModel.getColor()));
            currencyView.setTextColor((ContextCompat.getColor(currencyView.getContext(), itemCellModel.getColor())));
        }

        public void setListener(final ItemsAdapterListener listener, final ItemCellModel item, final int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item, position);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(item, position);
                    return false;
                }
            });
        }
    }
}
