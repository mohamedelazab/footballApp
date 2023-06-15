package com.example.footballapp.datasource.fixtures.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Match(
    @Json(name = "awayTeam")
    val awayTeam: AwayTeam? = AwayTeam(),
    @Json(name = "group")
    val group: Any? = Any(),
    @Json(name = "homeTeam")
    val homeTeam: HomeTeam? = HomeTeam(),
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "lastUpdated")
    val lastUpdated: String? = "",
    @Json(name = "matchday")
    val matchday: Int? = 0,
    @Json(name = "odds")
    val odds: Odds? = Odds(),
    @Json(name = "referees")
    val referees: List<Referee?>? = listOf(),
    @Json(name = "score")
    val score: Score? = Score(),
    @Json(name = "season")
    val season: Season? = Season(),
    @Json(name = "stage")
    val stage: String? = "",
    @Json(name = "status")
    val status: String? = "",
    @Json(name = "utcDate")
    val utcDate: String = ""
)