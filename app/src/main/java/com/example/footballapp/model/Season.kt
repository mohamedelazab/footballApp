package com.example.footballapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "currentMatchday")
    val currentMatchday: Int? = 0,
    @Json(name = "endDate")
    val endDate: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "startDate")
    val startDate: String? = ""
)