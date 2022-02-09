package com.energyaustralia.eatest.api

data class Band(
    val name: String,
    val recordLabel: String?
)
data class BandFestival(
    val name: String,
    val festival: String?
)

data class MusicFestival(
    val name: String?,
    val bands: List<Band>
)