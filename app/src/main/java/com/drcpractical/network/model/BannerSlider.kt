package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class BannerSlider(

    @SerializedName("slide_id") var slideId: String? = null,
    @SerializedName("slider_id") var sliderId: String? = null,
    @SerializedName("store_id") var storeId: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("mobile_image") var mobileImage: String? = null,
    @SerializedName("link") var link: String? = null,
    @SerializedName("category_id") var categoryId: String? = null,
    @SerializedName("order") var order: String? = null

)