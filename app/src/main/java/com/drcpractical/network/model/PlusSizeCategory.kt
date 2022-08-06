package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class PlusSizeCategory(

    @SerializedName("category_id") var categoryId: String? = null,
    @SerializedName("category_name") var categoryName: String? = null,
    @SerializedName("list") var catInsideList: ArrayList<CatInsideList> = arrayListOf()

)