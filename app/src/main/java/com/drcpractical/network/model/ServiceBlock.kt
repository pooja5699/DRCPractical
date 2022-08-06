package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class ServiceBlock(

    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null

)