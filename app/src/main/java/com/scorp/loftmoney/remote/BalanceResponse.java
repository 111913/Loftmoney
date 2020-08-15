package com.scorp.loftmoney.remote;

import com.google.gson.annotations.SerializedName;

public class BalanceResponse {
    @SerializedName("status") private String status;
    @SerializedName("total_expenses") private String totalExpenses;
    @SerializedName("total_income") private String totalIncome;

    public String getStatus() {
        return status;
    }

    public String getTotalExpenses() {
        return totalExpenses;
    }

    public String getTotalIncome() {
        return totalIncome;
    }
}
