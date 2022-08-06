package com.drcpractical.network.model

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("newsort_by") var newsortBy: ArrayList<String> = arrayListOf(),
    @SerializedName("top_menu") var topMenu: ArrayList<TopMenu> = arrayListOf(),
    @SerializedName("banner_slider") var bannerSlider: ArrayList<BannerSlider> = arrayListOf(),
    @SerializedName("best_seller") var bestSeller: BestSeller? = BestSeller(),
    @SerializedName("muumy_me_category") var muumyMeCategory: MuumyMeCategory? = MuumyMeCategory(),
    @SerializedName("see_more_categories") var seeMoreCategories: ArrayList<SeeMoreCategories> = arrayListOf(),
    @SerializedName("shop_by_brand") var shopByBrand: ArrayList<ShopByBrand> = arrayListOf(),
    @SerializedName("plus_size_category") var plusSizeCategory: PlusSizeCategory? = PlusSizeCategory(),
    @SerializedName("trending_category") var trendingCategory: TrendingCategory? = TrendingCategory(),
    @SerializedName("shop_under") var shopUnder: ArrayList<ShopUnder> = arrayListOf(),
    @SerializedName("offer_items_banner") var offerItemsBanner: ArrayList<OfferItemsBanner> = arrayListOf(),
    @SerializedName("color_collection_banner") var colorCollectionBanner: ArrayList<ColorCollectionBanner> = arrayListOf(),
    @SerializedName("summer_sale_banner") var summerSaleBanner: ArrayList<SummerSaleBanner> = arrayListOf(),
    @SerializedName("service_block") var serviceBlock: ArrayList<ServiceBlock> = arrayListOf()

)