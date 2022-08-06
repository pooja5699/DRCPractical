
package com.drcpractical.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceInformation {

    @SerializedName("BalanceDue")
    @Expose
    private String balanceDue;

    public String getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(String balanceDue) {
        this.balanceDue = balanceDue;
    }

}
