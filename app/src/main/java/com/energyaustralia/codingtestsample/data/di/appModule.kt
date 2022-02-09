package com.energyaustralia.eatest.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.energyaustralia.eatest.api.FestivalService
import com.energyaustralia.eatest.repository.FestivalRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Dependency injection module.
 */
val appModule = module {
    single {
        val jacksonMapper = ObjectMapper().apply {
            registerModule(KotlinModule(nullToEmptyCollection = true))
        }

        Retrofit.Builder()
            .baseUrl("https://eacp.energyaustralia.com.au/codingtest/")
            .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
            .build()
    }

    single { FestivalService(get()) }
    single { FestivalRepository(get()) }
}