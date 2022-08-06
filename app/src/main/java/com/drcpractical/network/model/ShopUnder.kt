package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class ShopUnder(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("currency_code") var currencyCode: String? = null

)