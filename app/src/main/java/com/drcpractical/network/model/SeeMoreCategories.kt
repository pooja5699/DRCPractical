package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class SeeMoreCategories(

    @SerializedName("name") var name: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("entity_id") var entityId: String? = null,
    @SerializedName("attribute_set_id") var attributeSetId: String? = null,
    @SerializedName("is_active") var isActive: String? = null,
    @SerializedName("parent_id") var parentId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("children_count") var childrenCount: String? = null,
    @SerializedName("category_shape") var categoryShape: String? = null

)