package com.companyname.appname.data.bored.api

import retrofit2.Call
import retrofit2.http.GET

interface BoredApi {

    @GET("activity")
    fun getRandomActivity(): Call<ActivityDto>
}
