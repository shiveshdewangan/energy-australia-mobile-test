package com.energyaustralia.eatest.models

import com.energyaustralia.eatest.api.Band
import com.energyaustralia.eatest.api.MusicFestival

data class RecordLabel(
    val name: String,
    val bands: List<BandFestivalAttendance>
)

data class BandFestivalAttendance(
    val band: Band,
    val festivals: List<MusicFestival>
)