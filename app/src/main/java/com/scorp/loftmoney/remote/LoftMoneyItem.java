package com.scorp.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

public class LoftMoneyItem {
    @SerializedName("id") private String itemId;
    @SerializedName("name") private String name;
    @SerializedName("price") private Integer price;
    @SerializedName("type") private String type;
    @SerializedName("date") private String date;

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
