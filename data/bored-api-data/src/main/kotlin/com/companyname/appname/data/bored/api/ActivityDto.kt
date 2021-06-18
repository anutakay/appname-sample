package com.companyname.appname.data.bored.api

import com.google.gson.annotations.SerializedName

data class ActivityDto(

    @SerializedName("activity")
    val activity: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("participants")
    val participants: Int,

    @SerializedName("price")
    val price: Float,

    @SerializedName("link")
    val link: String,

    @SerializedName("key")
    val key: String,

    @SerializedName("accessibility")
    val accessibility: Float
)
