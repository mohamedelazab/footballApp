package com.example.footballapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullTime(
    @Json(name = "awayTeam")
    val awayTeam: Int? = 0,
    @Json(name = "homeTeam")
    val homeTeam: Int? = 0
)