package com.drcpractical.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CustomerTemp {
    @SerializedName("data")
    @Expose
    var cutomers: List<Datum>? = null

    @SerializedName("totalCount")
    @Expose
    var totalCount: Int? = null

    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null

}