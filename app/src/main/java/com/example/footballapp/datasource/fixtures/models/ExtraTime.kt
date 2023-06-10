package com.example.footballapp.datasource.fixtures.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtraTime(
    @Json(name = "awayTeam")
    val awayTeam: Int? = null,
    @Json(name = "homeTeam")
    val homeTeam: Int? = null
)