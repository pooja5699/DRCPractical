package com.drcpractical.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("CustomerId")
    @Expose
    var customerId: String? = null

    @SerializedName("CustomerName")
    @Expose
    var customerName: String? = null

    @SerializedName("location")
    @Expose
    var location: String? = null

    @SerializedName("balanceInformation")
    @Expose
    var balanceInformation: BalanceInformation? = null

    @SerializedName("City")
    @Expose
    var city: String? = null

    @SerializedName("ProvinceCode")
    @Expose
    var provinceCode: String? = null

    @SerializedName("PostalCode")
    @Expose
    var postalCode: String? = null

    @SerializedName("CustomerPriceGroup")
    @Expose
    var customerPriceGroup: String? = null

    @SerializedName("CustomerContactNumber")
    @Expose
    var customerContactNumber: String? = null

    @SerializedName("Email")
    @Expose
    var email: String? = null

    @SerializedName("RouteId")
    @Expose
    var routeId: String? = null

    @SerializedName("ModeOfCommunication")
    @Expose
    var modeOfCommunication: String? = null

    @SerializedName("IsActive")
    @Expose
    var isActive: String? = null

    @SerializedName("CreatedAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("UpdatedAt")
    @Expose
    var updatedAt: String? = null
}