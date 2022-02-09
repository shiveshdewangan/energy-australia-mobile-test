package com.energyaustralia.eatest.repository

import com.energyaustralia.eatest.api.Band
import com.energyaustralia.eatest.api.FestivalService
import com.energyaustralia.eatest.api.MusicFestival
import com.energyaustralia.eatest.models.BandFestivalAttendance
import com.energyaustralia.eatest.models.RecordLabel
import retrofit2.Call
import retrofit2.Response

/**
 * Festival repository.
 * Gets data from [FestivalService]. May also implement caching and additional transformations.
 */
class FestivalRepository(private val festivalService: FestivalService) {
    companion object {
        private const val NO_LABEL = "No label"
    }

    fun getAll(): List<MusicFestival> {
        return festivalService.getFestivals()
    }
    fun getAllAsync(): Call<List<MusicFestival>> {
        return festivalService.getFestivalsAsync()
    }
}