package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class CatInsideList(

    @SerializedName("entity_id") var entityId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sku") var sku: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("mgs_brand") var mgsBrand: String? = null,
    @SerializedName("currency_code") var currencyCode: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("special_price") var specialPrice: String? = null,
    @SerializedName("final_price") var finalPrice: String? = null,
    @SerializedName("discount") var discount: String? = null,
    @SerializedName("product_label") var productLabel: String? = null,
    @SerializedName("is_wishlisted") var isWishlisted: Int? = null,
    @SerializedName("wishlist_item_id") var wishlistItemId: String? = null,
    @SerializedName("wishlist_id") var wishlistId: String? = null,
    @SerializedName("multi_buy_label") var multiBuyLabel: String? = null

)