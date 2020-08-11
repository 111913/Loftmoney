package com.scorp.loftmoney.cells.item;

import com.scorp.loftmoney.R;
import com.scorp.loftmoney.remote.LoftMoneyItem;

public class ItemCellModel {
    private String name;
    private String cost;
    private Integer currency;
    private Integer color;
    private String date;

    public ItemCellModel(String name, String cost, Integer currency, Integer color, String date) {
        this.name = name;
        this.cost = cost;
        this.currency = currency;
        this.color = color;
        this.date = date;
    }

    public static ItemCellModel getInstance(LoftMoneyItem loftMoneyItem){
        return new ItemCellModel(loftMoneyItem.getName(),
                loftMoneyItem.getPrice().toString(),
                R.string.currency,
                loftMoneyItem.getType().equals("expense") ? R.color.expenseColor : R.color.incomeColor,
                loftMoneyItem.getDate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public Integer getCurrency() {
        return currency;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }
}
