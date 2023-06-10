package com.example.footballapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FixturesResponse(
    @Json(name = "competition")
    val competition: Competition? = Competition(),
    @Json(name = "count")
    val count: Int? = 0,
    @Json(name = "filters")
    val filters: Filters? = Filters(),
    @Json(name = "matches")
    val matches: List<Match> = listOf()
)