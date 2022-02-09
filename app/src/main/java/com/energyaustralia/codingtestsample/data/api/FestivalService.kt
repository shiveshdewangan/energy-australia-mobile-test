package com.energyaustralia.eatest.api

import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Retrofit

/**
 * Pure network access layer.
 */
class FestivalService(retrofit: Retrofit) {
    private val api = retrofit.create(FestivalApi::class.java)

    fun getFestivals() = api.getFestivals().executeSync()
    fun getFestivalsAsync() = api.getFestivals()


    private fun <T> Call<T>.executeSync(): T {
        val response = execute()
        return if (response.isSuccessful) {
            response.body() ?: run {
                throw IllegalStateException("Empty HTTP response")
            }
        } else {
            throw HttpException(response)
        }
    }
}