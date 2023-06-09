package com.example.footballapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score(
    @Json(name = "duration")
    val duration: String? = "",
    @Json(name = "extraTime")
    val extraTime: ExtraTime? = ExtraTime(),
    @Json(name = "fullTime")
    val fullTime: FullTime? = FullTime(),
    @Json(name = "halfTime")
    val halfTime: HalfTime? = HalfTime(),
    @Json(name = "penalties")
    val penalties: Penalties? = Penalties(),
    @Json(name = "winner")
    val winner: String? = ""
)