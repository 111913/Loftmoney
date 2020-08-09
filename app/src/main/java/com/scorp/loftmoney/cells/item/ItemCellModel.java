package com.scorp.loftmoney.cells.item;

import com.scorp.loftmoney.R;
import com.scorp.loftmoney.remote.LoftMoneyItem;

public class ItemCellModel {
    private String name;
    private String cost;
    private Integer currency;
    private Integer color;

    public ItemCellModel(String name, String cost, int currency, Integer color) {
        this.name = name;
        this.cost = cost;
        this.currency = currency;
        this.color = color;
    }

    public static ItemCellModel getInstance(LoftMoneyItem loftMoneyItem){
        return new ItemCellModel(loftMoneyItem.getName(),
                loftMoneyItem.getPrice().toString(),
                R.string.currency,
                loftMoneyItem.getType().equals("expense") ? R.color.expenseColor : R.color.incomeColor);
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

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
