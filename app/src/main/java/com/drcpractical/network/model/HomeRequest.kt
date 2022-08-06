package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class HomeRequest(
    @SerializedName("customer_id") var customerId: String? = null,
)