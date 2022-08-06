package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class BestSeller(

    @SerializedName("id") var id: String? = null,
    @SerializedName("bestseller_list") var bestsellerList: ArrayList<BestsellerList> = arrayListOf()

)