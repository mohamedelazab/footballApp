package com.example.footballapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtraTime(
    @Json(name = "awayTeam")
    val awayTeam: Any? = Any(),
    @Json(name = "homeTeam")
    val homeTeam: Any? = Any()
)