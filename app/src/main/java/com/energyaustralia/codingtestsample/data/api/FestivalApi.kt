package com.energyaustralia.eatest.api

import retrofit2.Call
import retrofit2.http.GET

interface FestivalApi {
    @GET("api/v1/festivals")
    fun getFestivals(): Call<List<MusicFestival>>
}