package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class ShopByBrand(

    @SerializedName("brand_id") var brandId: String? = null,
    @SerializedName("label") var label: String? = null,
    @SerializedName("url_key") var urlKey: String? = null,
    @SerializedName("option_id") var optionId: String? = null,
    @SerializedName("image") var image: String? = null

)